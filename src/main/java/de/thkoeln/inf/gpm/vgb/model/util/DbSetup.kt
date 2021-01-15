package de.thkoeln.inf.gpm.vgb.model.util

import de.thkoeln.inf.gpm.vgb.model.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DbSetup {
    val db by lazy {
        Database.connect(
                "jdbc:postgresql://localhost:5432/GPM",
                driver = "org.postgresql.Driver",
                user = "POSTGRES", password = "POSTGRES"
        )
    }

    val dbWithDocker by lazy {
        Database.connect(
                "jdbc:postgresql://host.docker.internal:5432/GPM",
                driver = "org.postgresql.Driver",
                user = "POSTGRES", password = "POSTGRES"
        )
    }

    fun recreateSchema() {
        dropSchema()
        createSchema()
    }

    fun dropSchema() = transaction {
        println("drop existing schema")
        SchemaUtils.drop(Claims)
        SchemaUtils.drop(InsurancePolicies)
        SchemaUtils.drop(Preconditions)
        SchemaUtils.drop(MedicalHistories)
        SchemaUtils.drop(Insurants)
        SchemaUtils.drop(Customers)
        SchemaUtils.drop(Addresses)
        SchemaUtils.drop(Locations)
        SchemaUtils.drop(Diseases)
        SchemaUtils.drop(Tariffs)
        SchemaUtils.drop(ClaimClassifications)
    }

    fun createSchema() = transaction {
        println("created db schema")
        SchemaUtils.create(Locations)
        SchemaUtils.create(Diseases)
        SchemaUtils.create(Tariffs)
        SchemaUtils.create(ClaimClassifications)
        SchemaUtils.create(Addresses)
        SchemaUtils.create(Customers)
        SchemaUtils.create(Insurants)
        SchemaUtils.create(MedicalHistories)
        SchemaUtils.create(Preconditions)
        SchemaUtils.create(InsurancePolicies)
        SchemaUtils.create(Claims)
    }

    fun insertDummyData() {
        println("insert dummy data")
        val location1 = LocationDao.save(Location(null, "51515", "Olpe")) ?: return
        val disease = DiseaseDao.save(Disease(null, 3, "Herzfehler")) ?: return
        val tariff1 = TariffDao.save(Tariff(null, "Basis")) ?: return
        val tariff2 = TariffDao.save(Tariff(null, "Premium")) ?: return
        ClaimClassificationDao.save(ClaimClassification(null, "versicherungsfähig"))
        ClaimClassificationDao.save(ClaimClassification(null, "nicht versicherungsfähig"))
        val address1 = AddressDao.save(Address(null, "Am Glockenberg", "10", location1)) ?: return
        val customer1 = CustomerDao.save(Customer(null, "01/01/2020", null)) ?: return
        InsurantDao.save(Insurant(null, "Fischer", "Jens", "01/06/2001", 'm', 191, 75, address1, customer1))
        val medicalHistory1 = MedicalHistoryDao.save(MedicalHistory(null))
        val precondition1 = PreconditionDao.save(Precondition(null, medicalHistory1, disease)) ?: return
        val insurancePolicy1 = InsurancePolicyDao.save(
                InsurancePolicy(
                        null,
                        false,
                        200.0,
                        "Nicht ok",
                        42.0,
                        39.0,
                        "01/06/2009",
                        customer1,
                        tariff2,
                        medicalHistory1
                )
        ) ?: return
        val claim1 = ClaimDao.save(
                Claim(
                        null,
                        "20/10/1990",
                        30.4,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                )
        )
        println("finished")
    }
}