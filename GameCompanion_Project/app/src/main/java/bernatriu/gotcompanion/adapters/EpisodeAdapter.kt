package bernatriu.gotcompanion.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import bernatriu.gotcompanion.Models.GOTEpisode
import bernatriu.gotcompanion.R
import bernatriu.gotcompanion.activities.EpisodeActivity
import bernatriu.gotcompanion.utils.OnItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import java.lang.Exception
import java.util.ArrayList

class EpisodeAdapter(var list: ArrayList<GOTEpisode>): RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>(){

    public var OnItemClickListener: OnItemClickListener<GOTEpisode>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_episode, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(viewHolder: EpisodeViewHolder, position: Int) {
        val episode = list[position]
        viewHolder.name.text = episode.episodeName;
        if(episode.image!=null){
            Glide
                .with(viewHolder.image.context)
                //.load(character.image)
                .load("https://api.got.show/" + episode.image)
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
                val episodeIntent = Intent(viewHolder.button.context, EpisodeActivity::class.java)
                episodeIntent.putExtra("episode",episode.episodeName)

                viewHolder.button.context.startActivity(episodeIntent)

            }catch(e: Exception){
                //Log(e.toString())
            }
        }
    }

    class EpisodeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.characterName
        var image: ImageView = itemView.character_image
        var button: Button = itemView.characterButton
    }
}