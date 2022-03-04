package fr.epsi.projetexamenv2

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epsi.projetexamenv2.adapter.OffresAdapteur
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class OffresActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offres)

        val offres = arrayListOf<Offres>()
        val reclyclerView = findViewById<RecyclerView>(R.id.reclyclerViewOffres)
        reclyclerView.layoutManager = LinearLayoutManager(this)
        val offresAdapter = OffresAdapteur(this, offres)
        reclyclerView.adapter = offresAdapter

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val mRequestURL = "https://djemam.com/epsi/offers.json"
        val request = Request.Builder()
            .url(mRequestURL)
            .get()
            .cacheControl(CacheControl.FORCE_CACHE)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
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
                    runOnUiThread(Runnable {
                        offresAdapter.notifyDataSetChanged()
                    })

                    Log.d("WS", data)
                    Log.d("Offres", "${offres.size}")
                }
            }
        })
    }
}