package de.thkoeln.inf.gpm.vgb.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class Location(val id: Int? = null, val zipCode: String, val name: String)

class LocationDao(id: EntityID<Int>) : IntEntity(id) {
    private var zipCode by Locations.zipCode
    private var name by Locations.name

    companion object : IntEntityClass<LocationDao>(Locations) {
        /**
         * Update or create [Location] in database
         */
        fun save(location: Location) : Location? = transaction {
            val newLocation = if (location.id == null) {
                new { update(location) }
            } else {
                val old = LocationDao.findById(location.id)
                old?.update(location)
                LocationDao.findById(location.id)
            }

            newLocation?.toLocation()
        }
    }

    private fun update(location: Location) {
        this.zipCode = location.zipCode
        this.name = location.name
    }

    fun toLocation() = Location(id.value, zipCode, name)
}

object Locations : IntIdTable() {
    val zipCode = varchar("plz", 5)
    val name = varchar("name", 255)
}