package app.skeleton.data.db.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FooDao {

    @Insert
    suspend fun insert(fooEntity: FooEntity): Long

    @Query("SELECT * FROM optimal_route WHERE id = :id")
    suspend fun getRouteById(id: Int): FooEntity?

    @Delete
    suspend fun delete(fooEntity: FooEntity)

    @Query("DELETE FROM optimal_route WHERE id = :id")
    suspend fun deleteById(id: Int): Int

    @Query("DELETE FROM optimal_route")
    suspend fun clearAllRoutes()

    @Query("SELECT * FROM optimal_route")
    suspend fun getAll(): List<FooEntity>

    @Query("SELECT * FROM optimal_route")
    fun getAllRoutesFlow(): Flow<List<FooEntity>>
}