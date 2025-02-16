package app.skeleton.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<FooDatabase> {
  val appContext = ctx.applicationContext
  val dbFile = appContext.getDatabasePath(DATABASE_NAME)
  return Room.databaseBuilder<FooDatabase>(
    context = appContext,
    name = dbFile.absolutePath
  )
}