package com.example.cometchatauth.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.example.cometchatauth.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val appID:String="7425cd0fd61f16"

        CometChat.init(this,appID, object : CometChat.CallbackListener<String>() {
            override fun onSuccess(p0: String?) {
                Log.d("TAG", "Initialization completed successfully")
            }

            override fun onError(p0: CometChatException?) {
                Log.d("TAG", "Initialization failed with exception: " + p0?.message)
            }
        })

        sign_in.setOnClickListener {
            //        Convert Input to string
            val UID = login_uid.text.toString().trim()

//        Check validation
            if (UID.isEmpty()){
                login_uid.error = "UId required"
                login_uid.requestFocus()
                return@setOnClickListener
            }
//            Api Call
            val apiKey:String="439bf4ba468d430195869924b381eef3bc109746"

            CometChat.login(UID,apiKey, object : CometChat.CallbackListener<User>() {
                override fun onSuccess(p0: User?) {
                    Log.d("TAG", "Login Successful : " + p0?.toString())
                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_LONG).show()
                }

                override fun onError(p0: CometChatException?) {
                    Log.d("TAG", "Login failed with exception: " +  p0?.message)
                }
            })
        }
    }
}
