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
import com.example.inews.Detail.DetailOlahraga
import com.example.inews.R
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class ListSportsAdapter (private val itemSports: List<com.example.inews.pojo.sports.ArticlesItem?>) :  RecyclerView.Adapter<ListSportsAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListSportsAdapter.ListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemSports.size
    }

    override fun onBindViewHolder(holder: ListSportsAdapter.ListViewHolder, position: Int) {
        val dataIndividuSports = itemSports[position]
        holder.tanggalpublish.text = dataIndividuSports?.publishedAt?.let { convertDate(it) }
        holder.judulberita.text = dataIndividuSports?.title
        holder.publisher.text = dataIndividuSports?.source?.name
        Glide.with(holder.itemView.context).load(dataIndividuSports?.urlToImage).into(holder.gambarberita)
        holder.itemView.setOnClickListener { v ->
            val movewithparcel = Intent(v.context, DetailOlahraga::class.java).apply{
                putExtra(DetailOlahraga.DETAIL_OLAHRAGA,dataIndividuSports)
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