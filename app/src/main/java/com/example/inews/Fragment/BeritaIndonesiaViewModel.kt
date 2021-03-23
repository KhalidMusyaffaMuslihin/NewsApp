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

class BeritaIndonesiaViewModel : ViewModel() {
    private val respondataberita = MutableLiveData<ResponseDataBerita>()
    fun getResponseDataBerita(): LiveData<ResponseDataBerita>{
        return respondataberita

    }

    val dataBerita : Unit
    get() {
        AndroidNetworking.get("http://newsapi.org/v2/top-headlines?country=id&apiKey=6302d5c9ee20476b8f11df692f3a818c")
            .setPriority(Priority.MEDIUM).build().getAsObject(ResponseDataBerita::class.java, object :
                ParsedRequestListener<ResponseDataBerita>{
                override fun onResponse(response: ResponseDataBerita?) {
                    respondataberita.postValue(response)
                }

                override fun onError(anError: ANError?) {
                    Log.e("Error", anError.toString())
                }
            })
    }
}
