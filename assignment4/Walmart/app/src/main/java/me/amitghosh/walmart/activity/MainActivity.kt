package me.amitghosh.walmart.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import me.amitghosh.walmart.R
import me.amitghosh.walmart.model.User
import me.amitghosh.walmart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        val users = ArrayList<User>()
        users.add(User("Aiub", "Khan", "a@b.com", "123"))
        users.add(User("Joe", "Martin", "joe@gk.com", "234"))
        users.add(User("Mark", "Anthony", "mark@gk.com", "345"))
        users.add(User("Damien", "Martin", "dam@gk.com", "456"))
        users.add(User("Mark", "Brown", "brown@gk.com", "567"))

        binding.btnSignIn.setOnClickListener {

            //Log.i("Main activity:", txtEmail.text.toString())

            if (binding.txtEmail.text.trim().isEmpty() || binding.txtPassword.text.isEmpty()) {
                showToastMessage("Username and password both required")
                return@setOnClickListener
            }

            val validUser =
                users.find {
                    it.userName == binding.txtEmail.text.toString()
                        .trim() && it.password == binding.txtPassword.text.toString()
                }

            if (validUser == null) {
                showToastMessage("Invalid user or password")
                return@setOnClickListener
            }

            val shoppingIntent = Intent(this, ShoppingActivity::class.java)
            shoppingIntent.putExtra("userName", validUser.userName)
            startActivity(shoppingIntent)

        }

        binding.tvForgotPasswordButton.setOnClickListener {
            val txtEmail = findViewById<EditText>(R.id.txtEmail)
            val recipientEmail = txtEmail.text.toString()

            var user = users.find { it.userName == recipientEmail }
            if (user != null) {
                showToastMessage("Your password is: ${user.password}")

                val emailIntent = Intent(Intent.ACTION_SENDTO)
                emailIntent.data = Uri.parse("mailto:$recipientEmail")

                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your password")
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Your password is: ${user.password}")

                if (emailIntent.resolveActivity(packageManager) != null) {
                    startActivity(emailIntent)
                } else {
                    Log.d("ImplicitIntents", "Can’t handle this intent!")
                }
            } else {
                showToastMessage("User not found")
            }
        }

        //Register result contracts
        val registerResultContract =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val userData = result.data?.getParcelableExtra<User>("user_data")

                    val userExists = users.find { user -> user.userName == userData?.userName }
                    if (userExists != null) {
                        showToastMessage("User ${userData?.userName} already exist!")
                        return@registerForActivityResult
                    }

                    if (userData != null) {
                        users.add(userData)
                        showToastMessage("User with ${userData.userName} created.")
                    } else {
                        showToastMessage("Failed to retrieve user data")
                    }
                } else {
                    showToastMessage("Operation canceled or failed")
                }
            }

        binding.btnCreateNewAcc.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            registerResultContract.launch(intent)
        }
    }

    private fun showToastMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }
}