package com.example.responsi1mobileh1d023109.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023109.data.TeamRepository
import com.example.responsi1mobileh1d023109.data.model.TeamResponse
import kotlinx.coroutines.launch

class TeamViewModel(private val repository: TeamRepository) : ViewModel() {


    private val _teamData = MutableLiveData<TeamResponse?>()
    val teamData: LiveData<TeamResponse?> = _teamData


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchTeamData()
    }

    private fun fetchTeamData() {
        _isLoading.value = true
        viewModelScope.launch {
            val data = repository.getIpswichTownData()
            _teamData.postValue(data)
            _isLoading.postValue(false)
        }
    }
}