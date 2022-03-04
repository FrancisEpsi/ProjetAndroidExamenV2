package fr.epsi.projetexamenv2

<<<<<<< HEAD
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
=======
import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
>>>>>>> 2e047dc8401ecdaea659ea4bb13b51f746b45076
import androidx.appcompat.app.AppCompatActivity

class CreateAccountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
<<<<<<< HEAD

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

=======

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

>>>>>>> 2e047dc8401ecdaea659ea4bb13b51f746b45076
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
        val sharedPreferences= getSharedPreferences("account", Context.MODE_PRIVATE)
=======
        val sharedPreferences= getSharedPreferences("account",Context.MODE_PRIVATE)
>>>>>>> 2e047dc8401ecdaea659ea4bb13b51f746b45076
        val edit=sharedPreferences.edit()
        edit.putString(key,value)
        edit.apply()
    }

}