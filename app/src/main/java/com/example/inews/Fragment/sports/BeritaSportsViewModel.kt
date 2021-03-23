package com.example.inews.Fragment.sports

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.inews.pojo.sports.ResponseSports

class BeritaSportsViewModel : ViewModel() {
    private val respondataberitasports = MutableLiveData<ResponseSports>()
    fun getResponseDataBeritaSports(): LiveData<ResponseSports> {
        return respondataberitasports

    }

    val dataBeritaSports : Unit
        get() {
            AndroidNetworking.get("http://newsapi.org/v2/top-headlines?country=id&category=sports&apiKey=6302d5c9ee20476b8f11df692f3a818c")
                .setPriority(Priority.MEDIUM).build().getAsObject(ResponseSports::class.java, object :
                    ParsedRequestListener<ResponseSports> {
                    override fun onResponse(response: ResponseSports?) {
                        respondataberitasports.postValue(response)
                    }

                    override fun onError(anError: ANError?) {
                        Log.e("Error", anError.toString())
                    }
                })
        }
}
