package de.thkoeln.inf.gpm.vgb.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction


object ClaimClassifications : IntIdTable() {
    val description = text("beschreibung")
}

class ClaimClassificationDao(id: EntityID<Int>) : IntEntity(id) {
    private var description by ClaimClassifications.description

    companion object : IntEntityClass<ClaimClassificationDao>(ClaimClassifications) {
        fun save(claimClassification: ClaimClassification) : ClaimClassification? = transaction {
            val newClaimClassification = if (claimClassification.id == null) {
                new { update(claimClassification) }
            } else {
                val old = findById(claimClassification.id)
                old?.update(claimClassification)
                findById(claimClassification.id)
            }

            newClaimClassification?.toClaimClassification()
        }
    }

    private fun update(claimClassification: ClaimClassification) {
        this.description = claimClassification.description
    }

    fun toClaimClassification() = ClaimClassification(id.value, description)
}

class ClaimClassification(val id: Int? = null, val description: String)