package com.getontop.candidate.kariaki.securenote.core

sealed class DataState<T>(val data:T?=null,val message:String?=null){
    class DataInitial<T>():DataState<T>()
    class Success<T>(data: T): DataState<T>(data)
    class Loading<T>():DataState<T>()
    class Error<T>(message: String):DataState<T>(message = message)
}
