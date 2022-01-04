package com.example.loginregisterform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import android.content.SharedPreferences
class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private var edtUsername: AppCompatEditText? = null
    private var edtEmail: AppCompatEditText? = null
    private var edtMobile: AppCompatEditText? = null
    private var edtPassword: AppCompatEditText? = null
    private var btnRegister: AppCompatButton? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerInitialization()
    }

    private fun registerInitialization() {
        edtUsername = findViewById(R.id.edtUsername)
        edtMobile = findViewById(R.id.edtMobile)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnRegister = findViewById(R.id.btnRegister)
        btnRegister!!.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnRegister -> {
                validateInput()
            }
        }

    }

    private fun validateInput() {

      when {
            edtUsername!!.text.toString() == "" -> {
                Toast.makeText(
                    applicationContext,
                    "Please Enter Username",
                    Toast.LENGTH_SHORT
                ).show()
            }
          edtEmail!!.text.toString() == "" -> {
              Toast.makeText(
                  applicationContext,
                 "Please Enter Email",
                  Toast.LENGTH_SHORT
              ).show()
          }
            edtMobile!!.text.toString() == "" -> {
                Toast.makeText(
                    applicationContext,"please enter mobile number",
                    Toast.LENGTH_SHORT
                ).show()
            }

            edtPassword!!.text.toString() == "" -> {
                Toast.makeText(
                    applicationContext,
                   "Please Enter Password",
                    Toast.LENGTH_SHORT
                ).show()
            }
          !isValidPhoneNumber(edtMobile?.text.toString())->{
              Toast.makeText(
                  applicationContext,"pleas Enter valid mobile number",
                  Toast.LENGTH_SHORT
              ).show()



          }

          !isEmailValid(edtEmail?.text.toString())->{
              Toast.makeText(
                  applicationContext,"pleas Enter valid Email",
                  Toast.LENGTH_SHORT
              ).show()
          }
            else -> {
              registerForm()
            }

        }
    }

    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPhoneNumber(phone: CharSequence?): Boolean {
        return Patterns.PHONE.matcher(phone).matches()
    }

    private fun registerForm() {

        val editor = getSharedPreferences("my_pref", MODE_PRIVATE).edit()
        editor.putString("name", edtUsername?.text.toString())
        editor.putString("email", edtEmail?.text.toString())
        editor.putString("mobile", edtMobile?.text.toString())
        editor.putString("password", edtPassword?.text.toString())
        editor.apply()

      val intent = Intent(applicationContext,LoginActivity::class.java)
        startActivity(intent)
    }
}

