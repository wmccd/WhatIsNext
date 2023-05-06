package com.wmccd.common_models_types.external.models.request

import com.wmccd.common_models_types.external.types.request.RequestContentType
import com.wmccd.common_models_types.external.types.request.RequestMethodType
import com.wmccd.common_models_types.external.BaseModelType
import com.wmccd.common_models_types.external.types.ResponseType

data class RequestModel(
    val methodType: RequestMethodType,
    val contentType: RequestContentType,
    val urlDomain: String,
    val urlPath:String,
    val headers: HashMap<String, String> = hashMapOf(),
    val queryParameters: HashMap<String, String> = hashMapOf(),
    val bodyMap: HashMap<String, String> = hashMapOf(),
    val bodyJson: String = "",
    val success: (response: ResponseType) -> Unit,
    val failure: (code: Int, message: String) -> Unit
)