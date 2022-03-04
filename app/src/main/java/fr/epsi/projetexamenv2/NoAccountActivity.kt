package fr.epsi.projetexamenv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NoAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_account)

        val btnQrRegister = findViewById<Button>(R.id.btn_register_by_qr)
        val btnManuelRegister = findViewById<Button>(R.id.btn_register_manual)

        btnQrRegister.setOnClickListener {
            val newIntent= Intent(application,QrScanActivity::class.java)
            startActivity(newIntent)
        }

        btnManuelRegister.setOnClickListener {
            val newIntent=Intent(application,CreateAccountActivity::class.java)
            startActivity(newIntent)
        }
    }
}