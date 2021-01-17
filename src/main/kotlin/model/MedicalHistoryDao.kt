package model

import de.thkoeln.inf.gpm.vgb.model.MedicalHistory
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction


class MedicalHistoryDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<MedicalHistoryDao>(MedicalHistories) {
        /**
         * Update or create [MedicalHistory] in database
         */
        fun save(medicalHistory: MedicalHistory): MedicalHistory = transaction {
            if (medicalHistory.id != null) return@transaction medicalHistory
            new { }.toMedicalHistory()
        }

        fun delete(id: Long) = MedicalHistories.deleteWhere { MedicalHistories.id eq id }

        fun findAll(): List<MedicalHistory> = MedicalHistoryDao.all().map { it.toMedicalHistory() }
    }

    fun toMedicalHistory() = MedicalHistory(id.value)
}

object MedicalHistories : LongIdTable()