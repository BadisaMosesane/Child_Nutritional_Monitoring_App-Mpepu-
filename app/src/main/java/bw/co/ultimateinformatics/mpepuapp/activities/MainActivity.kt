package bw.co.ultimateinformatics.mpepuapp.activities

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import bw.co.ultimateinformatics.mpepuapp.R
import com.github.bassaer.chatmessageview.model.ChatUser
import com.github.bassaer.chatmessageview.model.Message
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val ACCESS_TOKEN = "9dfebed8c3ab48cc9163695134076b17"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        FuelManager.instance.baseHeaders = mapOf(
            "Authorization" to "Bearer $ACCESS_TOKEN"
        )

        FuelManager.instance.basePath =
                "https://api.dialogflow.com/v1"

        FuelManager.instance.baseParams = listOf(
            "v" to "20170712",                  // latest protocol
            "sessionId" to UUID.randomUUID(),   // random ID
            "lang" to "en"                      // English language
        )

        val human = ChatUser(
            1,
            "You",
            BitmapFactory.decodeResource(resources,
                R.drawable.ic_account_circle
            )
        )

        val agent = ChatUser(
            2,
            "Mpepu Agent",
            BitmapFactory.decodeResource(resources,
                R.drawable.ic_account_circle
            )
        )

        my_chat_view.setOnClickSendButtonListener(
            View.OnClickListener {
                my_chat_view.send(Message.Builder()
                    .setUser(human)
                    .setText(my_chat_view.inputText)
                    .build()
                )

                Fuel.get("/query",
                    listOf("query" to my_chat_view.inputText))
                    .responseJson { _, _, result ->
                        val reply = result.get().obj()
                            .getJSONObject("result")
                            .getJSONObject("fulfillment")
                            .getString("speech")

                        my_chat_view.send(Message.Builder()
                            .setRight(true)
                            .setUser(agent)
                            .setText(reply)
                            .build()
                        )
                    }

            }
        )
       val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


}
