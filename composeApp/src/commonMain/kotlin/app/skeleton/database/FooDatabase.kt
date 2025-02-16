package app.skeleton.database

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.SQLiteDriver
import app.skeleton.data.db.converter.FooItemListConverter
import app.skeleton.data.db.models.FooDao
import app.skeleton.data.db.models.FooEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
  entities = [FooEntity::class],
  version = 1,
  autoMigrations = [],
  exportSchema = true,
)
@TypeConverters(FooItemListConverter::class)
@ConstructedBy(FooDatabaseConstructor::class)
abstract class FooDatabase : RoomDatabase() {
  abstract fun getFooDao(): FooDao
}


// The Room compiler generates the `actual` implementations. https://developer.android.com/kotlin/multiplatform/room#defining-database
@Suppress("NO_ACTUAL_FOR_EXPECT", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object FooDatabaseConstructor : RoomDatabaseConstructor<FooDatabase> {
  override fun initialize(): FooDatabase
}

// We want to inject AndroidSQLDriver from android source set to be able to open IDE Database Inspector feature.
// With BundledSQLDriver the Database looks closed on DatabaseInspector.
// expect fun getRoomDatabasePlatform(builder: RoomDatabase.Builder<RouteDatabase>): RouteDatabase
fun getRoomDatabase(
  builder: RoomDatabase.Builder<FooDatabase>,
  sqLiteDriver: SQLiteDriver,
): FooDatabase {
  return builder
    .setDriver(sqLiteDriver)
    .setQueryCoroutineContext(Dispatchers.IO)
    .build()
}

const val DATABASE_NAME = "foo.db"