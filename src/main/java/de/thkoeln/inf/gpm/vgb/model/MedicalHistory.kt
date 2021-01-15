package de.thkoeln.inf.gpm.vgb.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction

class MedicalHistory(val id: Int?)


class MedicalHistoryDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MedicalHistoryDao>(MedicalHistories) {
        /**
         * Update or create [MedicalHistory] in database
         */
        fun save(medicalHistory: MedicalHistory): MedicalHistory = transaction {
            if (medicalHistory.id != null) return@transaction medicalHistory
            new { }.toMedicalHistory()
        }
    }

    fun toMedicalHistory() = MedicalHistory(id.value)
}

object MedicalHistories : IntIdTable()