package com.dodin.auratechassignment.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RebootEventEntity(
    @PrimaryKey val id: Int,
    val timestamp: Long,
)
