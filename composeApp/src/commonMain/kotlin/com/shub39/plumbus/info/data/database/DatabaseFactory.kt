package com.shub39.plumbus.info.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<PlumbusDb>
}