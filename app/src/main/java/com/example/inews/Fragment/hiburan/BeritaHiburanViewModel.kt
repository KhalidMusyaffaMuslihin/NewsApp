package com.example.inews.Fragment.hiburan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.inews.pojo.hiburan.ResponseHiburan

class BeritaHiburanViewModel : ViewModel() {
    private val respondataberitahiburan = MutableLiveData<ResponseHiburan>()
    fun getResponseDataBeritaHiburan(): LiveData<ResponseHiburan> {
        return respondataberitahiburan

    }

    val dataBeritaHiburan : Unit
        get() {
            AndroidNetworking.get("http://newsapi.org/v2/top-headlines?country=id&category=entertainment&apiKey=6302d5c9ee20476b8f11df692f3a818c")
                .setPriority(Priority.MEDIUM).build().getAsObject(ResponseHiburan::class.java, object :
                    ParsedRequestListener<ResponseHiburan> {
                    override fun onResponse(response: ResponseHiburan?) {
                        respondataberitahiburan.postValue(response)
                    }

                    override fun onError(anError: ANError?) {
                        Log.e("Error", anError.toString())
                    }
                })
        }
}
