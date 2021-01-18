package model

import de.thkoeln.inf.gpm.vgb.model.external.Disease
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction


class DiseaseDao(id: EntityID<Long>) : LongEntity(id) {
    private var category by Diseases.category
    private var description by Diseases.description

    companion object : LongEntityClass<DiseaseDao>(Diseases) {
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

        fun delete(id: Long) = Diseases.deleteWhere { Diseases.id eq id }

        fun findAll(): List<Disease> = all().map { it.toDisease() }
    }

    private fun update(disease: Disease) {
        this.category = disease.category
        this.description = disease.description
    }

    fun toDisease() = Disease(id.value, category, description)
}


object Diseases : LongIdTable() {
    val category = long("kategorie")
    val description = text("beschreibung")
}