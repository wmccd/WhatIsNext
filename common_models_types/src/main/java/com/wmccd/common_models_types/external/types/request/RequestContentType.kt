package com.wmccd.common_models_types.external.types.request

sealed interface RequestContentType{
    object ApplicationJson: RequestContentType
}