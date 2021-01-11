package de.thkoeln.inf.gpm.vgb.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction

object Addresses : IntIdTable() {
    val street = text("Strasse")
    val houseNumber = text("Hausnummer")
    val location = reference("Ort", Locations)
}

class AddressDao(id: EntityID<Int>) : IntEntity(id) {
    private var street by Addresses.street
    private var houseNumber by Addresses.houseNumber
    private var location by LocationDao referencedOn Addresses.location

    companion object : IntEntityClass<AddressDao>(Addresses) {
        /**
         * Update or create [Address] in database
         */
        fun save(address: Address) : Address? = transaction {
            val location = LocationDao.save(address.location) ?: return@transaction null
            val locationDao = LocationDao.findById(location.id!!) ?: return@transaction null
            val newAddress = if (address.id == null) {
                new { update(address, locationDao) }
            } else {
                val old = findById(address.id)
                old?.update(address, locationDao)
                findById(address.id)
            }

            newAddress?.toAddress()
        }
    }

    private fun update(address: Address, locationDao: LocationDao) {
        this.street = address.street
        this.houseNumber = address.houseNumber
        this.location = locationDao
    }

    fun toAddress() = Address(id.value, street, houseNumber, location.toLocation())
}

class Address(
    val id: Int?,
    val street: String,
    val houseNumber: String,
    val location: Location
)