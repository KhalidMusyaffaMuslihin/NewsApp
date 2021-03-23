package com.example.inews.Fragment.bisnis

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.inews.pojo.bisnis.ResponseBisnis

class BeritaBisnisViewModel : ViewModel() {
    private val respondataberitabisnis= MutableLiveData<ResponseBisnis>()
    fun getResponseDataBeritaBisnis(): LiveData<ResponseBisnis> {
        return respondataberitabisnis

    }

    val dataBeritaBisnis: Unit
        get() {
            AndroidNetworking.get("http://newsapi.org/v2/top-headlines?country=id&category=business&apiKey=6302d5c9ee20476b8f11df692f3a818c")
                .setPriority(Priority.MEDIUM).build().getAsObject(ResponseBisnis::class.java, object :
                    ParsedRequestListener<ResponseBisnis> {
                    override fun onResponse(response: ResponseBisnis?) {
                        respondataberitabisnis.postValue(response)
                    }

                    override fun onError(anError: ANError?) {
                        Log.e("Error", anError.toString())
                    }
                })
        }
}
