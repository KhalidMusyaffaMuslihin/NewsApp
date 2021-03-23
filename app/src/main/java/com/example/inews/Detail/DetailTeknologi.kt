package com.example.inews.Detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.inews.AboutActivity
import com.example.inews.R
import com.example.inews.pojo.teknologi.ArticlesItem
import kotlinx.android.synthetic.main.activity_detail_teknologi.*
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class DetailTeknologi : AppCompatActivity() {
    companion object {
        const val DETAIL_TEKNOLOGI = "DetailTekno"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_teknologi)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val item : ArticlesItem = intent.getParcelableExtra(DETAIL_TEKNOLOGI)

        detailjadwal_teknologi.text = item.publishedAt?.let { convertDate(it) }
        Glide.with(this).load(item.urlToImage).into(detailgambarberita_teknologi)
        detailjudul_teknologi.text = item.description
        detailpublisher_teknologi.text = item.source?.name
        deskripsiberita_teknologi.text = item.content as CharSequence?
        detaillink_teknologi.text
        detaillink_teknologi.setOnClickListener {
            intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(item.url))
            startActivity(intent)

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


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }else if (item.itemId == R.id.about){
            val pindahabout = Intent(this, AboutActivity::class.java )
            startActivity(pindahabout)
        }
        return super.onOptionsItemSelected(item)
    }
}
