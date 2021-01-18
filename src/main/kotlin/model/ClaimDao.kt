package model

import de.thkoeln.inf.gpm.vgb.model.Claim
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction


class ClaimDao(id: EntityID<Long>) : LongEntity(id) {
    private var claimDate by Claims.claimDate
    private var desiredStartDate by Claims.desiredStartDate
    private var bmi by Claims.bmi
    private var riskFactorAge by Claims.riskFactorAge
    private var riskFactorBmi by Claims.riskFactorBmi
    private var riskFactorMedicalHistory by Claims.riskFactorMedicalHistory
    private var isInsurable by Claims.isInsurable
    private var insurant by InsurantDao referencedOn Claims.insurant
    private var insurancePolicy by InsurancePolicyDao optionalReferencedOn Claims.insurancePolicy
    private var medicalHistory by MedicalHistoryDao optionalReferencedOn Claims.medicalHistory

    companion object : LongEntityClass<ClaimDao>(Claims) {
        fun save(claim: Claim): Claim? = transaction {
            val insurant = InsurantDao.save(claim.insurant) ?: return@transaction null
            val insurantDao = InsurantDao.findById(insurant.id!!) ?: return@transaction null

            val insurancePolicyDao = claim.insurancePolicy?.id?.let { InsurancePolicyDao.findById(it) }
            val medicalHistoryDao = claim.medicalHistory?.id?.let { MedicalHistoryDao.findById(it) }

            val newClaim = if (claim.id == null) {
                new { update(claim, insurantDao, insurancePolicyDao, medicalHistoryDao) }
            } else {
                val old = findById(claim.id)
                old?.update(claim, insurantDao, insurancePolicyDao, medicalHistoryDao)
                findById(claim.id)
            }

            newClaim?.toClaim()
        }

        fun delete(id: Long) = Claims.deleteWhere { Claims.id eq id }

        fun findAll(): List<Claim> = ClaimDao.all().map { it.toClaim() }
    }

    private fun update(
        claim: Claim,
        insurantDao: InsurantDao,
        insurancePolicyDao: InsurancePolicyDao?,
        medicalHistoryDao: MedicalHistoryDao?
    ) {
        this.claimDate = claim.claimDate
        this.desiredStartDate = claim.desiredStartDate
        this.bmi = claim.bmi
        this.riskFactorAge = claim.riskFactorAge
        this.riskFactorBmi = claim.riskFactorBmi
        this.riskFactorMedicalHistory = claim.riskFactorMedicalHistory
        this.isInsurable = claim.insurable
        this.insurant = insurantDao
        this.insurancePolicy = insurancePolicyDao
        this.medicalHistory = medicalHistoryDao
    }

    fun toClaim() = Claim(
        id.value,
        claimDate,
        desiredStartDate,
        bmi,
        riskFactorAge,
        riskFactorBmi,
        riskFactorMedicalHistory,
        isInsurable,
        insurant.toInsurant(),
        insurancePolicy?.toInsurancePolicy(),
        medicalHistory?.toMedicalHistory()
    )
}

object Claims : LongIdTable() {
    val claimDate = text("antragsdatum")
    val desiredStartDate = text("gewuenschtes_anfangs_datum")
    val bmi = double("bmi")
    val riskFactorAge = long("risikofaktor_alter").nullable()
    val riskFactorBmi = long("risikofaktor_bmi").nullable()
    val riskFactorMedicalHistory = long("risikofaktor_krankenhistorie").nullable()
    val isInsurable = bool("versicherungsfaehig").nullable()
    val insurant = reference("versicherter_id", Insurants)
    val insurancePolicy = reference("versicherungspolice_id", InsurancePolicies).nullable()
    val medicalHistory = reference("krankenhistorie_id", MedicalHistories).nullable()
}