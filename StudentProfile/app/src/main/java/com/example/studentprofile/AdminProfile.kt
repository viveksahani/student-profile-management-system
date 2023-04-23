package com.example.studentprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.w3c.dom.Text

class AdminProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_profile)
        val profile:TextView = findViewById(R.id.profile)
        val nav: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val regNo = intent.getStringExtra("Id")
        val per = intent.getStringExtra("Person")
        profile.setText(per)
        val bundle = Bundle()
        bundle.putString("Id", regNo)
        bundle.putString("Person", "Admin")
        val fragment = Common_Profile()
        val fragment2 = Search()
        fragment.arguments = bundle
        fragment2.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.adminFrame, fragment)
            .addToBackStack(null)
            .commit()
        nav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.adminFrame, fragment)
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.adminFrame, fragment2)
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