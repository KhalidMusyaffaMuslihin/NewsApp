package com.example.inews.Fragment.teknologi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.inews.pojo.teknologi.ResponseTeknologi

class BeritaTeknologiViewModel : ViewModel() {
    private val respondataberitateknologi = MutableLiveData<ResponseTeknologi>()
    fun getResponseDataBeritaTeknologi(): LiveData<ResponseTeknologi> {
        return respondataberitateknologi

    }

    val dataBeritaTeknologi: Unit
        get() {
            AndroidNetworking.get("http://newsapi.org/v2/top-headlines?country=id&category=technology&apiKey=6302d5c9ee20476b8f11df692f3a818c")
                .setPriority(Priority.MEDIUM).build()
                .getAsObject(ResponseTeknologi::class.java, object :
                    ParsedRequestListener<ResponseTeknologi> {
                    override fun onResponse(response: ResponseTeknologi?) {
                        respondataberitateknologi.postValue(response)
                    }

                    override fun onError(anError: ANError?) {
                        Log.e("Error", anError.toString())
                    }
                })
        }

}
