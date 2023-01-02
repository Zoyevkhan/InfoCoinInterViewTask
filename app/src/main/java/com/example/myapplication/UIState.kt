package com.example.myapplication


sealed class UIState<T>(val data:T?=null,val message: String?=null){
    class  isLoading<T>():UIState<T>()
    class Success<T>(data:T?):UIState<T>(data)
    class Eror<T>(message:String?):UIState<T>(message=message)
}