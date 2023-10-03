package com.example.networktest1.model
import com.google.gson.annotations.SerializedName
import retrofit2.Response


data class FactDTO(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("data")
    val `data`: List<DataDTO?>?,
    @SerializedName("first_page_url")
    val firstPageUrl: String?,
    @SerializedName("from")
    val from: Int?,
    @SerializedName("last_page")
    val lastPage: Int?,
    @SerializedName("last_page_url")
    val lastPageUrl: String?,
    @SerializedName("linkDTOS")
    val linkDTOS: List<LinkDTO?>?,
    @SerializedName("next_page_url")
    val nextPageUrl: String?,
    @SerializedName("path")
    val path: String?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("prev_page_url")
    val prevPageUrl: Any?,
    @SerializedName("to")
    val to: Int?,
    @SerializedName("total")
    val total: Int?
) {

    data class DataDTO(
        @SerializedName("fact")
        val fact: String?,
        @SerializedName("length")
        val length: Int?
    )

    data class LinkDTO(
        @SerializedName("active")
        val active: Boolean?,
        @SerializedName("label")
        val label: String?,
        @SerializedName("url")
        val url: String?
    )

}

fun Response<FactDTO>.toDomain(): Fact {
    return if (isSuccessful){
        body()?.let { Fact.fromFactDTO(it) } ?: throw Exception("Response body is null")
    } else {
        throw Exception("Request failed with code ${code()}")
    }
}