package util

import de.thkoeln.inf.gpm.vgb.model.external.*
import de.thkoeln.inf.gpm.vgb.model.external.Claim
import de.thkoeln.inf.gpm.vgb.model.external.Customer
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
        val customer = Customer.createOrUpdate(Customer("2010-10-10T00:00:00", "demo"))
        val insurant = Insurant.createOrUpdate(
            Insurant(
                "Fischer2",
                "Jens",
                "2001-10-10T00:00:00",
                "m",
                1.87,
                78.0,
                stein,
                customer
            )
        )
        val medicalHistory = MedicalHistory.createOrUpdate(MedicalHistory())
        val precondition = Precondition.createOrUpdate(Precondition(medicalHistory, heart))
        val claim = Claim.createOrUpdate(
            Claim(
                "2020-10-10T00:00:00",
                "2020-10-11T00:00:00",
                35.0,
                null,
                null,
                null,
                null,
                null,
                insurant,
                null,
                medicalHistory
            )
        )
        val policy = InsurancePolicy.createOrUpdate(
            InsurancePolicy(
                false,
                null,
                null,
                35.7,
                33.2,
                "2020-10-12T00:00:00",
                true,
                "2020-10-11T00:00:00",
                false,
                insurant,
                medicalHistory
            )
        )
        println("finished")
    }
}