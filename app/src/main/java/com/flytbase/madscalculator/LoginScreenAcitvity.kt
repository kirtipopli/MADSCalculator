package com.flytbase.madscalculator

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginScreenAcitvity : AppCompatActivity(), View.OnClickListener {

    private var usernameStr: String = ""
    private var passwordStr: String = ""

    companion object {

        private val username: String = "mads"
        private val password: String = "123456"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener(this)

        et_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isNotEmpty()) {
                    usernameStr = s.toString().trim()
                } else {
                    usernameStr = ""
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isNotEmpty()) {
                    passwordStr = s.toString().trim()
                } else {
                    passwordStr = ""
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    override fun onClick(v: View?) {
        when (v) {

            btn_login -> {

                if (usernameStr.isNotEmpty() && passwordStr.isEmpty()) {
                    Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show()
                } else if (usernameStr.isEmpty() && passwordStr.isNotEmpty()) {
                    Toast.makeText(this, "Please enter Username", Toast.LENGTH_SHORT).show()
                } else if (usernameStr.isEmpty() && passwordStr.isEmpty()) {
                    Toast.makeText(this, "Please enter Username & Password", Toast.LENGTH_SHORT)
                        .show()
                } else if (usernameStr.isNotEmpty() && passwordStr.isNotEmpty()) {
                    if (usernameStr.contentEquals(username) && !passwordStr.contentEquals(password)) {
                        Toast.makeText(this, "Please enter correct Password", Toast.LENGTH_SHORT)
                            .show()
                    } else if (!usernameStr.contentEquals(username) && passwordStr.contentEquals(
                            password
                        )
                    ) {
                        Toast.makeText(this, "Please enter correct Username", Toast.LENGTH_SHORT)
                            .show()
                    } else if (usernameStr.contentEquals(username) && passwordStr.contentEquals(
                            password
                        )
                    ) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(this, "Incorrect Username & Password", Toast.LENGTH_SHORT)
                }

            }
        }
    }

}