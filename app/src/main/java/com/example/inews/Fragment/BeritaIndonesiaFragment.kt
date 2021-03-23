package com.example.inews.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.inews.ListBeritaIndoAdapter

import com.example.inews.R

class BeritaIndonesiaFragment : Fragment() {
    private var refreshLayout: SwipeRefreshLayout? = null
    private var recyclerView: RecyclerView? = null
    private var viewModel: BeritaIndonesiaViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.berita_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView = view.findViewById(R.id.rv_listberita)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.setHasFixedSize(true)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeritaIndonesiaViewModel::class.java)
        refreshLayout?.isRefreshing = true
        viewModel?.dataBerita
        viewModel?.getResponseDataBerita()?.observe(viewLifecycleOwner, Observer { responseDataBerita ->
            if (responseDataBerita != null) {
                val adapter = responseDataBerita.articles?.let {ListBeritaIndoAdapter(it) }
                recyclerView?.adapter = adapter
                recyclerView?.adapter = adapter
                refreshLayout?.isRefreshing = false
            }else{
                refreshLayout?.isRefreshing = false
            }
        })

        refreshLayout?.setOnRefreshListener {
            viewModel?.dataBerita
        }
    }

}
