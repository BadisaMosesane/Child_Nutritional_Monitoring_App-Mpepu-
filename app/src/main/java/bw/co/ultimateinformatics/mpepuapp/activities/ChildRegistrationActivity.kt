package bw.co.ultimateinformatics.mpepuapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.util.Log
import android.view.View
import bw.co.ultimateinformatics.mpepuapp.helpers.MpepuHelper
import bw.co.ultimateinformatics.mpepuapp.R
import bw.co.ultimateinformatics.mpepuapp.models.ChildModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.content_child_details.*


class ChildRegistrationActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var currentChildModel: ChildModel

    private var isRegistered: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_details)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

       val i = intent
       val regOrNah = i.getStringExtra("FLAG")
        isRegistered = regOrNah


        val myRegID = i.getStringExtra("regID")
        val cWeight = i.getDoubleExtra("currentWeight", 0.0).toString()
        val cHeight = i.getDoubleExtra("currentHeight", 0.0).toString()
        unLockAll()
        edtRegID.setText(myRegID)
        edtCWeight.setText(cWeight)
        edtCHeight.setText(cHeight)



        if (regOrNah == "Exists"){
            currentChildModel = i.getSerializableExtra("CHILD") as ChildModel


            if (currentChildModel.age > 0) {
                edtFName.setText(currentChildModel.firstName)
                edtLName.setText(currentChildModel.lastName)
                edtAge.setText(currentChildModel.age.toString())
                edtBWeight.setText(currentChildModel.birthWeight.toString())
                edtbHeight.setText(currentChildModel.birthHeight.toString())
                edtMName.setText(currentChildModel.mothersName)
                edtfaName.setText(currentChildModel.fathersName)
                edtCHeight.setText(i.getStringExtra("currentHeight"))
                edtCWeight.setText(i.getStringExtra("currentWeight"))

                lockAll()

                intent = Intent(this, AllGraphsActivity::class.java)
                startActivity(intent)
            }
        }






        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { attemptRegistration() }
    }



    /**
     * Attempts to register the child specified in the form.
     * If there are form errors (invalid registration number, missing fields, etc.), the
     * errors are presented and no actual registration attempt is made.
     */
    private fun attemptRegistration() {


        // Reset errors.
        edtRegID.error = null


        // Store values at the time of the login attempt.
        val regNumberStr = edtRegID.text.toString()


        // Check for a valid registration Number, if the user entered one.
        val validator = MpepuHelper()
        if (!TextUtils.isEmpty(regNumberStr) && !validator.validateID(regNumberStr)) {
            edtRegID.error = getString(R.string.error_invalid_id)

        } else {
            //TODO:
            registerChild()
        }
    }


    public override fun onStart() {
        super.onStart()

        db = FirebaseFirestore.getInstance()

    }

    private fun registerChild() {

        val child = ChildModel()

        // Toast.makeText(this,child.recordedHeights.size.toString(), Toast.LENGTH_LONG).show()

        child.registrationNumber = edtRegID.text.toString()
        child.firstName = edtFName.text.toString()
        child.lastName = edtLName.text.toString()
        child.mothersName = edtMName.text.toString()
        child.fathersName = edtfaName.text.toString()
        child.birthWeight = java.lang.Double.parseDouble(edtBWeight.text.toString())
        child.birthHeight = java.lang.Double.parseDouble(edtbHeight.text.toString())
        child.age = java.lang.Integer.parseInt(edtAge.text.toString())
        child.recordCurrentWeight ( java.lang.Double.parseDouble(edtCWeight.text.toString()))
        child.recordCurrentHeight ( java.lang.Double.parseDouble(edtCHeight.text.toString()))

        currentChildModel = child


        if (isRegistered != "Exists"){

            // Add a new document using the registration Number
            db.collection("children").document(edtRegID.text.toString())
                .set(child)
                .addOnSuccessListener {
                    Log.d("Registration", "DocumentSnapshot successfully written!")
                    intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { e -> Log.w("Registration", "Error writing document", e) }

        }else{
            //TODO : Add the functionality for when the child already exists on the database

        }



    }

    private fun unLockAll(){

        edtFName.isFocusableInTouchMode
        edtLName.isFocusableInTouchMode
        edtAge.isFocusableInTouchMode
        edtBWeight.isFocusableInTouchMode
        edtbHeight.isFocusableInTouchMode
        edtMName.isFocusableInTouchMode
        edtfaName.isFocusableInTouchMode
        edtCHeight.isFocusableInTouchMode
        edtCWeight.isFocusableInTouchMode



    }

    private fun lockAll(){


        edtRegID.isFocusable = false
        edtFName.isFocusable = false
        edtLName.isFocusable = false
        edtAge.isFocusable = false
        edtBWeight.isFocusable = false
        edtbHeight.isFocusable = false
        edtMName.isFocusable = false
        edtfaName.isFocusable = false
        edtCHeight.isFocusable = false
        edtCWeight.isFocusable = false
      //  ditText.


    }


}
