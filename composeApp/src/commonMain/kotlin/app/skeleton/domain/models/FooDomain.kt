package app.skeleton.domain.models

data class FooDomain(
    val id: Int = 0,
    val list: List<FooItemDomain>,
    val distance: Double,
)