package fr.epsi.projetexamenv2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import org.json.JSONObject
import java.lang.Exception
import android.content.Context

class QrScanActivity : AppCompatActivity() {

    private lateinit var codescanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scan)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 123)
        } else {
            startScanning()
        }
    }

    private fun startScanning() {
        val scannerView: CodeScannerView = findViewById(R.id.scanner_view)
        codescanner = CodeScanner(this, scannerView)
        codescanner.camera = CodeScanner.CAMERA_BACK
        codescanner.formats = CodeScanner.ALL_FORMATS
        codescanner.autoFocusMode = AutoFocusMode.SAFE
        codescanner.scanMode = ScanMode.SINGLE
        codescanner.isAutoFocusEnabled = true
        codescanner.isFlashEnabled = false

        codescanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                decodeJson(it.text)
            }
        }

        codescanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(this, "Erreur de lecture du code QR !", Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codescanner.startPreview()
        }

    }

    private fun decodeJson(jsonStr: String){
        try {
            val jsObj = JSONObject(jsonStr)
            val firstName = jsObj.optString("firstName","empty")
            val lastName = jsObj.optString("lastName","empty")
            val email = jsObj.optString("email", "empty")
            val address = jsObj.optString("address","empty")
            val zipcode = jsObj.optString("zipcode", "empty")
            val city = jsObj.optString("city","empty")
            val cardRef = jsObj.optString("cardRef","empty")

            val newIntent = Intent(application, CreateAccountActivity::class.java)
            newIntent.putExtra("QRDATA","TRUE")
            newIntent.putExtra("firstname", firstName)
            newIntent.putExtra("lastname", lastName)
            newIntent.putExtra("email", email)
            newIntent.putExtra("address", address)
            newIntent.putExtra("zipcode", zipcode)
            newIntent.putExtra("city", city)
            newIntent.putExtra("cardref", cardRef)

            startActivity(newIntent)


        } catch (e : Exception) {
            Toast.makeText(this, "Error while parsing JSON", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScanning()
            } else {
                Toast.makeText(this, "Autorisation de la camera non accorde. Application ne va pas bien fonctionner.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codescanner.isInitialized) {
            codescanner?.startPreview()
        }
    }

    override fun onPause() {
        if (::codescanner.isInitialized) {
            codescanner?.releaseResources()
        }
        super.onPause()
    }
}