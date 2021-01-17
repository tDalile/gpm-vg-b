package model

import de.thkoeln.inf.gpm.vgb.model.*
import de.thkoeln.inf.gpm.vgb.model.external.InsurancePolicy
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction


class InsurancePolicyDao(id: EntityID<Int>) : IntEntity(id) {
    var isNewCustomer by InsurancePolicies.isNewCustomer
    var riskSurcharge by InsurancePolicies.riskSurcharge
    var riskSurchargeReason by InsurancePolicies.riskSurchargeReason
    var monthlyContribution by InsurancePolicies.monthlyContribution
    var initialContributionAmount by InsurancePolicies.initialContributionAmount
    var startOfContract by InsurancePolicies.startOfContract
    var customer by CustomerDao referencedOn InsurancePolicies.customer
    var isPremiumTariff by InsurancePolicies.isPremiumTariff
    var medicalHistory by MedicalHistoryDao referencedOn InsurancePolicies.medicalHistory

    companion object : IntEntityClass<InsurancePolicyDao>(InsurancePolicies) {
        fun save(insurancePolicy: InsurancePolicy): InsurancePolicy? = transaction {
            val customer = CustomerDao.save(insurancePolicy.customer) ?: return@transaction null
            val customerDao = CustomerDao.findById(customer.id!!) ?: return@transaction null

            val medicalHistory = MedicalHistoryDao.save(insurancePolicy.medicalHistory)
            val medicalHistoryDao = MedicalHistoryDao.findById(medicalHistory.id!!) ?: return@transaction null

            val newInsurancePolicy = if (insurancePolicy.id == null) {
                new { update(insurancePolicy, customerDao, medicalHistoryDao) }
            } else {
                val old = findById(insurancePolicy.id)
                old?.update(insurancePolicy, customerDao, medicalHistoryDao)
                findById(insurancePolicy.id)
            }

            newInsurancePolicy?.toInsurancePolicy()
        }

        fun delete(id: Int) = InsurancePolicies.deleteWhere { InsurancePolicies.id eq id }

        fun findAll(): List<InsurancePolicy> = InsurancePolicyDao.all().map { it.toInsurancePolicy() }
    }

    private fun update(
        insurancePolicy: InsurancePolicy,
        customerDao: CustomerDao,
        medicalHistoryDao: MedicalHistoryDao
    ) {
        this.isNewCustomer = insurancePolicy.newCustomer
        this.riskSurcharge = insurancePolicy.riskSurcharge
        this.riskSurchargeReason = insurancePolicy.riskSurchargeReason
        this.monthlyContribution = insurancePolicy.monthlyContribution
        this.initialContributionAmount = insurancePolicy.initialContributionAmount
        this.startOfContract = insurancePolicy.startOfContract
        this.isPremiumTariff = insurancePolicy.premiumTariff
        this.customer = customerDao
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
        customer.toCustomer(),
        medicalHistory.toMedicalHistory()
    )
}

object InsurancePolicies : IntIdTable() {
    val isNewCustomer = bool("neukunde")
    val riskSurcharge = double("risikozuschlag").nullable()
    val riskSurchargeReason = text("risikozuschlagsbegruendung").nullable()
    val monthlyContribution = double("monatlicher_beitrag")
    val initialContributionAmount = double("initiale_beitragshoehe")
    val startOfContract = text("vertragsbeginn")
    val isPremiumTariff = bool("premium_tarif")
    val customer = reference("kundennr", Customers)
    val medicalHistory = reference("krankenhistorie_id", MedicalHistories)
}
