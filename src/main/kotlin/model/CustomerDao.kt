package model

import de.thkoeln.inf.gpm.vgb.model.external.Customer
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

class CustomerDao(id: EntityID<Long>) : LongEntity(id) {
    private var entry by Customers.entry
    private var password by Customers.password
    private var insurantId by Customers.insurantId // InsurantDao referencedOn Customers.insurant

    companion object : LongEntityClass<CustomerDao>(Customers) {
        fun save(customer: Customer): Customer? = transaction {
            val newCustomer = if (customer.id == null) {
                new { update(customer) }
            } else {
                val old = findById(customer.id)
                old?.update(customer)
                findById(customer.id)
            }

            newCustomer?.toCustomer()
        }

        fun delete(id: Long) = Customers.deleteWhere { Customers.id eq id }

        fun findAll(): List<Customer> = CustomerDao.all().map { it.toCustomer() }
    }

    private fun update(customer: Customer) {
        this.entry = customer.entry
        this.password = customer.password
        this.insurantId = customer.insurantId
    }

    fun toCustomer() = Customer(id.value, entry, password, insurantId)

    fun setInsurantId(insurant: Long) = transaction {
        insurantId = insurant
    }
}

object Customers : LongIdTable() {
    val entry = text("eintrittdatum")
    val password = text("passwort")
    val insurantId = long("versicherterId").nullable()
}
