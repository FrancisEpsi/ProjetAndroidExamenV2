package fr.epsi.projetexamenv2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {


    fun showButtonBack() {
        val imageButtonBack = findViewById<ImageView>(R.id.imageViewBack)
        imageButtonBack.visibility = View.VISIBLE
        imageButtonBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

    fun setStringHeader(txt: String) {
        val textStrinHeader = findViewById<TextView>(R.id.textViewHeader)
        textStrinHeader.text = txt
    }
}