package fr.epsi.projetexamenv2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.offres -> {
                    val newIntent = Intent(application, OffresActivity::class.java)
                    startActivity(newIntent)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    Log.e("ERR", "Gros con")
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }
}