package fr.epsi.projetexamenv2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv_CardRef : TextView = view.findViewById(R.id.tv_CardRef)
        val tv_FirstName : TextView = view.findViewById(R.id.textView_UserFirstName)
        val tv_LastName : TextView = view.findViewById(R.id.textView_UserLastName)
        val ivQRCode= view.findViewById<android.widget.ImageView>(R.id.ivQRCode)
        tv_FirstName.setText(readSharedPreferences("FirstName"))
        tv_LastName.setText(readSharedPreferences("LastName"))
        tv_CardRef.setText(readSharedPreferences("CardRef"))

        val qrwriter = com.google.zxing.qrcode.QRCodeWriter()
        try {
            val bitMatrix = qrwriter.encode(tv_CardRef.text.toString(), com.google.zxing.BarcodeFormat.QR_CODE, 200, 200)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = android.graphics.Bitmap.createBitmap(width, height, android.graphics.Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x,y, if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
                }
            }
            ivQRCode.setImageBitmap(bmp)
        } catch (ex : com.google.zxing.WriterException) {

        }

    }

    private fun readSharedPreferences(key : String) : String{
        activity?.let {
            val sharedPreferences = it.getSharedPreferences("account", android.content.Context.MODE_PRIVATE)
            val txt = sharedPreferences.getString(key, "Not found")
            return txt.toString()
        }
        return "Not found"
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}