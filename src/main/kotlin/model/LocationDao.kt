package model

import de.thkoeln.inf.gpm.vgb.model.Location
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

class LocationDao(id: EntityID<Long>) : LongEntity(id) {
    private var zipCode by Locations.zipCode
    private var name by Locations.name

    companion object : LongEntityClass<LocationDao>(Locations) {
        fun save(location: Location): Location? = transaction {
            val newLocation = if (location.id == null) {
                new { update(location) }
            } else {
                val old = LocationDao.findById(location.id)
                old?.update(location)
                LocationDao.findById(location.id)
            }

            newLocation?.toLocation()
        }

        fun delete(id: Long) = Locations.deleteWhere { Locations.id eq id }

        fun findAll(): List<Location> = LocationDao.all().map { it.toLocation() }
    }

    private fun update(location: Location) {
        this.zipCode = location.zipCode
        this.name = location.name
    }

    fun toLocation() = Location(id.value, zipCode, name)
}

object Locations : LongIdTable() {
    val zipCode = varchar("plz", 5)
    val name = varchar("name", 255)
}