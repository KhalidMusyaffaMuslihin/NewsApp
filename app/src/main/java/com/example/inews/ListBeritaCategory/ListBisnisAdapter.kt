package com.example.inews.ListBeritaCategory

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inews.Detail.DetailBisnis
import com.example.inews.Detail.DetailHiburan
import com.example.inews.R
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class ListBisnisAdapter (private val itemBisnis: List<com.example.inews.pojo.bisnis.ArticlesItem?>) :  RecyclerView.Adapter<ListBisnisAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListBisnisAdapter.ListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemBisnis.size
    }

    override fun onBindViewHolder(holder: ListBisnisAdapter.ListViewHolder, position: Int) {
        val dataIndividuBisnis = itemBisnis[position]
        holder.tanggalpublish.text = dataIndividuBisnis?.publishedAt?.let { convertDate(it) }


        holder.judulberita.text = dataIndividuBisnis?.title
        holder.publisher.text = dataIndividuBisnis?.source?.name
        Glide.with(holder.itemView.context).load(dataIndividuBisnis?.urlToImage).into(holder.gambarberita)
        holder.itemView.setOnClickListener { v ->
            val movewithparcel = Intent(v.context, DetailBisnis::class.java).apply{
                putExtra(DetailBisnis.DETAIL_BISNIS,dataIndividuBisnis)
            }
            v.context.startActivity(movewithparcel)
        }
    }

    private fun convertDate(date : String) : String {
        val inputPattern = "yyyy-MM-dd"
        val outputPattern = "EEE, dd MMM yyyy"

        val inputformatter = DateTimeFormatter.ISO_DATE_TIME
        val outputformatter = DateTimeFormatter.ofPattern(outputPattern, Locale.getDefault())
        val formattedDate = inputformatter.parse(date)
        return outputformatter.format(formattedDate)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val gambarberita : ImageView
        val judulberita : TextView
        val publisher : TextView
        val tanggalpublish : TextView

        init {
            gambarberita = itemView.findViewById(R.id.imageview)
            judulberita = itemView.findViewById(R.id.titlenews)
            publisher = itemView.findViewById(R.id.publishernews)
            tanggalpublish = itemView.findViewById(R.id.jadwalpublish)

        }
    }
}