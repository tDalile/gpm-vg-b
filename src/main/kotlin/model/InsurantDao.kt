package model

import de.thkoeln.inf.gpm.vgb.model.Insurant
import model.*
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

class InsurantDao(id: EntityID<Long>) : LongEntity(id) {
    private var name by Insurants.name
    private var firstName by Insurants.firstName
    private var birthdate by Insurants.birthdate
    private var sex by Insurants.sex
    private var size by Insurants.size
    private var weight by Insurants.weight
    private var address by AddressDao referencedOn Insurants.address
    private var customer by CustomerDao referencedOn Insurants.customerId

    companion object : LongEntityClass<InsurantDao>(Insurants) {
        fun save(insurant: Insurant): Insurant? = transaction {
            val address = AddressDao.save(insurant.address) ?: return@transaction null
            val addressDao = AddressDao.findById(address.id!!) ?: return@transaction null
            val customerDao = CustomerDao.findById(insurant.customer.id!!) ?: return@transaction null

            val newInsurant = if (insurant.id == null) {
                new { update(insurant, addressDao, customerDao) }
            } else {
                val old = findById(insurant.id)
                old?.update(insurant, addressDao, customerDao)
                findById(insurant.id)
            }

            // set own customer ref, if needed
            if (customerDao.toCustomer().insurantId == null)
                newInsurant?.let { customerDao.setInsurantId(it.id.value) }

            newInsurant?.toInsurant()
        }

        fun delete(id: Long) = Insurants.deleteWhere { Insurants.id eq id }

        fun findAll(): List<Insurant> = InsurantDao.all().map { it.toInsurant() }
    }

    private fun update(insurant: Insurant, addressDao: AddressDao, customerDao: CustomerDao) {
        this.name = insurant.name
        this.firstName = insurant.firstName
        this.birthdate = insurant.birthdate
        this.sex = insurant.sex
        this.size = insurant.size.toInt()
        this.weight = insurant.weight.toInt()
        this.address = addressDao
        this.customer = customerDao

    }

    fun toInsurant() = Insurant(
            id.value,
            name,
            firstName,
            birthdate,
            sex,
            size.toLong(),
            weight.toLong(),
            address.toAddress(),
            customer.toCustomer()
    )
}

object Insurants : LongIdTable() {
    val name = text("Name")
    val firstName = text("Vorname")
    val birthdate = text("Geburtstag")
    val sex = char("Geschlecht")
    val size = integer("Groesse")
    val weight = integer("Gewicht")
    val address = reference("Adresse", Addresses)
    val customerId = reference("Kundennr", Customers)
}