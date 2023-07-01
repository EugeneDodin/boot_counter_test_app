package com.dodin.auratechassignment.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RebootDao {
    @Insert
    suspend fun insert(eventEntity: RebootEventEntity)

    @Query("SELECT * FROM rebootevententity WHERE timestamp >= :lastNotificationShownTimestamp")
    suspend fun findEvents(lastNotificationShownTimestamp: Long): List<RebootEventEntity>
}