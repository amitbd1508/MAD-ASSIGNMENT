package me.amit.ghosh.tableadditems

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import me.amit.ghosh.tableadditems.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var addButton: Button
    private lateinit var tableLayout: TableLayout

    private lateinit var androidVersion: EditText
    private lateinit var androidCode: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "TableAddItems"

        addButton = findViewById(R.id.add_button)
        tableLayout = findViewById(R.id.table_layout)

        addButton.setOnClickListener { onButtonClick() }

        androidVersion = findViewById(R.id.android_version)
        androidCode = findViewById(R.id.android_code)
        add("Version", "Code Name")

    }

    private fun onButtonClick() {
        add(leftText = androidVersion.text.toString(),
            rightText = androidCode.text.toString())
    }

    private fun add(leftText: String, rightText: String) {
        val tableRow = TableRow(applicationContext)
        tableRow.setBackgroundColor(Color.MAGENTA);

        val horizontalView = LinearLayout(applicationContext)
        horizontalView.orientation = LinearLayout.HORIZONTAL

        val leftView = TextView(applicationContext)
        leftView.setTextColor(Color.BLUE)
        leftView.text = leftText

        val rightView = TextView(applicationContext)
        rightView.setTextColor(Color.BLUE)
        rightView.text = rightText

        val border = View(applicationContext)
        border.setBackgroundColor(Color.BLACK)

        val borderParams = LinearLayout.LayoutParams(0, 80)



        val rightViewParams = LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT)
        val leftViewParams = LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT)


        leftViewParams.weight = 3.0f
        rightViewParams.weight = 3.0f
        borderParams.weight = 0.05f

        horizontalView.addView(leftView, leftViewParams)
        horizontalView.addView(border, borderParams)
        horizontalView.addView(rightView, rightViewParams)
        tableRow.addView(horizontalView, TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f))
        val tableRowParams = TableRow.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f)
        tableRowParams.setMargins(0, 10, 0, 10)

        tableLayout.addView(tableRow, tableRowParams)
    }
}