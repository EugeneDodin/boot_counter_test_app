package com.dodin.auratechassignment.data.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [RebootEventEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun rebootDao(): RebootDao
}
