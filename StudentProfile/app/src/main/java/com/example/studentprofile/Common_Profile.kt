package com.example.studentprofile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.io.File

class Common_Profile : Fragment() {
    lateinit var v:View
    lateinit var intent:Intent
    var reg = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.activity_common_profile, container, false)
        val regNo = arguments?.getString("Id")
        reg = regNo?:"12018349"
        init(container)
        return v
    }
    fun init(v1:ViewGroup?) {
        val RegNo: TextView = v.findViewById(R.id.textView4)
        val Name: TextView = v.findViewById(R.id.textView3)
        val FatherName: TextView = v.findViewById(R.id.textView6)
        val MotherName: TextView = v.findViewById(R.id.textView7)
        val MobileNo: TextView = v.findViewById(R.id.textView5)
        val Gmail: TextView = v.findViewById(R.id.textView8)
        val Address: TextView = v.findViewById(R.id.textView9)
        val Marks10th: TextView = v.findViewById(R.id.textView10)
        val Marks12th: TextView = v.findViewById(R.id.textView11)
        val image: ImageView = v.findViewById(R.id.imageView8)
        val fileDir = requireContext().getExternalFilesDir(null)
        val file = File(fileDir, "${reg}.txt")
        val file2 = File(fileDir, "${reg}profile.txt")
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
        }
        if(file2.exists()) {
            val fileData2 = file2.readText()
            if (fileData2 != "") {
                val imageBytes = Base64.decode(fileData2, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                image.setImageBitmap(decodedImage)
            }
        }
    }
}