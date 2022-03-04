package fr.epsi.projetexamenv2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.epsi.projetexamenv2.BaseActivity
import fr.epsi.projetexamenv2.Offres
import fr.epsi.projetexamenv2.R

class OffresAdapteur (
    val context : BaseActivity,
    private val offres :  ArrayList<Offres>
    ) : RecyclerView.Adapter<OffresAdapteur.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle = view.findViewById<TextView>(R.id.textViewName)
        val textViewDesc = view.findViewById<TextView>(R.id.textViewDesc)
        val imageViewProduct = view.findViewById<ImageView>(R.id.imageProduct)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.self_offre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = offres.get(position)
        holder.textViewTitle.text = product.name
        holder.textViewDesc.text = product.desc

        // Remplir pour l'image avec Picasso
    }

    override fun getItemCount(): Int {
        return offres.size
    }
}