package com.example.debuggingchallenge


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var llMain: LinearLayout
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var password2: EditText
    private lateinit var submitBtn: Button
    private lateinit var activeUsers: TextView
    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView
    private lateinit var tv4: TextView
    private lateinit var tv5: TextView
    private lateinit var tv6: TextView
    private lateinit var tv7: TextView


    private var users = arrayListOf(
            "Freddy",
            "Jason",
            "Ripley",
            "Poncho",
            "Saitama",
            "Archer",
            "Derek",
            "Pamela",
            "Simba",
            "Simon",
            "Retsy",
            "Peter",
            "Daria",
            "Smitty"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llMain = findViewById(R.id.llMain)
        userName = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        password2 = findViewById(R.id.etConfirmPassword)
        submitBtn = findViewById(R.id.btSubmit)
        tv1=findViewById(R.id.tv1)
        tv2=findViewById(R.id.tv2)
        tv3=findViewById(R.id.tv3)
        tv4=findViewById(R.id.tv4)
        tv5=findViewById(R.id.tv5)
        tv6=findViewById(R.id.tv6)
        tv7=findViewById(R.id.tv7)
        submitBtn.setOnClickListener {
            if(usernameAccepted(userName.text.toString()) && passwordAccepted(password.text.toString())) {
                if (password.text.toString() == password2.text.toString()) {
                    Toast.makeText(this, "User created!", Toast.LENGTH_LONG).show()
                    users.add(userName.text.toString().capitalize())
                    displayUsers()
                }else{
                    Toast.makeText(this, "The passwords do not match, try again", Toast.LENGTH_LONG).show()
                }
            }
        }
        activeUsers = findViewById(R.id.tvActiveUsers)
    }

    private fun usernameAccepted(text: String): Boolean{
        if(!users.contains(text.capitalize())){
            if(text.length in 5..15){
                tv1.setTextColor(Color.rgb(44, 234, 21 ))
                if(!hasNumber(text)){
                    tv2.setTextColor(Color.rgb(44, 234, 21  ))
                    if(!hasSpecial(text) && !text.contains(" ")){
                        tv3.setTextColor(Color.rgb(44, 234, 21  ))
                        return true
                    }else {
                        Toast.makeText(this, "The username cannot contain special characters or spaces", Toast.LENGTH_LONG).show()
                        tv3.setTextColor(Color.rgb(234, 21, 21 ))
                    }
                }else {
                    Toast.makeText(this, "The username cannot contain numbers", Toast.LENGTH_LONG).show()
                    tv2.setTextColor(Color.rgb(234, 21, 21 ))
                }
            }else {
                Toast.makeText(this, "The username must be between 5 and 15 characters long", Toast.LENGTH_LONG).show()
                tv1.setTextColor(Color.rgb(234, 21, 21 ))
            }
        }else {
            Toast.makeText(this, "The username is already taken", Toast.LENGTH_LONG).show()
        }
        return false
    }

    private fun passwordAccepted(text: String): Boolean{
        if(text.length >= 8) {
            tv4.setTextColor(Color.rgb(44, 234, 21  ))
            if (hasUpper(text)) {
                tv5.setTextColor(Color.rgb(44, 234, 21  ))
                if (hasNumber(text)) {
                    tv6.setTextColor(Color.rgb(44, 234, 21  ))
                    if (hasSpecial(text)) {
                        tv7.setTextColor(Color.rgb(44, 234, 21  ))
                        return true
                    } else {
                        Toast.makeText(this, "The password must contain a special character", Toast.LENGTH_LONG).show()
                        tv7.setTextColor(Color.rgb(234, 21, 21 ))
                    }
                } else {
                    Toast.makeText(this, "The password must contain a number", Toast.LENGTH_LONG).show()
                    tv6.setTextColor(Color.rgb(234, 21, 21 ))
                }
            } else {
                Toast.makeText(this, "The password must contain an uppercase letter", Toast.LENGTH_LONG).show()
                tv5.setTextColor(Color.rgb(234, 21, 21 ))
            }
        }else {
            Toast.makeText(this, "The password must be at least 8 characters long", Toast.LENGTH_LONG).show()
            tv4.setTextColor(Color.rgb(234, 21, 21 ))
        }
        return false
    }

    private fun hasUpper(text: String): Boolean{
        var letter = 'A'
        while (letter <= 'Z') {
            if(text.contains(letter)){
                return true
            }
            ++letter
        }
        return false
    }

    private fun hasNumber(text: String): Boolean{
        for(i in 0..9){
            if(text.contains(i.toString())){
                return true
            }
        }
        return false
    }

    private fun hasSpecial(text: String): Boolean{
        val specialCharacters = "!@#$%"
        for(special in specialCharacters){
            if(text.contains(special)){
                return true
            }
        }
        return false
    }

    private fun displayUsers(){
        var allUsers = "Active Users:\n\n"
        for(user in users){
            allUsers += user + "\n"
        }
        activeUsers.text = allUsers
        activeUsers.isVisible = true
        llMain.isVisible = false
    }
}