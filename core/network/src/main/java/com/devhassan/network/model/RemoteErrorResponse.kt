package com.devhassan.network.model

import com.squareup.moshi.Json

data class RemoteErrorResponse(
    @Json(name = "status_message")
    var statusMessage: String?,
    @Json(name = "status_code")
    var statusCode: Int?,
    @Json(name = "success")
    var success: Boolean?,
)