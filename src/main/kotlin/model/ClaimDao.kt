package model

import de.thkoeln.inf.gpm.vgb.model.Claim
import de.thkoeln.inf.gpm.vgb.model.MedicalHistory
import de.thkoeln.inf.gpm.vgb.model.Precondition
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction


class ClaimDao(id: EntityID<Int>) : IntEntity(id) {
    private var claimDate by Claims.claimDate
    private var bmi by Claims.bmi
    private var riskFactorAge by Claims.riskFactorAge
    private var riskFactorBmi by Claims.riskFactorBmi
    private var riskFactorMedicalHistory by Claims.riskFactorMedicalHistory
    private var isInsurable by Claims.isInsurable
    private var insurancePolicy by InsurancePolicyDao optionalReferencedOn Claims.insurancePolicy
    private var medicalHistory by MedicalHistoryDao optionalReferencedOn Claims.medicalHistory

    companion object : IntEntityClass<ClaimDao>(Claims) {
        fun save(claim: Claim): Claim? = transaction {
            val insurancePolicyDao = claim.insurancePolicy?.id?.let { InsurancePolicyDao.findById(it) }
            val medicalHistoryDao = claim.medicalHistory?.id?.let { MedicalHistoryDao.findById(it) }

            val newClaim = if (claim.id == null) {
                new { update(claim, insurancePolicyDao, medicalHistoryDao) }
            } else {
                val old = findById(claim.id)
                old?.update(claim, insurancePolicyDao, medicalHistoryDao)
                findById(claim.id)
            }

            newClaim?.toClaim()
        }

        fun delete(id: Int) = Claims.deleteWhere { Claims.id eq id }

        fun findAll(): List<Claim> = ClaimDao.all().map { it.toClaim() }
    }

    private fun update(
        claim: Claim,
        insurancePolicyDao: InsurancePolicyDao?,
        medicalHistoryDao: MedicalHistoryDao?
    ) {
        this.claimDate = claim.claimDate
        this.bmi = claim.bmi
        this.riskFactorAge = claim.riskFactorAge
        this.riskFactorBmi = claim.riskFactorBmi
        this.riskFactorMedicalHistory = claim.riskFactorMedicalHistory
        this.isInsurable = claim.insurable
        this.insurancePolicy = insurancePolicyDao
        this.medicalHistory = medicalHistoryDao
    }

    fun toClaim() = Claim(
        id.value,
        claimDate,
        bmi,
        riskFactorAge,
        riskFactorBmi,
        riskFactorMedicalHistory,
        isInsurable,
        insurancePolicy?.toInsurancePolicy(),
        medicalHistory?.toMedicalHistory()
    )
}

object Claims : IntIdTable() {
    val claimDate = text("antragsdatum")
    val bmi = double("bmi")
    val riskFactorAge = long("risikofaktor_alter").nullable()
    val riskFactorBmi = long("risikofaktor_bmi").nullable()
    val riskFactorMedicalHistory = long("risikofaktor_krankenhistorie").nullable()
    val isInsurable = bool("versicherungsfaehig").nullable()
    val insurancePolicy = reference("versicherungspolice_id", InsurancePolicies).nullable()
    val medicalHistory = reference("krankenhistorie_id", MedicalHistories).nullable()
}