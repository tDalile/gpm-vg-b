package model

import de.thkoeln.inf.gpm.vgb.model.*
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction


class InsurancePolicyDao(id: EntityID<Long>) : LongEntity(id) {
    var isNewCustomer by InsurancePolicies.isNewCustomer
    var riskSurcharge by InsurancePolicies.riskSurcharge
    var riskSurchargeReason by InsurancePolicies.riskSurchargeReason
    var monthlyContribution by InsurancePolicies.monthlyContribution
    var initialContributionAmount by InsurancePolicies.initialContributionAmount
    var startOfContract by InsurancePolicies.startOfContract
    var isActive by InsurancePolicies.isActive
    var insurant by InsurantDao referencedOn InsurancePolicies.insurant
    var isPremiumTariff by InsurancePolicies.isPremiumTariff
    var medicalHistory by MedicalHistoryDao referencedOn InsurancePolicies.medicalHistory

    companion object : LongEntityClass<InsurancePolicyDao>(InsurancePolicies) {
        fun save(insurancePolicy: InsurancePolicy): InsurancePolicy? = transaction {
            val insurant = InsurantDao.save(insurancePolicy.insurant) ?: return@transaction null
            val insurantDao = InsurantDao.findById(insurant.id!!) ?: return@transaction null

            val medicalHistory = MedicalHistoryDao.save(insurancePolicy.medicalHistory)
            val medicalHistoryDao = MedicalHistoryDao.findById(medicalHistory.id!!) ?: return@transaction null

            val newInsurancePolicy = if (insurancePolicy.id == null) {
                new { update(insurancePolicy, insurantDao, medicalHistoryDao) }
            } else {
                val old = findById(insurancePolicy.id)
                old?.update(insurancePolicy, insurantDao, medicalHistoryDao)
                findById(insurancePolicy.id)
            }

            newInsurancePolicy?.toInsurancePolicy()
        }

        fun delete(id: Long) = InsurancePolicies.deleteWhere { InsurancePolicies.id eq id }

        fun findAll(): List<InsurancePolicy> = InsurancePolicyDao.all().map { it.toInsurancePolicy() }
    }

    private fun update(
        insurancePolicy: InsurancePolicy,
        insurantDao: InsurantDao,
        medicalHistoryDao: MedicalHistoryDao
    ) {
        this.isNewCustomer = insurancePolicy.newCustomer
        this.riskSurcharge = insurancePolicy.riskSurcharge
        this.riskSurchargeReason = insurancePolicy.riskSurchargeReason
        this.monthlyContribution = insurancePolicy.monthlyContribution
        this.initialContributionAmount = insurancePolicy.initialContributionAmount
        this.startOfContract = insurancePolicy.startOfContract
        this.isActive = insurancePolicy.active
        this.isPremiumTariff = insurancePolicy.premiumTariff
        this.insurant = insurantDao
        this.medicalHistory = medicalHistoryDao
    }

    fun toInsurancePolicy() = InsurancePolicy(
        id.value,
        isNewCustomer,
        riskSurcharge,
        riskSurchargeReason,
        monthlyContribution,
        initialContributionAmount,
        startOfContract,
        isPremiumTariff,
        isActive,
        insurant.toInsurant(),
        medicalHistory.toMedicalHistory()
    )
}

object InsurancePolicies : LongIdTable() {
    val isNewCustomer = bool("neukunde")
    val riskSurcharge = double("risikozuschlag").nullable()
    val riskSurchargeReason = text("risikozuschlagsbegruendung").nullable()
    val monthlyContribution = double("monatlicher_beitrag")
    val initialContributionAmount = double("initiale_beitragshoehe")
    val startOfContract = text("vertragsbeginn")
    val isPremiumTariff = bool("premium_tarif")
    val isActive = bool("police_ist_aktiv")
    val insurant = reference("versicherter_id", Insurants)
    val medicalHistory = reference("krankenhistorie_id", MedicalHistories)
}
