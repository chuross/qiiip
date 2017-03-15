package com.github.chuross.qiiip.infrastructure.qiita.v2.body

import com.google.gson.annotations.SerializedName

data class TokenBody(
        @SerializedName("client_id")
        val clientId: String,
        @SerializedName("client_secret")
        val clientSecret: String,
        val code: String
)
