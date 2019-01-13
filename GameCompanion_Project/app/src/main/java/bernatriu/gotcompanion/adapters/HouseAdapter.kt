package bernatriu.gotcompanion.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import bernatriu.gotcompanion.Fragments.HousesFragment
import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.activities.HouseActivity
import bernatriu.gotcompanion.models.GOTCharacter
import bernatriu.gotcompanion.models.GOTHouse
import bernatriu.gotcompanion.utils.OnItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.simple_house.view.*
import java.lang.Exception
import java.util.ArrayList

class HouseAdapter(var list: ArrayList<GOTHouse>): RecyclerView.Adapter<HouseAdapter.HouseViewHolder>(){

    public var OnItemClickListener: OnItemClickListener<GOTHouse>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_house, parent, false)
        return HouseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(viewHolder: HouseViewHolder, position: Int) {
        val element = list[position]
        viewHolder.name.text = element.houseName;
        if(element.image!=null){
            Glide
                .with(viewHolder.image.context)
                //.load(character.image)
                .load("https://api.got.show/" + element.image)
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
                val intent = Intent(viewHolder.button.context, HouseActivity::class.java)
                intent.putExtra("house",element.houseName)

                viewHolder.button.context.startActivity(intent)

            }catch(e: Exception){
                //Log(e.toString())
            }
        }
    }

    class HouseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.houseName
        var image: ImageView = itemView.house_image
        var button: Button = itemView.houseButton
    }
}