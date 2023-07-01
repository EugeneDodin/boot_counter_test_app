package com.dodin.auratechassignment.usecase

import com.dodin.auratechassignment.data.repository.RebootEventsRepository

class GetNotificationTextUseCase(
    private val repo: RebootEventsRepository,
) {
    suspend operator fun invoke(): String {
        val events = repo.getRebootEvents()
        return when (events.size) {
            0 -> "No boots detected"
            1 -> {
                val timestamp = events.first().timestamp
                "The boot was detected with timestamp = $timestamp"
            }
            else -> {
                val lastTimestamp = events.last().timestamp
                val previousTimestamp = events[events.lastIndex -1].timestamp
                val delta = lastTimestamp - previousTimestamp
                "Last boots time delta = ${delta}"
            }
        }
    }
}
