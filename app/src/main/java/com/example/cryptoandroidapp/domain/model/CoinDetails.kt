package com.example.cryptoandroidapp.domain.model

import com.example.cryptoandroidapp.data.dto.TeamMember

data class CoinDetails(
    val description: String,
    val coinId: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<TeamMember>
)
