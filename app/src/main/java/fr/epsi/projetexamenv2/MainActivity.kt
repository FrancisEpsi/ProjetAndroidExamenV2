package fr.epsi.projetexamenv2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showPictureAccount()

        val navButtonCard : TextView = findViewById(R.id.navButton_Card)
        val navButtonOffers : TextView = findViewById(R.id.navButton_Offers)
        val navButtonShop : TextView = findViewById(R.id.navButton_Shop)

        navButtonCard.setOnClickListener {
            showCardFragment()
        }
        navButtonOffers.setOnClickListener {
            showButtonOffers()
        }
        navButtonShop.setOnClickListener {
            showButtonShop()
        }

    }

    private fun showCardFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, CardFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("fF") // name can be null
        transaction.commit()
    }

    private fun showButtonOffers() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, OffersFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("fF") // name can be null
        transaction.commit()
    }

    private fun showButtonShop() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentLayout, ShopsFragment::class.java, null)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack("fF") // name can be null
        transaction.commit()
    }
}