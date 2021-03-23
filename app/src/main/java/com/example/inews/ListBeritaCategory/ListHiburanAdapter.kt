package com.example.inews.ListBeritaCategory

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inews.Detail.DetailHiburan

import com.example.inews.R
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class ListHiburanAdapter(private val itemHiburan: List<com.example.inews.pojo.hiburan.ArticlesItem?>) :  RecyclerView.Adapter<ListHiburanAdapter.ListViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHiburanAdapter.ListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemHiburan.size
    }

    override fun onBindViewHolder(holder: ListHiburanAdapter.ListViewHolder, position: Int) {
        val dataIndividuHiburan = itemHiburan[position]
        holder.tanggalpublish.text = dataIndividuHiburan?.publishedAt?.let { convertDate(it) }


        holder.judulberita.text = dataIndividuHiburan?.title
        holder.publisher.text = dataIndividuHiburan?.source?.name
        Glide.with(holder.itemView.context).load(dataIndividuHiburan?.urlToImage).into(holder.gambarberita)
        holder.itemView.setOnClickListener { v ->
            val movewithparcel = Intent(v.context, DetailHiburan::class.java).apply{
                putExtra(DetailHiburan.DETAIL_HIBURAN,dataIndividuHiburan)
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