package model

import de.thkoeln.inf.gpm.vgb.model.Disease
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction


class DiseaseDao(id: EntityID<Int>) : IntEntity(id) {
    private var category by Diseases.category
    private var description by Diseases.description

    companion object : IntEntityClass<DiseaseDao>(Diseases) {
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

        fun delete(id: Int) = Diseases.deleteWhere { Diseases.id eq id }

        fun findAll(): List<Disease> = all().map { it.toDisease() }
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