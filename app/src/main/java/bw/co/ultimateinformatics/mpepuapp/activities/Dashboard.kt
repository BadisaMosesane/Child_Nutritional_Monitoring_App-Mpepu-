package bw.co.ultimateinformatics.mpepuapp.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bw.co.ultimateinformatics.mpepuapp.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnGraphs.setOnClickListener { proceed() }
        btnConsultation.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }


    }

    private fun proceed() {
        val intent = Intent(this, LogActivity::class.java)
        startActivity(intent)
    }
}
