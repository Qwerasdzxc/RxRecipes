package rs.raf.projekat2.luka_petrovic_rn3318.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Qwerasdzxc on 4.6.21.
 */
data class RecipeDetails(
    val recipe_id: String,
    val title: String,
    val publisher: String,
    val image_url: String,
    val ingredients: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(recipe_id)
        parcel.writeString(title)
        parcel.writeString(publisher)
        parcel.writeString(image_url)
        parcel.writeStringList(ingredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeDetails> {
        override fun createFromParcel(parcel: Parcel): RecipeDetails {
            return RecipeDetails(parcel)
        }

        override fun newArray(size: Int): Array<RecipeDetails?> {
            return arrayOfNulls(size)
        }
    }
}