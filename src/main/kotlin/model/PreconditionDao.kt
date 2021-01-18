package model

import de.thkoeln.inf.gpm.vgb.model.external.Precondition
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction


class PreconditionDao(id: EntityID<Long>) : LongEntity(id) {
    private var medicalHistory by MedicalHistoryDao referencedOn Preconditions.medicalHistory
    private var disease by DiseaseDao referencedOn Preconditions.disease

    companion object : LongEntityClass<PreconditionDao>(Preconditions) {
        fun save(precondition: Precondition): Precondition? = transaction {
            val medicalHistory = MedicalHistoryDao.save(precondition.medicalHistory)
            val medicalHistoryDao = MedicalHistoryDao.findById(medicalHistory.id!!) ?: return@transaction null
            val disease = DiseaseDao.save(precondition.disease) ?: return@transaction null
            val diseaseDao = DiseaseDao.findById(disease.id!!) ?: return@transaction null

            val newPrecondition = if (precondition.id == null) {
                new { update(medicalHistoryDao, diseaseDao) }
            } else {
                val old = findById(precondition.id)
                old?.update(medicalHistoryDao, diseaseDao)
                findById(precondition.id)
            }

            newPrecondition?.toPrecondition()
        }

        fun delete(id: Long) = Preconditions.deleteWhere { Preconditions.id eq id }

        fun findAll(): List<Precondition> = PreconditionDao.all().map { it.toPrecondition() }
    }

    fun toPrecondition() = Precondition(id.value, medicalHistory.toMedicalHistory(), disease.toDisease())

    private fun update(medicalHistoryDao: MedicalHistoryDao, diseaseDao: DiseaseDao) {
        this.medicalHistory = medicalHistoryDao
        this.disease = diseaseDao
    }
}

object Preconditions : LongIdTable() {
    val medicalHistory = reference("krankenhistorie", MedicalHistories)
    val disease = reference("Erkrankung", Diseases)
}