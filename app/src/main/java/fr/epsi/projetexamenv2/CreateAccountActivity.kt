package fr.epsi.projetexamenv2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
<<<<<<< HEAD
=======
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
>>>>>>> 7b25f417d7c4d9fd94dbb2ac4b71ebc0f57f7013

class CreateAccountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val btnCreate : Button = findViewById(R.id.button_createAccount)

        val tbLastName : EditText = findViewById(R.id.input_lastName)
        val tbFirstName : EditText = findViewById(R.id.input_firstName)
        val tbEmail : EditText = findViewById(R.id.input_email)
        val tbAddress : EditText = findViewById(R.id.input_adress)
        val tbZipcode : EditText = findViewById(R.id.input_postal)
        val tbCity : EditText = findViewById(R.id.input_city)
        val tbCardRef : EditText = findViewById(R.id.input_card)

        if (intent.getStringExtra("QRDATA") == "TRUE") {
            tbLastName.setText(intent.getStringExtra("lastname"))
            tbFirstName.setText(intent.getStringExtra("firstname"))
            tbEmail.setText(intent.getStringExtra("email"))
            tbAddress.setText(intent.getStringExtra("address"))
            tbZipcode.setText(intent.getStringExtra("zipcode"))
            tbCity.setText(intent.getStringExtra("city"))
            tbCardRef.setText(intent.getStringExtra("cardref"))
        }

        btnCreate.setOnClickListener {
            writeSharedPreferences("HasAccount","YES")
            writeSharedPreferences("LastName",tbLastName.text.toString())
            writeSharedPreferences("FirstName",tbFirstName.text.toString())
            writeSharedPreferences("Email", tbEmail.text.toString())
            writeSharedPreferences("Address", tbAddress.text.toString())
            writeSharedPreferences("ZipCode", tbZipcode.text.toString())
            writeSharedPreferences("City", tbCity.text.toString())
            writeSharedPreferences("CardRef", tbCardRef.text.toString())

            val newIntent = Intent(application, MainActivity::class.java)
            startActivity(newIntent)
            finish()
        }
    }

    private fun writeSharedPreferences(key : String , value : String){
<<<<<<< HEAD
        val sharedPreferences= getSharedPreferences("account",Context.MODE_PRIVATE)
=======
        val sharedPreferences= getSharedPreferences("account", Context.MODE_PRIVATE)
>>>>>>> 7b25f417d7c4d9fd94dbb2ac4b71ebc0f57f7013
        val edit=sharedPreferences.edit()
        edit.putString(key,value)
        edit.apply()
    }

}