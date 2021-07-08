package bw.co.ultimateinformatics.mpepuapp.helpers

import android.app.Application
import android.graphics.Color
import com.android.volley.RequestQueue
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.util.ArrayList


class MpepuHelper: Application() {

    val TAG = MpepuHelper::class.java
        .simpleName

    private var mRequestQueue: RequestQueue? = null

    private var mInstance: MpepuHelper? = null


    val FIRST_NAME = "firstName"
    val LAST_NAME = "lastName"
    val AGE_STR = "age"
    val BIRTH_HEIGHT = "birthLength"
    val BIRTH_WEIGHT = "birthWeight"
    val FATHER_S_NAME = "fathersName"
    val MOTHER_S_NAME = "mothersName"

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    @Synchronized
    fun getInstance(): MpepuHelper? {
        return mInstance
    }

    fun getRequestQueue(): RequestQueue? {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(applicationContext)
        }

        return mRequestQueue
    }

    fun <T> addToRequestQueue(req: Request<T>, tag: String) {
        // set the default tag if tag is empty
        req.setTag(if (TextUtils.isEmpty(tag)) TAG else tag)
        getRequestQueue()?.add(req)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        req.setTag(TAG)
        getRequestQueue()?.add(req)
    }

    fun cancelPendingRequests(tag: Any) {
        mRequestQueue?.cancelAll(tag)
    }

    fun isIDValid(regID: String): Boolean {

        return (regID.get(4) == '1' || regID.get(4) == '2')
    }

    fun isValidLength(regID: String): Boolean {

        return regID.length == 9
    }


    fun validateID(regID: String): Boolean {

        return isValidLength(regID) && isIDValid(regID)
    }


     fun labelMonths(): ArrayList<String> {
        val xVals = ArrayList<String>()
         for (i in 0..11) {

             val month = "Month $i"

             xVals.add(month)
         }
        return xVals
    }



   private fun setYAxisValues(ourArray: Array<Double>): ArrayList<Entry> {
        val yVals = ArrayList<Entry>()

       for (index in 0 until ourArray.size){
           yVals.add(Entry(ourArray[index].toFloat(),index))

       }


        return yVals
    }

    private fun setZSCores(): ArrayList<String> {

        val zScores = ArrayList<String>()

        for (i in 3..-3){
            zScores.add(i.toString())
        }


        return zScores
    }

    public fun setData(tag: String): LineData{
        val xVals = labelMonths()
        emptyArray<Double>()
        val ts = arrayOf(23.0, 23.5, 24.0)

        val yVals = setYAxisValues(ts)

        val set1: LineDataSet


        // create a dataset and give it a type
        set1 = LineDataSet(yVals, "Birth to 5 years (Z-scores)")

        set1.fillAlpha = 110 //110
        set1.color = Color.BLACK
        set1.setCircleColor(Color.BLACK)
        set1.lineWidth = 1f
        set1.circleRadius = 3f
        set1.setDrawCircleHole(false)
        set1.valueTextSize = 9f
        set1.setDrawFilled(false)



        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1) // add the datasets
        val data = LineData(xVals, dataSets)

        return data
    }
}
