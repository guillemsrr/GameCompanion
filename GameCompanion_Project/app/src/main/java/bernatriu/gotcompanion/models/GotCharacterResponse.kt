package bernatriu.gotcompanion.models

import com.google.gson.annotations.SerializedName

data class GOTCharacter (
    @SerializedName("name") var  characterName : String? = null,
    @SerializedName("culture")    var culture: String? = null,
    @SerializedName("actor")      var actor: String? = null
    //var type: String? = null,
    //var title: String? = null,
    //@SerializedName("viewer_count")      var viewerCount: String? = null,
    //@SerializedName("thumbnail_url")      var thumbnailUrl: String? = null




){

    //fun getImageUrl(): String? {
    //    return thumbnailUrl?.replace("{width}x{height}","500x500")
    //}
}


data class GotCharacterResponse(
    var data: ArrayList<GOTCharacter>? = null
)