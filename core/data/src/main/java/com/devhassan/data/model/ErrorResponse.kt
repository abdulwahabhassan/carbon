package com.smartflowtech.cupidcustomerapp.data.api

import com.squareup.moshi.Json

data class ErrorResponse(
    @Json(name = "status_message")
    var statusMessage: String?,
    @Json(name = "status_code")
    var statusCode: Int?,
)