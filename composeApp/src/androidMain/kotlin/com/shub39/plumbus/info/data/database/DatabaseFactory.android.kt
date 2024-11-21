package com.shub39.plumbus.info.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<PlumbusDb> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(PlumbusDb.DATABASE_NAME)

        return Room.databaseBuilder(appContext, dbFile.absolutePath)
    }
}