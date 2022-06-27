package com.nithiann.thecircle.common

sealed class Resource<T>  (val data: T? = null, val response: String? = null){
    class Success<T>(data: T): Resource<T>(data);
    class Error<T>(response: String, data: T? = null): Resource<T>(data, response);
    class Loading<T>(data: T? = null): Resource<T>(data);
}