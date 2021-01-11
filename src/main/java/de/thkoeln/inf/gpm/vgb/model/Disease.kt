package de.thkoeln.inf.gpm.vgb.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction


class Disease(
    val id: Int?,
    val category: Int,
    val description: String

)


class DiseaseDao(id: EntityID<Int>) : IntEntity(id) {
    private var category by Diseases.category
    private var description by Diseases.description

    companion object : IntEntityClass<DiseaseDao>(Diseases) {
        /**
         * Update or create [Disease] in database
         */
        fun save(disease: Disease): Disease? = transaction {
            val newDisease = if (disease.id == null) {
                new { update(disease) }
            } else {
                val old = DiseaseDao.findById(disease.id)
                old?.update(disease)
                DiseaseDao.findById(disease.id)
            }

            newDisease?.toDisease()
        }
    }

    private fun update(disease: Disease) {
        this.category = disease.category
        this.description = disease.description
    }

    fun toDisease() = Disease(id.value, category, description)
}


object Diseases : IntIdTable() {
    val category = integer("kategorie")
    val description = text("beschreibung")
}