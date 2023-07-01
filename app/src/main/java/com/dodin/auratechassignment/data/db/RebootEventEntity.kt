package com.dodin.auratechassignment.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RebootEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Long,
)
