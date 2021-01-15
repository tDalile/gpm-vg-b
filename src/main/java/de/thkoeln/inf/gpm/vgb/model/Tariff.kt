package de.thkoeln.inf.gpm.vgb.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction

class Tariff(val id: Int? = null, val description: String)

class TariffDao(id: EntityID<Int>) : IntEntity(id) {
    private var description by Tariffs.description

    companion object : IntEntityClass<TariffDao>(Tariffs) {
        fun save(tariff: Tariff): Tariff? = transaction {
            val newTariff = if (tariff.id == null) {
                new { update(tariff) }
            } else {
                val old = findById(tariff.id)
                old?.update(tariff)
                findById(tariff.id)
            }

            newTariff?.toTariff()
        }
    }

    private fun update(tariff: Tariff) {
        this.description = tariff.description
    }

    fun toTariff() = Tariff(id.value, description)
}

object Tariffs : IntIdTable() {
    val description = text("beschreibung")
}