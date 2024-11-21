package com.shub39.plumbus.info.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<PlumbusDb> {
        val os = System.getProperty("os.name").lowercase()
        val useHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "Plumbus")
            os.contains("mac") -> File(useHome, "Library/Application Support/Plumbus")
            else -> File(useHome, ".local/share/Plumbus")
        }
        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }
        val dbFile = File(appDataDir, PlumbusDb.DATABASE_NAME)

        return Room.databaseBuilder(dbFile.absolutePath)
    }
}