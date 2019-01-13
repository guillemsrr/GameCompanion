package bernatriu.gotcompanion.models

import com.google.gson.annotations.SerializedName

data class GOTHouse (
    @SerializedName("name") var  houseName : String? = null,
    @SerializedName("currentLord")    var lord: String? = null,
    @SerializedName("words")      var words: String? = null,
    @SerializedName("imageLink")      var image: String? = null,
    @SerializedName("region")      var region: String? = null,
    @SerializedName("overlord")      var overlord: String? = null,
    @SerializedName("coatOfArms")      var coatOfArms: String? = null




    //@SerializedName("message")      var messageErr: String? = null
    //var type: String? = null,
    //var title: String? = null,
    //@SerializedName("viewer_count")      var viewerCount: String? = null,
    //@SerializedName("thumbnail_url")      var thumbnailUrl: String? = null




)