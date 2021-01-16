package model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction


class ClaimClassification(val id: Int? = null, val description: String)

class ClaimClassificationDao(id: EntityID<Int>) : IntEntity(id) {
    private var description by ClaimClassifications.description

    companion object : IntEntityClass<ClaimClassificationDao>(ClaimClassifications) {
        fun save(claimClassification: ClaimClassification): ClaimClassification? = transaction {
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

object ClaimClassifications : IntIdTable() {
    val description = text("beschreibung")
}