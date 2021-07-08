package bw.co.ultimateinformatics.mpepuapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.util.Log
import android.view.View
import bw.co.ultimateinformatics.mpepuapp.helpers.MpepuHelper
import bw.co.ultimateinformatics.mpepuapp.R
import bw.co.ultimateinformatics.mpepuapp.models.ChildModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.content_log.*
import java.util.ArrayList


class LogActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var currentChildModel: ChildModel
    private lateinit var mpepuHelper: MpepuHelper




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        mpepuHelper = MpepuHelper()


        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { validateID() }
    }

    public override fun onStart() {
        super.onStart()
        db = FirebaseFirestore.getInstance()
    }

    fun validateID() {
        // Reset errors.
        edtRegID.error = null
        // Store values at the time of the login attempt.
        val regNumberStr = edtRegID.text.toString()
        // Check for a valid registration Number, if the user entered one.
        val validator = MpepuHelper()
        if (!TextUtils.isEmpty(regNumberStr) && !validator.validateID(regNumberStr)) {
            edtRegID.error = getString(R.string.error_invalid_id)

        } else {
            checkRecords()
        }

    }

    private fun checkRecords() {

        val regID = edtRegID.text.toString()
        val currentWeight = edtCWeight.text.toString().toDouble()
        val currentHeight = edtCHeight.text.toString().toDouble()



        val builder: AlertDialog.Builder = AlertDialog.Builder(
            this,
            android.R.style.Theme_DeviceDefault_Dialog
        )

        builder.setTitle("No Records Found")
            .setMessage("Do you want to create a new one rather?")
            .setPositiveButton("YES") { dialog, which ->
                val intent = Intent(this@LogActivity, ChildRegistrationActivity::class.java)

                intent.putExtra("regID", regID)
                startActivity(intent)
            }
            .setNegativeButton(android.R.string.no) { dialog, which ->
                // do nothing
            }
            .setIcon(android.R.drawable.ic_dialog_alert)


        val childDocumentRef = db.collection("children").document(regID)

        childDocumentRef.get()
            .addOnSuccessListener { document ->
                if (document.data != null) {
                    val child = ChildModel()

                    child.registrationNumber = regID
                    child.firstName = document.getString(mpepuHelper.FIRST_NAME)
                    child.lastName = document.getString(mpepuHelper.LAST_NAME)
                    child.age = (document.getLong(mpepuHelper.AGE_STR))!!.toInt()
                    child.birthHeight = document.getDouble(mpepuHelper.BIRTH_HEIGHT)!!.toDouble()
                    child.birthWeight = document.getDouble(mpepuHelper.BIRTH_WEIGHT)!!.toDouble()
                    child.fathersName = document.getString(mpepuHelper.FATHER_S_NAME)
                    child.mothersName = document.getString(mpepuHelper.MOTHER_S_NAME)
                    child.recordedHeights = document.get("recordedHeights") as ArrayList<Double>?
                    child.recordedWeights =
                            document.get("recordedWeights") as ArrayList<Double>? //as MutableList<Double>?

                    currentChildModel = child



                    val intent = Intent(this@LogActivity, ChildRegistrationActivity::class.java)

                    intent.putExtra("regID", regID.toString())
                    intent.putExtra("currentWeight",currentWeight)
                    intent.putExtra("currentHeight",currentHeight)
                    intent.putExtra("FLAG","Exists")

                    intent.putExtra("CHILD", currentChildModel)
                    startActivity(intent)


                    Log.d("TAG", "DocumentSnapshot data: " + document.data)
                } else {
                    builder.show()
                    Log.d("TAG", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "get failed with ", exception)
                builder.show()
            }



    }


}


