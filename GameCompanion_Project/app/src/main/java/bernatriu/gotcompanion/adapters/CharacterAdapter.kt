package bernatriu.gotcompanion.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.models.GOTCharacter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.simple_character.view.*
import java.lang.Exception
import java.util.ArrayList

class CharacterAdapter(var list: ArrayList<GOTCharacter>): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    public var OnItemClickListener: AdapterView.OnItemClickListener<GOTCharacter>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(viewHolder: CharacterViewHolder, position: Int) {
        val character = list[position]
        viewHolder.name.text = character.name;
        Glide
            .with(viewHolder.image.context)
            .load(character.image)
            .apply(
                RequestOptions()
                    .transforms(CenterCrop())
                    .placeholder(R.drawable.default_character_image)
            )
            .into(viewHolder.image)

        //buttons:
        viewHolder.button.setOnClickListener {

            try{
                val intent = Intent(viewHolder.button.context, SpecificCharacterFragment)
                //intent.putExtra()
                viewHolder.button.context.startActivity(intent)
            }catch(e: Exception){
                //Log(e.toString())
            }
        }
    }

    class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.characterName
        var image: ImageView = itemView.character_image
        var button: Button = itemView.characterButton
    }
}