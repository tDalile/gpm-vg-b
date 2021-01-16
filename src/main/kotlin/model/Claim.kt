package de.thkoeln.inf.gpm.vgb.model

import model.*
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction


class Claim(
    val id: Int?,
    val claimDate: String, // TODO brauchen wir nicht RIP
    val bmi: Double,
    val riskFactorAge: Long?,
    val riskFactorBmi: Long?,
    val riskFactorMedicalHistory: Long?,
    val claimClassification: ClaimClassification?,
    val insurancePolicy: InsurancePolicy?,
    val medicalHistory: MedicalHistory?
)

class ClaimDao(id: EntityID<Int>) : IntEntity(id) {
    private var claimDate by Claims.claimDate
    private var bmi by Claims.bmi
    private var riskFactorAge by Claims.riskFactorAge
    private var riskFactorBmi by Claims.riskFactorBmi
    private var riskFactorMedicalHistory by Claims.riskFactorMedicalHistory
    private var claimClassification by ClaimClassificationDao optionalReferencedOn Claims.claimClassification
    private var insurancePolicy by InsurancePolicyDao optionalReferencedOn Claims.insurancePolicy
    private var medicalHistory by MedicalHistoryDao optionalReferencedOn Claims.medicalHistory

    companion object : IntEntityClass<ClaimDao>(Claims) {
        /**
         * Update or create [Address] in database
         */
        fun save(claim: Claim): Claim? = transaction {
            val claimClassificationDao = claim.claimClassification?.id?.let { ClaimClassificationDao.findById(it) }
            val insurancePolicyDao = claim.insurancePolicy?.id?.let { InsurancePolicyDao.findById(it) }
            val medicalHistoryDao = claim.medicalHistory?.id?.let { MedicalHistoryDao.findById(it) }

            val newClaim = if (claim.id == null) {
                new { update(claim, claimClassificationDao, insurancePolicyDao, medicalHistoryDao) }
            } else {
                val old = findById(claim.id)
                old?.update(claim, claimClassificationDao, insurancePolicyDao, medicalHistoryDao)
                findById(claim.id)
            }

            newClaim?.toClaim()
        }
    }

    private fun update(
        claim: Claim,
        claimClassificationDao: ClaimClassificationDao?,
        insurancePolicyDao: InsurancePolicyDao?,
        medicalHistoryDao: MedicalHistoryDao?
    ) {
        this.claimDate = claim.claimDate
        this.bmi = claim.bmi
        this.riskFactorAge = claim.riskFactorAge
        this.riskFactorBmi = claim.riskFactorBmi
        this.riskFactorMedicalHistory = claim.riskFactorMedicalHistory
        this.claimClassification = claimClassificationDao
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
            claimClassification?.toClaimClassification(),
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
    val claimClassification = reference("antragsklassifizierung", ClaimClassifications).nullable()
    val insurancePolicy = reference("versicherungspolice_id", InsurancePolicies).nullable()
    val medicalHistory = reference("krankenhistorie_id", MedicalHistories).nullable()
}