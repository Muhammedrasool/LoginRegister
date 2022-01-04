package com.example.loginregisterform
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import android.content.SharedPreferences
import android.util.Log

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var edtEmail: AppCompatEditText? = null
    private var edtMobile: AppCompatEditText? = null
    private var edtPassword: AppCompatEditText? = null
    private var btnLogin: AppCompatButton? = null
    private var btnRegister: AppCompatButton? = null
    var pref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewInitialization()
    }

    private fun viewInitialization() {

        edtEmail = findViewById(R.id.edtEmail)
        edtMobile = findViewById(R.id.edtMobile)
        edtPassword = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)
        btnLogin!!.setOnClickListener(this)
        btnRegister!!.setOnClickListener(this)
        pref = getSharedPreferences("user_details", MODE_PRIVATE)
        intent = Intent(this@LoginActivity, WelcomeActivity::class.java)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnLogin -> {
               validateInput()

            }
            R.id.btnRegister -> {
                register()

            }

        }

    }
    private fun validateInput(){
        val prefs = getSharedPreferences("my_pref", MODE_PRIVATE)

        val username = prefs?.getString("name","")
        val email = prefs?.getString("email","")
        val mobile = prefs?.getString("mobile","")
        val password = prefs?.getString("password","")
       when {
            edtEmail!!.text.toString() == "" -> {
                Toast.makeText(
                    applicationContext,
                    "Please Enter Email",
                    Toast.LENGTH_SHORT
                ).show()
            }
           edtPassword!!.text.toString() == "" -> {
               Toast.makeText(
                   applicationContext,
                   "please enter password",
                   Toast.LENGTH_SHORT
               ).show()
           }


           edtEmail?.text.toString() != email && edtPassword?.text.toString() != password ->{
               Toast.makeText(
                   applicationContext,"invalid user",
                   Toast.LENGTH_SHORT
               ).show()

           }
           else -> {
                loginForm()
            }

        }
    }

    private fun loginForm() {

        val intent = Intent(applicationContext, WelcomeActivity::class.java)
        startActivity(intent)

    }



    private fun register() {
        val intent = Intent(applicationContext, RegisterActivity::class.java)
        startActivity(intent)
    }

}
