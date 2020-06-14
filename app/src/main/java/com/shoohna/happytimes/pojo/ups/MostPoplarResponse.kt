package com.shoohna.happytimes.pojo.ups

data class MostPoplarResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)