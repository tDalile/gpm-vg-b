package de.thkoeln.inf.gpm.vgb.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction

class Customer(
        val id: Int?,
        val entry: String,
        val insurantId: Int?
)

class CustomerDao(id: EntityID<Int>) : IntEntity(id) {
    private var entry by Customers.entry
    private var insurantId by Customers.insurantId // InsurantDao referencedOn Customers.insurant

    companion object : IntEntityClass<CustomerDao>(Customers) {
        /**
         * Update or create [Customer] in database
         */
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
    }

    private fun update(customer: Customer) {
        this.entry = customer.entry
        this.insurantId = customer.insurantId
    }

    fun toCustomer() = Customer(id.value, entry, insurantId)

    fun setInsurantId(insurant: Int) = transaction {
        insurantId = insurant
    }
}

object Customers : IntIdTable() {
    val entry = varchar("eintrittdatum", 50)
    val insurantId = integer("versicherterId").nullable()
}
