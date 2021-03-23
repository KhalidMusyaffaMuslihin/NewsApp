package com.example.inews

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_about.*
import java.util.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.tentang_aplikasi)


        var a =  findViewById<CircleImageView>(R.id.profil)
        var b = findViewById<TextView>(R.id.judul_app)
        var c = findViewById<TextView>(R.id.descriptionapp)
        var d = findViewById<TextView>(R.id.fitur)
        var e = findViewById<TextView>(R.id.fiturberitaindoninggris)
        var f = findViewById<TextView>(R.id.fiturdeskripsi)
        var g = findViewById<TextView>(R.id.fiturlebihbnykberita)
        var h = findViewById<TextView>(R.id.fiturlocalization)
        var i = findViewById<TextView>(R.id.madeby)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
