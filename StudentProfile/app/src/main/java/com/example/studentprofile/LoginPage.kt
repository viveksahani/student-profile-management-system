package com.example.studentprofile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout.Alignment
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class LoginPage : AppCompatActivity() {
    lateinit var spinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        val options = arrayOf("Student", "Teacher", "Admin")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
        spinner = findViewById(R.id.spinner1)
        spinner.adapter = adapter
        spinner.dropDownWidth = 500
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, AdminFragment())
            .addToBackStack(null)
            .commit()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem == "Student") {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, StudentFragment())
                        .addToBackStack(null)
                        .commit()
                } else if (selectedItem == "Teacher") {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, TeacherFragment())
                        .addToBackStack(null)
                        .commit()
                } else if (selectedItem == "Admin") {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AdminFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    override fun onBackPressed() {
        val inflat = LayoutInflater.from(this).inflate(R.layout.activity_feedback, null)
        var alert = AlertDialog.Builder(this)
            .setTitle("Feedback")
            .setMessage("please give feedback")
            .setView(inflat)
            .setCancelable(true)
            .setPositiveButton("submit") { x, which ->
                Toast.makeText(this, "feedback submitted", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNeutralButton("cancel") { x, which ->
            }
            .create().show();
    }
}