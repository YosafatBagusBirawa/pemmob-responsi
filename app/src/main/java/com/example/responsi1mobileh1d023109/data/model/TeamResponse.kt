package com.example.responsi1mobileh1d023109.data.model

data class TeamResponse(
    val id: Int,
    val name: String,
    val shortName: String?,
    val tla: String?,
    val crest: String?,
    val address: String?,
    val phone: String?,
    val website: String?,
    val email: String?,
    val founded: Int?,
    val clubColors: String?,
    val venue: String?,
    val coach: Coach,
    val squad: List<Player>,
    val lastUpdated: String?
)
