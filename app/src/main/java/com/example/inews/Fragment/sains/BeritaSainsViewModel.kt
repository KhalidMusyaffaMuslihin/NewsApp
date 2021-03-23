package com.example.inews.Fragment.sains

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.inews.pojo.sains.ResponseSains

class BeritaSainsViewModel : ViewModel() {
    private val respondataberitasains = MutableLiveData<ResponseSains>()
    fun getResponseDataBeritaSains(): LiveData<ResponseSains> {
        return respondataberitasains

    }

    val dataBeritaSains : Unit
        get() {
            AndroidNetworking.get("http://newsapi.org/v2/top-headlines?country=id&category=science&apiKey=6302d5c9ee20476b8f11df692f3a818c")
                .setPriority(Priority.MEDIUM).build().getAsObject(ResponseSains::class.java, object :
                    ParsedRequestListener<ResponseSains> {
                    override fun onResponse(response: ResponseSains?) {
                        respondataberitasains.postValue(response)
                    }

                    override fun onError(anError: ANError?) {
                        Log.e("Error", anError.toString())
                    }
                })
        }
}
