package rs.raf.projekat2.luka_petrovic_rn3318.data.datasources.local

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Qwerasdzxc on 5.6.21.
 */
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}