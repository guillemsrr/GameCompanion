package bernatriu.gotcompanion.models

import com.google.gson.annotations.SerializedName

data class GOTCharacter (
    @SerializedName("name") var  name : String? = null,
    @SerializedName("culture")    var culture: String? = null,
    @SerializedName("actor")      var actor: String? = null,
    @SerializedName("imageLink")      var image: String? = null,
    @SerializedName("mother")      var mother: String? = null,
    @SerializedName("father")      var father: String? = null,
    @SerializedName("house")      var house: String? = null,
    @SerializedName("message")      var message: String? = null





    //@SerializedName("message")      var messageErr: String? = null
    //var type: String? = null,
    //var title: String? = null,
    //@SerializedName("viewer_count")      var viewerCount: String? = null,
    //@SerializedName("thumbnail_url")      var thumbnailUrl: String? = null



)


data class GOTCharacterSearch(
    var message: String? = null,
    var data: GOTCharacter? = null
)

//data class GotCharacterResponseObject(
//    var obj: GotCharacterResponse? = null
//)