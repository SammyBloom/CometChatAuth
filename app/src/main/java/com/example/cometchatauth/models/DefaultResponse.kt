package com.example.cometchatauth.models

import com.google.gson.annotations.SerializedName

data class DefaultResponse (@SerializedName("uid")val uid: String,
                            @SerializedName("name")val name: String,
                            @SerializedName("status")val status: String,
                            @SerializedName("createdAt")val createdAt:Int)

