package bernatriu.gotcompanion.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import bernatriu.gotcompanion.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_house.*

class HouseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house)

        houseName.text = intent.getStringExtra("name")
        if(intent.getStringExtra("name") == null)
            houseName.text = "unknown"
        lord.text = intent.getStringExtra("lord")
        if(intent.getStringExtra("lord") == null)
            lord.text = "unknown"
        words.text = intent.getStringExtra("words")
        if(intent.getStringExtra("words") == null)
            words.text = "unknown"
        region.text = intent.getStringExtra("region")
        if(intent.getStringExtra("region") == null)
            region.text = "unknown"
        overlord.text = intent.getStringExtra("overlord")
        if(intent.getStringExtra("overlord") == null)
            overlord.text = "unknown"
        coat_arms.text = intent.getStringExtra("coatOfArms")
        if(intent.getStringExtra("coatOfArms") == null)
            coat_arms.text = "coatOfArms"


        if(intent.getStringExtra("image")!=null){
            Glide
                .with(house_image.context)
                //.load(character.image)
                .load("https://api.got.show/" + intent.getStringExtra("image"))
                .apply(
                    RequestOptions()
                        .transforms(CenterCrop())
                        .placeholder(R.drawable.default_character_image)
                )
                .into(house_image)
        }
        //Log.e("CharacterFragment", "there is no bundle!")
    }
}
