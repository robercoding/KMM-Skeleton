package app.skeleton.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity("optimal_route")
class FooEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "route_list") val list: List<FooItemEntity>,
    @ColumnInfo(name = "distance") val distance: Double,
)

@Serializable
data class FooItemEntity(
    val text: String,
    val otherItem: Double,
)