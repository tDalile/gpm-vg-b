package model

import de.thkoeln.inf.gpm.vgb.model.external.Address
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

class AddressDao(id: EntityID<Long>) : LongEntity(id) {
    private var street by Addresses.street
    private var houseNumber by Addresses.houseNumber
    private var location by LocationDao referencedOn Addresses.location

    companion object : LongEntityClass<AddressDao>(Addresses) {
        fun save(address: Address): Address? = transaction {
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

        fun delete(id: Long) = Addresses.deleteWhere { Addresses.id eq id }

        fun findAll(): List<Address> = AddressDao.all().map { it.toAddress() }
    }

    private fun update(address: Address, locationDao: LocationDao) {
        this.street = address.street
        this.houseNumber = address.houseNumber
        this.location = locationDao
    }

    fun toAddress() = Address(id.value, street, houseNumber, location.toLocation())
}

object Addresses : LongIdTable() {
    val street = text("Strasse")
    val houseNumber = text("Hausnummer")
    val location = reference("Ort", Locations)
}