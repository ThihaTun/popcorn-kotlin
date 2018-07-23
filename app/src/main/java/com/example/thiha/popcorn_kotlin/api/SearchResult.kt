package com.example.thiha.popcorn_kotlin.api

import com.google.gson.annotations.SerializedName

data class SearchResult (
        @SerializedName("Search")
        val search: List<Search>,
        @SerializedName("totalResults")
        val totalResult: String,
        @SerializedName("Reponse")
        val response: String
)