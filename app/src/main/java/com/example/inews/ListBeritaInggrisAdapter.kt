package com.example.inews

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inews.Detail.DetailBeritaInggris
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class ListBeritaInggrisAdapter(private val itemInggris: List<com.example.inews.pojo.us.ArticlesItem?>) :  RecyclerView.Adapter<ListBeritaInggrisAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListBeritaInggrisAdapter.ListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemInggris.size
    }

    override fun onBindViewHolder(holder: ListBeritaInggrisAdapter.ListViewHolder, position: Int) {
        val dataIndividuBeritaInggris = itemInggris[position]

        holder.tanggalpublish.text = dataIndividuBeritaInggris?.publishedAt?.let { convertDate(it) }


        holder.judulberita.text = dataIndividuBeritaInggris?.title
        holder.publisher.text = dataIndividuBeritaInggris?.source?.name
        Glide.with(holder.itemView.context).load(dataIndividuBeritaInggris?.urlToImage)
            .into(holder.gambarberita)
        holder.itemView.setOnClickListener { v ->
            val movewithparcel = Intent(v.context, DetailBeritaInggris::class.java).apply{
                putExtra(DetailBeritaInggris.DETAIL_BERITA_INGGRIS,dataIndividuBeritaInggris)
            }
            v.context.startActivity(movewithparcel)
        }
    }

    private fun convertDate(date : String) : String {
        val inputPattern = "yyyy-MM-dd" // karena inputnya 2020-04-30 maka patternya yyyy-MM-dd
        val outputPattern = "EEE, dd MMM yyyy" // output : Thu, 30 Apr 2020
        //val outputPattern = "EEEE, dd MMMM yyyy" // output : Thurshday, 30 Apr 2020
//        val inputformatter =  DateTimeFormatter.ofPattern(inputPattern)
        val inputformatter =  DateTimeFormatter.ISO_DATE_TIME
        val outputformatter = DateTimeFormatter.ofPattern(outputPattern, Locale.getDefault())
        val formattedDate = inputformatter.parse(date)
        return outputformatter.format(formattedDate)

    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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