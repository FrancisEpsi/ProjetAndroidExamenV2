package fr.epsi.projetexamenv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val btnQr : Button = findViewById(R.id.button1)
        btnQr.setOnClickListener {
            val newIntent = Intent(application, QrScanActivity::class.java)
            startActivity(newIntent)
        }
    }
}