package rs.raf.projekat2.luka_petrovic_rn3318.data.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by Qwerasdzxc on 4.6.21.
 */
data class SavedRecipe(
    val recipe_id: String,
    val title: String,
    val publisher: String,
    val category: String,
    val image_url: String,
    val imagePath: String?,
    val date: Date,
    val ingredients: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        Date(parcel.readLong()),
        parcel.createStringArrayList()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(recipe_id)
        parcel.writeString(title)
        parcel.writeString(publisher)
        parcel.writeString(category)
        parcel.writeString(image_url)
        parcel.writeString(imagePath)
        parcel.writeLong(date.time)
        parcel.writeStringList(ingredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SavedRecipe> {
        override fun createFromParcel(parcel: Parcel): SavedRecipe {
            return SavedRecipe(parcel)
        }

        override fun newArray(size: Int): Array<SavedRecipe?> {
            return arrayOfNulls(size)
        }
    }
}