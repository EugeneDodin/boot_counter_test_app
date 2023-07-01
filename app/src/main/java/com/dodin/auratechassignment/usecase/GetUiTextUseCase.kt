package com.dodin.auratechassignment.usecase

import com.dodin.auratechassignment.data.repository.RebootEventsRepository

class GetUiTextUseCase(
    private val repo: RebootEventsRepository,
) {
    suspend operator fun invoke(): String {
        val events = repo.getRebootEvents()
        return when (events.size) {
            0 -> "No boots detected"
            else -> {
                events.joinToString("\n") { rebootEntity ->
                    "${rebootEntity.id} - ${rebootEntity.timestamp}"
                }
            }
        }
    }
}
