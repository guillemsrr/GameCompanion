package bernatriu.gotcompanion.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import bernatriu.gotcompanion.Fragments.CharactersFragment
import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.activities.CharacterActivity
import bernatriu.gotcompanion.models.GOTCharacter
import bernatriu.gotcompanion.utils.OnItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.simple_character.view.*
import java.lang.Exception
import java.util.ArrayList

class CharacterAdapter(var list: ArrayList<GOTCharacter>,var fragmentActivity: CharactersFragment): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    public var OnItemClickListener: OnItemClickListener<GOTCharacter>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    /*companion object {
        fun newInstance(characterName: String): SpecificCharacterFragment {
            val fragment = SpecificCharacterFragment()
            val args = Bundle()
            args.putString("character", characterName)
            Log.w("CharacterFragment","Introduced ${characterName} to open fragment")
            fragment.setArguments(args)
            return fragment
        }
    }*/

    override fun onBindViewHolder(viewHolder: CharacterViewHolder, position: Int) {
        val character = list[position]
        viewHolder.name.text = character.name;
        if(character.image!=null){
            Glide
                .with(viewHolder.image.context)
                //.load(character.image)
                .load("https://api.got.show/" + character.image)
                .apply(
                    RequestOptions()
                        .transforms(CenterCrop())
                        .placeholder(R.drawable.default_character_image)
                )
                .into(viewHolder.image)
        }
        //buttons:
        viewHolder.button.setOnClickListener {
            try{
                val characterIntent = Intent(viewHolder.button.context, CharacterActivity::class.java)
                characterIntent.putExtra("character",character.name)
                characterIntent.putExtra("father",character.father)
                characterIntent.putExtra("mother",character.mother)
                characterIntent.putExtra("culture",character.culture)
                characterIntent.putExtra("actor",character.actor)
                characterIntent.putExtra("image",character.image)
                characterIntent.putExtra("house",character.house)
                characterIntent.putExtra("message",character.message)
                viewHolder.button.context.startActivity(characterIntent)

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