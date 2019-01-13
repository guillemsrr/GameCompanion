package bernatriu.gotcompanion.Models

import com.google.gson.annotations.SerializedName
import java.util.*

data class GOTEpisode (
    @SerializedName("name") var  episodeName : String? = null,
    @SerializedName("season")    var season: Int? = null,
    @SerializedName("nr")      var number: Int? = null,
    @SerializedName("imageLink")      var image: String? = null,
    @SerializedName("airDate")      var region: Date? = null,
    @SerializedName("successor")      var nextEpisode: String? = null,
    @SerializedName("director")      var director: String? = null,
    @SerializedName("characters")   var characters: ArrayList<String?>? = null




    //@SerializedName("message")      var messageErr: String? = null
    //var type: String? = null,
    //var title: String? = null,
    //@SerializedName("viewer_count")      var viewerCount: String? = null,
    //@SerializedName("thumbnail_url")      var thumbnailUrl: String? = null




)