package fr.epsi.projetexamenv2

import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val HasAccount : String = readSharedPreferences("HasAccount")

        lateinit var newIntent : Intent
        if (HasAccount == "YES") {
            newIntent = Intent(application,MainActivity::class.java)
        } else {
            newIntent = Intent(application,NoAccountActivity::class.java)
        }

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(newIntent)
            finish()
        },2000)
    }

    private fun readSharedPreferences(key : String) : String{
        val sharedPreferences= getSharedPreferences("account",Context.MODE_PRIVATE)
        val txt=sharedPreferences.getString(key,"Not found")
        return txt.toString()
    }
}