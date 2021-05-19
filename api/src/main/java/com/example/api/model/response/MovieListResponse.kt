package com.example.api.model.response

import android.text.TextUtils
import android.text.format.DateFormat
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class MovieListResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("queryParam")
    val queryParam: QueryParam,
    @SerializedName("result")
    val result: List<Result>
)

@Keep
data class QueryParam(
    @SerializedName("category")
    val category: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("sort")
    val sort: String
)

@Keep
data class Result(
    @SerializedName("_id")
    val id: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("director")
    val director: List<String>,
    @SerializedName("downVoted")
    val downVoted: List<String>,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("pageViews")
    val pageViews: Int,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("productionCompany")
    val productionCompany: List<String>,
    @SerializedName("releasedDate")
    val releasedDate: Long,
    @SerializedName("runTime")
    val runTime: Any?,
    @SerializedName("stars")
    val stars: List<String>,
    @SerializedName("title")
    val title: String,
    @SerializedName("totalVoted")
    val totalVoted: Int,
    @SerializedName("upVoted")
    val upVoted: List<String>,
    @SerializedName("voted")
    val voted: List<Voted>,
    @SerializedName("voting")
    val voting: Int,
    @SerializedName("writers")
    val writers: List<String>
) : Serializable {
    fun getStarsList() : String {
        return TextUtils.join(", ",stars)
    }

    fun getDirectorList() : String {
        return TextUtils.join(", ",director)
    }

    fun getRunningTime() : String {
        return if(runTime != null)
            "$runTime Mins"
        else
            "Mins"
    }

    fun getRelease() : String {
        return DateFormat.format("dd MMM", (releasedDate)).toString()
    }

    fun getViews() : String {
        return "$pageViews views"
    }

    fun getVotes() : String {
        return "Voted by $totalVoted people"
    }
}

@Keep
data class Voted(
    @SerializedName("_id")
    val id: String,
    @SerializedName("downVoted")
    val downVoted: List<Any>,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("upVoted")
    val upVoted: List<String>,
    @SerializedName("updatedAt")
    val updatedAt: Int
)