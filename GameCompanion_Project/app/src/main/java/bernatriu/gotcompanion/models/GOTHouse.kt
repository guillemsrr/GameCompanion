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


)
data class GOTHouseSearch(
    var message: String? = null,
    var data: GOTHouse? = null
)