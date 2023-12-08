package me.amitghosh.walmart.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import me.amitghosh.walmart.model.User
import me.amitghosh.walmart.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignIn.setOnClickListener {
            if (binding.txtFirstName.text.isEmpty() || binding.txtLastName.text.isEmpty() || binding.txtEmail.text.isEmpty() || binding.txtPassword.text.isEmpty()) {
                Toast.makeText(applicationContext, "All fields are mandatory", Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }
            val newUser = User(
                binding.txtFirstName.text.toString().trim(),
                binding.txtLastName.text.toString().trim(),
                binding.txtEmail.text.toString().trim(),
                binding.txtPassword.text.toString()
            )

            /*val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)*/

            val resultIntent = Intent()
            resultIntent.putExtra("user_data", newUser as Parcelable)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}