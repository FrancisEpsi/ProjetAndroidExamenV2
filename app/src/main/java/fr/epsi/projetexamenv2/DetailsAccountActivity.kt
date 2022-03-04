package fr.epsi.projetexamenv2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class DetailsAccountActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_account)
        showButtonBack()

        val editView_name = findViewById<EditText>(R.id.inputLastName)
        val editView_prenom = findViewById<EditText>(R.id.inputFirstName)
        val editView_email = findViewById<EditText>(R.id.inputEmail)
        val editView_adresse = findViewById<EditText>(R.id.inputAdresse)
        val editView_code = findViewById<EditText>(R.id.inputCodePostal)
        val editView_ville = findViewById<EditText>(R.id.inputCity)

        val buttonUpdate = findViewById<Button>(R.id.buttonUpdateAccount)

        val sharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("lastname", null)
        val prenom = sharedPreferences.getString("firstname", null)
        val adresse = sharedPreferences.getString("address", null)
        val email = sharedPreferences.getString("email", null)
        val code = sharedPreferences.getString("zipcode", null)
        val city = sharedPreferences.getString("city", null)

        if (name != null || prenom != null || email != null || code != null ||
                city != null) {
            editView_name.setText(name)
            editView_prenom.setText(prenom)
            editView_email.setText(email)
            editView_adresse.setText(adresse)
            editView_code.setText(code)
            editView_ville.setText(city)
        }

        buttonUpdate.setOnClickListener { View.OnClickListener {
            writeSharedPreferences("HasAccount","YES")
            writeSharedPreferences("LastName",editView_name.text.toString())
            writeSharedPreferences("FirstName",editView_prenom.text.toString())
            writeSharedPreferences("Email", editView_email.text.toString())
            writeSharedPreferences("Address", editView_adresse.text.toString())
            writeSharedPreferences("ZipCode", editView_code.text.toString())
            writeSharedPreferences("City", editView_ville.text.toString())

            val newIntent = Intent(application, MainActivity::class.java)
            startActivity(newIntent)
        } }
    }

    private fun readSharedPreferences(key : String) : String{
        val sharedPreferences= getSharedPreferences("account", Context.MODE_PRIVATE)
        val txt=sharedPreferences.getString(key,"Not found")
        return txt.toString()
    }

    private fun writeSharedPreferences(key : String , value : String){
        val sharedPreferences= getSharedPreferences("account",Context.MODE_PRIVATE)
        val edit=sharedPreferences.edit()
        edit.putString(key,value)
        edit.apply()
    }
}