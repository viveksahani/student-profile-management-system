package com.example.studentprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.FileWriter
import java.util.zip.Inflater

class TeacherProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_profile)
        val regNo = intent.getStringExtra("Id")
        val per = intent.getStringExtra("Person")
        val person: TextView = findViewById(R.id.person)
        person.setText(per)
        val nav: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val bundle = Bundle()
        bundle.putString("Id", regNo)
        bundle.putString("Person", "Teacher")
        val fragment = Common_Profile()
        val fragment2 = Search()
        fragment.arguments = bundle
        fragment2.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.teacherFrame, fragment)
            .addToBackStack(null)
            .commit()
        nav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.teacherFrame, fragment)
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.teacherFrame, fragment2)
                        .addToBackStack(null)
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}