package fr.epsi.projetexamenv2

import android.content.Intent
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

    fun showPictureAccount() {
        val imageAccount = findViewById<ImageView>(R.id.imageViewAccount)
        imageAccount.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application, DetailsAccountActivity::class.java)
            startActivity(newIntent)
        })
    }

    fun invisinleImg() {
        val imageAccount = findViewById<ImageView>(R.id.imageViewAccount)
        imageAccount.visibility = View.GONE
    }

    private fun showUserCard() {

    }
}