package com.example.cometchatauth.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.example.cometchatauth.R
import com.example.cometchatauth.api.RetrofitClient
import com.example.cometchatauth.models.DefaultResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Initialize the comet library
        val appID:String="7425cd0fd61f16"

        CometChat.init(this,appID, object : CometChat.CallbackListener<String>() {
            override fun onSuccess(p0: String?) {
                Log.d("TAG", "Initialization completed successfully")
            }

            override fun onError(p0: CometChatException?) {
                Log.d("TAG", "Initialization failed with exception: " + p0?.message)
            }
        })

        create_acct.setOnClickListener {

//            Converts input data to String
            val uid = input_uid.text.toString().trim()
            val name = input_name.text.toString().trim()

//            Necessary validations to ensure input fields are not empty
            if (uid.isEmpty()){
                input_uid.error = "UId required"
                input_uid.requestFocus()
                return@setOnClickListener
            }
            if (name.isEmpty()){
                input_name.error = "Name is required"
                input_name.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.createUser(uid, name)
                .enqueue(object: Callback<DefaultResponse>{
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {

                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {

                        if (response.code() == 200){
                            Toast.makeText(applicationContext, "Created Successfully", Toast.LENGTH_LONG).show()
                            val intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                        } else{
                            Toast.makeText(applicationContext, "Unsuccessful", Toast.LENGTH_LONG).show()
                        }
                    }
                })
        }
    }
}
