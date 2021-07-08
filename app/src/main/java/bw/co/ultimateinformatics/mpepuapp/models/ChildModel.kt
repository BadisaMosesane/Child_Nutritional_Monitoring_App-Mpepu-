package bw.co.ultimateinformatics.mpepuapp.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.telephony.PhoneNumberUtils

import java.io.Serializable
import java.util.ArrayList
import java.util.Date

@Entity(tableName = "childModel")
class ChildModel : Serializable {
    @PrimaryKey
    var regID: Int = 0
    var registrationNumber: String? = null
    var firstName: String? = null
    var isRegistered: String? = null
    var lastName: String? = null
    var fathersName: String? = null
    var mothersName: String? = null
    private val motherDOB: Date? = null
    var birthHeight: Double? = null
    var birthWeight: Double? = null
    var age: Int = 0
    private val headCircumference: Double? = null
    private val breastfedWithinAnHour: Boolean = false
    private val placeOfBirth: String? = null
    private val homeBorn: Boolean = false
    private val nameOfFacility: String? = null
    private val firstSeenAtFacility: Date? = null
    private val presentPostalAdress: String? = null
    private val physicalAddress: String? = null
    private val ward: String? = null
    private val village: String? = null
    private val phoneNumber: Int = 0

    @Ignore
    var recordedWeights: ArrayList<Double>? = null
    @Ignore
    var recordedHeights: ArrayList<Double>? = null
    @Ignore
    var recordedInoculations: ArrayList<String>? = null

    constructor(regID: Int, firstName: String, lastName: String) {
        this.regID = regID
        this.firstName = firstName
        this.lastName = lastName
    }

    // Empty Constructor for Firebase
    constructor()


    fun recordCurrentHeight(currentHeight: Double) {

        // recordedHeights.set(age - 1, currentHeight);

    }

    fun recordCurrentWeight(currentWeight: Double) {

        recordedWeights!!.set(age - 1, currentWeight)

    }
}
