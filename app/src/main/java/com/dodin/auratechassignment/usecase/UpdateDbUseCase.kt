package com.dodin.auratechassignment.usecase

import com.dodin.auratechassignment.data.repository.RebootEventsRepository

class UpdateDbUseCase(
    private val repository: RebootEventsRepository
) {

    suspend operator fun invoke() {
        repository.addEvent()
    }
}
