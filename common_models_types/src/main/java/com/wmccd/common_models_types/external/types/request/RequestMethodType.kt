package com.wmccd.common_models_types.external.types.request

sealed interface RequestMethodType{
    object GET: RequestMethodType
    object POST: RequestMethodType
    object PUT: RequestMethodType
    object DELETE: RequestMethodType
}