package com.example.studentprofile

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File

class StudentProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)
        val exit:Button = findViewById(R.id.exit)
        exit.setOnClickListener {
            onBackPressed()
        }
        val id = intent.getStringExtra("Id")
        val per = intent.getStringExtra("Person")
        val person:TextView = findViewById(R.id.person)
        person.setText(per)
        val RegNo: TextView = findViewById(R.id.textView4)
        val Name: TextView = findViewById(R.id.textView3)
        val FatherName: TextView = findViewById(R.id.textView6)
        val MotherName: TextView = findViewById(R.id.textView7)
        val MobileNo: TextView = findViewById(R.id.textView5)
        val Gmail: TextView = findViewById(R.id.textView8)
        val Address: TextView = findViewById(R.id.textView9)
        val Marks10th: TextView = findViewById(R.id.textView10)
        val Marks12th: TextView = findViewById(R.id.textView11)
        val image: ImageView = findViewById(R.id.imageView8)
        val fileDir = getExternalFilesDir(null)
        val file = File(fileDir, "$id.txt")
        val file2 = File(fileDir, "${id}profile.txt")
        val seperator = "\n"
        val seperator2 = ":"
        if(file.exists()) {
            val fileData = file.readText()
            val arrayData = fileData.split(seperator).toTypedArray()
            for(i in arrayData.indices) {
                val arrayData2 = arrayData[i].split(seperator2).toTypedArray()
                if (arrayData2[0] == "RegNo") {
                    RegNo.setText(arrayData2[1])
                } else if (arrayData2[0] == "Name") {
                    Name.setText(arrayData2[1])
                } else if (arrayData2[0] == "FatherName") {
                    FatherName.setText("Father Name: " + arrayData2[1])
                } else if (arrayData2[0] == "MotherName") {
                    MotherName.setText("Mother Name: " + arrayData2[1])
                } else if (arrayData2[0] == "MobileNo") {
                    MobileNo.setText("Mob: " + arrayData2[1])
                } else if (arrayData2[0] == "Gmail") {
                    Gmail.setText("Gmail: " + arrayData2[1])
                } else if (arrayData2[0] == "Address") {
                    Address.setText("Address: " + arrayData2[1])
                } else if (arrayData2[0] == "Marks10th") {
                    Marks10th.setText("Marks10th: " + arrayData2[1])
                } else if (arrayData2[0] == "Marks12th") {
                    Marks12th.setText("Marks12th: " + arrayData2[1])
                }
            }
            val fileData2 = file2.readText()
            if (fileData2 != "") {
                val imageBytes = Base64.decode(fileData2, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                image.setImageBitmap(decodedImage)
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}