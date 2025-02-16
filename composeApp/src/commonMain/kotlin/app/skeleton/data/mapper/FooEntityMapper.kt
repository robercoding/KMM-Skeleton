package app.skeleton.data.mapper

import app.skeleton.data.db.models.FooEntity
import app.skeleton.data.db.models.FooItemEntity
import app.skeleton.domain.models.FooDomain
import app.skeleton.domain.models.FooItemDomain

fun FooEntity.toDomain() = FooDomain(
    id = id,
    list = list.map { it.toDomain() },
    distance = distance
)

fun FooItemEntity.toDomain() = FooItemDomain(
    text = text,
    number = otherItem
)

fun FooDomain.toEntity() = FooEntity(
    id = id,
    list = list.map { it.toEntity() },
    distance = distance
)

fun FooItemDomain.toEntity() = FooItemEntity(
    text = text,
    otherItem = number
)