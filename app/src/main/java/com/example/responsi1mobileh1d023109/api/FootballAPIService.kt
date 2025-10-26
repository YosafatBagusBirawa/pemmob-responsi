package com.example.responsi1mobileh1d023109.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import com.example.responsi1mobileh1d023109.data.model.TeamResponse

interface FootballApiService {
    @GET("v4/teams/{idClub}")
    suspend fun getTeamData(
        @Path("idClub") idClub: Int,
        @Header("X-Auth-Token") token: String
    ): Response<TeamResponse>
}