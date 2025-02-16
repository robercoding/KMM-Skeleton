package app.skeleton.domain

import app.skeleton.data.FooRepository
import kotlinx.coroutines.flow.map

class GetAllFooUseCase(private val fooRepository: FooRepository) {
    suspend operator fun invoke() = fooRepository.getAll().map { it } // Filter or map
}