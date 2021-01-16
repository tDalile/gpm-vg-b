package model

import model.Disease
import model.DiseaseDao
import model.Diseases
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction

class Precondition(val id: Int?, val medicalHistory: MedicalHistory, val disease: Disease)

class PreconditionDao(id: EntityID<Int>) : IntEntity(id) {
    private var medicalHistory by MedicalHistoryDao referencedOn Preconditions.medicalHistory
    private var disease by DiseaseDao referencedOn Preconditions.disease

    companion object : IntEntityClass<PreconditionDao>(Preconditions) {
        /**
         * Update or create [Address] in database
         */
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
    }

    private fun toPrecondition() = Precondition(id.value, medicalHistory.toMedicalHistory(), disease.toDisease())

    private fun update(medicalHistoryDao: MedicalHistoryDao, diseaseDao: DiseaseDao) {
        this.medicalHistory = medicalHistoryDao
        this.disease = diseaseDao
    }
}

object Preconditions : IntIdTable() {
    val medicalHistory = reference("krankenhistorie", MedicalHistories)
    val disease = reference("Erkrankung", Diseases)
}