package com.example.inews.Fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.inews.pojo.ResponseDataBerita
import com.example.inews.pojo.us.ResponseBeritaInggris

class BeritaInggrisViewModel : ViewModel() {
    private val responseDataBeritaInggris = MutableLiveData<ResponseBeritaInggris>()
    fun getResponseDataBeritaInggris(): LiveData<ResponseBeritaInggris>{
        return responseDataBeritaInggris
    }

    val dataBeritaInggris : Unit
    get() {
        AndroidNetworking.get("http://newsapi.org/v2/top-headlines?country=us&apiKey=6302d5c9ee20476b8f11df692f3a818c")
            .setPriority(Priority.MEDIUM).build().getAsObject(ResponseBeritaInggris::class.java, object :
                ParsedRequestListener<ResponseBeritaInggris> {
                override fun onResponse(response: ResponseBeritaInggris?) {
                    responseDataBeritaInggris.postValue(response)
                }

                override fun onError(anError: ANError?) {
                    Log.e("Error", anError.toString())
                }
            })

    }
}
