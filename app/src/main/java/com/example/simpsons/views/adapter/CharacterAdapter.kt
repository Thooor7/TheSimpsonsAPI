package com.example.simpsons.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simpsons.R
import com.example.simpsons.models.Characters
import com.google.android.material.bottomsheet.BottomSheetDialog

class CharacterAdapter(
    val context: Context,
    var listaPersonajes: List<Characters>
): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_character, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaPersonajes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = listaPersonajes[position]

        Glide
            .with(context)
            .load(character.image)
            .centerInside()
            .into(holder.ivCharacter)

        holder.tvNomCharacter.text = character.character
        holder.cvCharacter.setOnClickListener {
            visiblePhrase(character.phrase)
        }
    }


    private fun visiblePhrase(phrase: String) {
        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_frase)

        val tvPhrase = bottomSheetDialog.findViewById<TextView>(R.id.tvPhrase)
        tvPhrase!!.text = phrase

        bottomSheetDialog.show()
    }


    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val cvCharacter = item.findViewById(R.id.cvCharacter) as CardView
        val ivCharacter = item.findViewById(R.id.ivCharacter) as ImageView
        val tvNomCharacter = item.findViewById(R.id.tvNomCharacter) as TextView
    }
}
