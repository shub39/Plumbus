package com.shub39.plumbus.info.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DbConstructor: RoomDatabaseConstructor<PlumbusDb> {
    override fun initialize(): PlumbusDb
}