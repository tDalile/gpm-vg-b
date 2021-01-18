package util

import de.thkoeln.inf.gpm.vgb.model.*
import de.thkoeln.inf.gpm.vgb.model.Insurant
import model.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DbUtil {
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

    fun <T> runInTransaction(statement: () -> T) = transaction {
        statement()
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
    }

    fun createSchema() = transaction {
        println("created db schema")
        SchemaUtils.create(Locations)
        SchemaUtils.create(Diseases)
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

        val gm = Location.createOrUpdate(Location("51643", "Gummersbach"))
        val stein = Address.createOrUpdate(Address("Steinmülleralle", "7b", gm))
        val heart = Disease.createOrUpdate(Disease(3, "Herzfehler"))
        val customer = Customer.createOrUpdate(Customer("01/01/2020", "demo"))
        val insurant = Insurant.createOrUpdate(
            Insurant(
                "Fischer2",
                "Jens",
                "02/05/2020",
                "m",
                1.87,
                78.0,
                stein,
                customer
            )
        )
        val medicalHistory = MedicalHistory.createOrUpdate(MedicalHistory())
        val precondition = Precondition.createOrUpdate(Precondition(medicalHistory, heart))
        val claim = Claim.createOrUpdate(Claim(
            "01/01/2022",
            "01/03/2022",
            35.0,
            null,
            null,
            null,
            null,
            null,
            insurant,
            null,
            medicalHistory
        ))
        val policy = InsurancePolicy.createOrUpdate(
            InsurancePolicy(
                false,
                null,
                null,
                35.7,
                33.2,
                "01/06/2021",
                true,
                "10/10/1010",
                false,
                insurant,
                medicalHistory
            )
        )
        println("finished")
    }
}