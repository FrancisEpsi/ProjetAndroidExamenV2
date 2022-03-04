package fr.epsi.projetexamenv2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epsi.projetexamenv2.adapter.OffresAdapteur
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OffersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OffersFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_offers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val offres = arrayListOf<Offres>()
        val reclyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.reclyclerViewOffres)
        activity?.let {
            reclyclerView.layoutManager = LinearLayoutManager(it)
        }
        lateinit var offresAdapter : fr.epsi.projetexamenv2.adapter.OffresAdapteur
        activity?.let {
            val baseActivity = BaseActivity()

            offresAdapter = fr.epsi.projetexamenv2.adapter.OffresAdapteur(baseActivity, offres)
        }

        reclyclerView.adapter = offresAdapter

        val okHttpClient: okhttp3.OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL = "https://djemam.com/epsi/offers.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_CACHE)
            .build()
        okHttpClient.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val data = response.body?.string()
                if(data != null) {
                    val jsOb = JSONObject(data)
                    val jsAr = jsOb.getJSONArray("item")
                    for (i in 0 until jsAr.length()) {
                        val jsOffres = jsAr.getJSONObject(i)
                        val name = jsOffres.optString("name", "")
                        val desc = jsOffres.optString("desc", "")
                        val img = jsOffres.optString("img", "")
                        val offre = Offres(name = name, desc = desc, img = img)
                        offres.add(offre)
                        Log.d("Offres", offre.name)
                    }

                    activity?.runOnUiThread {
                        offresAdapter.notifyDataSetChanged()
                    }

                    Log.d("WS", data)
                    Log.d("Offres", "${offres.size}")
                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OffersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OffersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}