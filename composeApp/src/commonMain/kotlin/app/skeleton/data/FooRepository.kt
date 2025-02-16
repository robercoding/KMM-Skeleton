package app.skeleton.data

import app.skeleton.data.mapper.toEntity
import app.skeleton.database.FooDatabase
import app.skeleton.domain.models.FooDomain

class FooRepository(private val fooDatabase: FooDatabase) {
    private val fooDao = fooDatabase.getFooDao()

    suspend fun insert(routeDomain: FooDomain) {
        fooDao.insert(routeDomain.toEntity())
    }

    fun getAll() = fooDao.getAllRoutesFlow()

    suspend fun clearAll() = fooDao.clearAllRoutes()

    suspend fun delete(routeId: Int) = fooDao.deleteById(routeId)
}