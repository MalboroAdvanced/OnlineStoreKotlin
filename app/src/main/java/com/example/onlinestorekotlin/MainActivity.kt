package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_btnLogin.setOnClickListener {

            val logInURL = "http://192.168.43.206/OnlineStoreApp/login_app_user.php?email=" +
                    activity_main_edtEmail.text.toString() +
                    "&pass=" + activity_main_edtPassword.text.toString()
            val requestQ = Volley.newRequestQueue(this@MainActivity)
            val stringRequest = StringRequest(Request.Method.GET,logInURL,Response.Listener {
                    response ->

                if(response.equals("The user does exist")){

                    Person.email = activity_main_edtEmail.text.toString()

                    Toast.makeText(this@MainActivity,response,Toast.LENGTH_SHORT).show()

                    val homeIntent = Intent(this@MainActivity,HomeScreen::class.java)
                    startActivity(homeIntent)

                }else{

                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message").
                        setMessage(response).create().show()
                }

            },Response.ErrorListener { error ->

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Message").
                    setMessage(error.message).create().show()

            })

            requestQ.add(stringRequest)
        }

        activity_main_btnSignUp.setOnClickListener {

            var signUpIntent = Intent(this@MainActivity,SignUpLayout::class.java)
            startActivity(signUpIntent)
        }
    }
}
