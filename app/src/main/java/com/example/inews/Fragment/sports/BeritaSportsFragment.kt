package com.example.inews.Fragment.sports

import androidx.lifecycle.ViewModelProviders
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
import com.example.inews.Fragment.sains.BeritaSainsViewModel
import com.example.inews.Fragment.sports.BeritaSportsViewModel
import com.example.inews.ListBeritaCategory.ListSainsAdapter
import com.example.inews.ListBeritaCategory.ListSportsAdapter

import com.example.inews.R

class BeritaSportsFragment : Fragment() {

    private var refreshLayout: SwipeRefreshLayout? = null
    private var recyclerView: RecyclerView? = null
    private lateinit var viewModel: BeritaSportsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.berita_sports_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(this.context)
        recyclerView = view.findViewById(R.id.rv_beritaSports)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.setHasFixedSize(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeritaSportsViewModel::class.java)
        refreshLayout?.isRefreshing = true
        viewModel?.dataBeritaSports
        viewModel?.getResponseDataBeritaSports()?.observe(viewLifecycleOwner, Observer { responseDataBerita ->
            if (responseDataBerita != null) {
                val adapter = responseDataBerita.articles?.let { ListSportsAdapter(it) }
                recyclerView?.adapter = adapter
                recyclerView?.adapter = adapter
                refreshLayout?.isRefreshing = false
            }else{
                refreshLayout?.isRefreshing = false
            }
        })

        refreshLayout?.setOnRefreshListener {
            viewModel?.dataBeritaSports
        }
    }

}
