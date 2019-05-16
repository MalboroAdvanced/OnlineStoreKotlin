package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up_layout.*

class SignUpLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_layout)


        signup_layout_btnLogin.setOnClickListener {

           finish()
        }



        signup_layout_btnSignup.setOnClickListener {

            if(signup_layout_edtPassword.text.toString().equals(signup_layout_edtPasswordConfirm.text.toString())){
                //registration process
                val signUpURL = "http://192.168.43.206/OnlineStoreApp/join_new_user.php?email="+
                        signup_layout_edtEmail.text.toString() +
                        "&username=" + signup_layout_edtUsername.text.toString() +
                        "&pass=" + signup_layout_edtPassword.text.toString()
                    val requestQ = Volley.newRequestQueue(this@SignUpLayout)
                val striRequest = StringRequest(Request.Method.GET,signUpURL,Response.Listener { response ->

                    if(response.equals("A user with same Email Address already exists")){

                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Message").
                            setMessage(response).create().show()

                    }else{

                        Person.email = signup_layout_edtEmail.text.toString()

                        val dialogBuilder = AlertDialog.Builder(this)
                        dialogBuilder.setTitle("Message").
                            setMessage(response).create().show()
                        val homeIntent = Intent(this@SignUpLayout,HomeScreen::class.java)
                        startActivity(homeIntent)

                    }

                },Response.ErrorListener { error ->

                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setTitle("Message").
                        setMessage(error.message).create().show()

                })
                requestQ.add(striRequest)


            }else{

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Alert").
                    setMessage("Password Mismatch").create().show()


            }

        }
    }
}
