package me.amitghosh.quizapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import me.amitghosh.quizapp.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI();
    }

    private fun setupUI() {
        binding.btnSubmit.setOnClickListener{
            val radio1_2 = findViewById<RadioButton>(R.id.radio1_2)
            val radio2_1 = findViewById<RadioButton>(R.id.radio2_1)

            var score = 0
            if (radio1_2.isChecked) score++
            if (radio2_1.isChecked) score++


            if (score > 0) {
                val currentDateTime = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")
                val formattedDateTime = currentDateTime.format(formatter)
                showAlertDialog(
                    "Congratulations!",
                    "You submitted on $formattedDateTime\nYou achieved: ${score * 50}%"
                )
            } else {
                showAlertDialog(
                    "Oops!",
                    "Wrong answers.\nTry again..."
                )
            }
        }

        binding.btnReset.setOnClickListener {
            val question1RadioGroup = findViewById<RadioGroup>(R.id.question1RadioGroup)
            val question2RadioGroup = findViewById<RadioGroup>(R.id.question2RadioGroup)

            uncheckAllRadioButtons(question1RadioGroup)
            uncheckAllRadioButtons(question2RadioGroup)
        }
    }

    fun showAlertDialog(txtTitle: String?, txtBody: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("$txtTitle")
            .setMessage("$txtBody")

        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun uncheckAllRadioButtons(radioGroup: RadioGroup?) {
        if (radioGroup != null) {
            for (i in 0 until radioGroup.childCount) {
                val radioButton = radioGroup.getChildAt(i) as? RadioButton
                radioButton?.isChecked = false
            }
        }
    }
}