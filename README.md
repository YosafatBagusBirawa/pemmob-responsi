# Responsi 1 Mobile - Bologna FC 1909 Info App

Aplikasi Android sederhana yang menampilkan informasi tentang klub sepak bola Bologna FC 1909. Aplikasi ini dibuat sebagai tugas Responsi 1 Praktikum Pemrograman Mobile.

---

## Informasi Praktikan

* **Nama:** Yosafat Bagus Birawa
* **NIM:** H1D023109
* **Shift Baru:** D
* **Shift Asal:** [D

---

## Video Demo Aplikasi

![Video Demo Aplikasi](ss/nama_video_demo.gif)

<br>
---

## Penjelasan Alur Data Aplikasi

Aplikasi ini mengadopsi arsitektur modern Android yang memisahkan tanggung jawab antara lapisan data, logika bisnis (ViewModel), dan antarmuka pengguna (UI). Berikut adalah alur data dari pemanggilan API hingga penyajian di layar:

### Komponen Arsitektur Utama

1.  **Retrofit & OkHttp (Lapisan Jaringan)**: Bertugas melakukan pemanggilan HTTP ke API `https://api.football-data.org/` untuk mendapatkan data tim. `HttpLoggingInterceptor` digunakan untuk memantau request/response saat debugging.
2.  **Repository (`TeamRepository`)**: Bertindak sebagai *single source of truth*. Mengambil data dari `FootballAPIService` dan menyediakan data bersih ke ViewModel.
3.  **ViewModel (`TeamViewModel`)**: Menjembatani Repository dan UI. Menyimpan data UI (seperti info tim, pelatih, skuad) dan mempertahankannya saat ada perubahan konfigurasi (misal rotasi layar). Menggunakan `viewModelScope` untuk memanggil fungsi suspend dari Repository.
4.  **LiveData**: Digunakan di dalam `TeamViewModel` untuk menyimpan data yang bisa diamati oleh UI. UI akan otomatis diperbarui ketika data di LiveData berubah.
5.  **Activity/Fragment & Adapter (Lapisan UI)**:
    * **Activities** (`MainActivity`, `ClubHistoryActivity`, `CoachActivity`, `SquadActivity`): Menampilkan layout XML dan mengamati `LiveData` dari `TeamViewModel`.
    * **Fragment** (`PlayerDetailFragment`): Menampilkan detail pemain dalam BottomSheet.
    * **Adapter** (`PlayerAdapter`): Mengelola data pemain untuk ditampilkan di `RecyclerView` dalam `SquadActivity`, termasuk logika pewarnaan kartu berdasarkan posisi pemain.
    * **Coil**: Digunakan untuk memuat gambar pelatih di `CoachActivity`.

### Proses Pengambilan Data

1.  **Inisiasi**: Saat `CoachActivity` atau `SquadActivity` dibuat, `TeamViewModel` diinisialisasi melalui `TeamViewModelFactory` (yang menyuntikkan `TeamRepository`).
2.  **Trigger Pengambilan Data**: Dalam blok `init` `TeamViewModel`, sebuah *coroutine* diluncurkan menggunakan `viewModelScope` untuk memanggil `repository.getBolognaData()`. Status `_isLoading` diset `true`.
3.  **Request API**: `TeamRepository` memanggil `apiService.getTeamData()` (fungsi suspend Retrofit) dengan ID klub Bologna (103) dan token API. Pemanggilan ini terjadi di *background thread* karena merupakan *suspend function*.
4.  **Respons API**: Retrofit (dengan GsonConverter) mengonversi respons JSON dari API menjadi objek `TeamResponse`.
5.  **Update LiveData**: `TeamRepository` mengembalikan objek `TeamResponse` (atau `null` jika gagal) ke `TeamViewModel`. ViewModel kemudian mem-posting data ini ke `_teamData` (MutableLiveData) dan mengupdate `_isLoading` menjadi `false`.
6.  **Observasi UI**: Activity (`CoachActivity` atau `SquadActivity`) yang sedang mengamati (`observe`) `viewModel.teamData` akan menerima data baru.
7.  **Penyajian di Layar**:
    * Di `CoachActivity`, detail pelatih (`teamData.coach`) ditampilkan di `TextView` dan gambar dimuat menggunakan Coil.
    * Di `SquadActivity`, daftar pemain (`teamData.squad`) diberikan ke `PlayerAdapter` yang kemudian menampilkannya di `RecyclerView`. Setiap item pemain diwarnai sesuai posisinya. Saat item diklik, `PlayerDetailFragment` ditampilkan.

Dengan alur ini, komponen-komponen aplikasi terpisah dengan baik, memudahkan pengembangan, pengujian, dan pemeliharaan.

---