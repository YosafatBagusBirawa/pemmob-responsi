package com.example.responsi1mobileh1d023109.data

import com.example.responsi1mobileh1d023109.api.RetrofitClient
import com.example.responsi1mobileh1d023109.data.model.TeamResponse
import java.io.IOException

class TeamRepository {

    private val apiService = RetrofitClient.apiService
    private val apiToken = RetrofitClient.API_TOKEN

    private val BOLOGNA_ID = 103

    suspend fun getBolognaData(): TeamResponse? {
        try {
            val response = apiService.getTeamData(BOLOGNA_ID, apiToken)

            return if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
