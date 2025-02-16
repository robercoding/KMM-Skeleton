package app.skeleton.data.db.converter

import androidx.room.TypeConverter
import app.skeleton.data.db.models.FooItemEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FooItemListConverter {
    @TypeConverter
    fun fromStringList(value: List<FooItemEntity>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<FooItemEntity> {
        return try {
            Json.decodeFromString(value)
        } catch (e: Exception) {
            e.printStackTrace()
            listOf(
                FooItemEntity(
                    text = "asd",
                    otherItem = 2.0,
                ),
            )
        }
    }
}