package com.example.studentprofile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import java.io.ByteArrayOutputStream
import java.io.File

class AdminRegistrationForm : AppCompatActivity() {
    lateinit var filePath: Uri
    lateinit var imageUri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_registration_form)
        val intent = intent
        val regNoData = intent.getStringExtra("RegNo")
        val RegNo: EditText = findViewById(R.id.EditText1)
        val Name: EditText = findViewById(R.id.EditText2)
        val FatherName: EditText = findViewById(R.id.EditText3)
        val MotherName: EditText = findViewById(R.id.EditText4)
        val MobileNo: EditText = findViewById(R.id.EditText5)
        val Gmail: EditText = findViewById(R.id.EditText6)
        val Address: EditText = findViewById(R.id.EditText7)
        val RegNo10th: EditText = findViewById(R.id.EditText8)
        val Marks10th: EditText = findViewById(R.id.EditText9)
        val RegNo12th: EditText = findViewById(R.id.EditText10)
        val Marks12th: EditText = findViewById(R.id.EditText11)
        val image: ImageView = findViewById(R.id.ImageView1)
        val SelectImage: Button = findViewById(R.id.button1)
        val Register: Button = findViewById(R.id.button3)
        val Cancel: Button = findViewById(R.id.button4)
        RegNo.isEnabled = false
        val fileDir = getExternalFilesDir(null)
        val file = File(fileDir, "$regNoData.txt")
        val fileData = file.readText()
        val seprator = "\n"
        val seprator2 = ":"
        val arrayData = fileData.split(seprator).toTypedArray()
        for (i in arrayData.indices) {
            val arrayData2 = arrayData[i].split(seprator2).toTypedArray()
            if (arrayData2[0] == "RegNo") {
                RegNo.setText(arrayData2[1])
            } else if (arrayData2[0] == "Name") {
                Name.setText(arrayData2[1])
                if (arrayData2[1] != "_") {
                    Name.isEnabled = false
                }
            } else if (arrayData2[0] == "FatherName") {
                FatherName.setText(arrayData2[1])
                if (arrayData2[1] != "_") {
                    FatherName.isEnabled = false
                }
            } else if (arrayData2[0] == "MotherName") {
                MotherName.setText(arrayData2[1])
                if (arrayData2[1] != "_") {
                    MotherName.isEnabled = false
                }
            } else if (arrayData2[0] == "MobileNo") {
                MobileNo.setText(arrayData2[1])
                if (arrayData2[1] != "_") {
                    MobileNo.isEnabled = false
                }
            } else if (arrayData2[0] == "Gmail") {
                Gmail.setText(arrayData2[1])
                if (arrayData2[1] != "_") {
                    Gmail.isEnabled = false
                }
            } else if (arrayData2[0] == "Address") {
                Address.setText(arrayData2[1])
                if (arrayData2[1] != "_") {
                    Address.isEnabled = false
                }
            } else if (arrayData2[0] == "RegNo10th") {
                RegNo10th.setText(arrayData2[1])
                if (arrayData2[1] != "_") {
                    RegNo10th.isEnabled = false
                }
            } else if (arrayData2[0] == "Marks10th") {
                Marks10th.setText(arrayData2[1])
                if (arrayData2[1] != "_") {
                    Marks10th.isEnabled = false
                }
            } else if (arrayData2[0] == "RegNo12th") {
                RegNo12th.setText(arrayData2[1])
                if (arrayData2[1] != "_") {
                    RegNo12th.isEnabled = false
                }
            } else if (arrayData2[0] == "Marks12th") {
                Marks12th.setText(arrayData2[1])
                if (arrayData2[1] != "_") {
                    Marks12th.isEnabled = false
                }
            }
        }
        val file2 = File(fileDir, "${regNoData}profile.txt")
        val fileData2 = file2.readText()
        if (fileData2 != "") {
            SelectImage.isEnabled = false
            val imageBytes = Base64.decode(fileData2, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            image.setImageBitmap(decodedImage)
        }
        Cancel.setOnClickListener {
            onBackPressed()
        }
        val ip = registerForActivityResult(ActivityResultContracts.GetContent()) {
            filePath = it!!
            if (it != null) {
                imageUri = filePath
                image.setImageURI(it)
            }

        }
        SelectImage.setOnClickListener {
            ip.launch("image/*")
        }
        Register.setOnClickListener {
            var oldData = file.readText()
            var newData = ""
            for (i in arrayData.indices) {
                val arrayData2 = arrayData[i].split(seprator2).toTypedArray()
                if (arrayData2[0] == "RegNo") {
                    newData += "\nRegNo:" + arrayData2[1]
                } else if (arrayData2[0] == "Name") {
                    newData += "\nName:" + Name.text.toString()
                } else if (arrayData2[0] == "FatherName") {
                    newData += "\nFatherName:" + FatherName.text.toString()
                } else if (arrayData2[0] == "MotherName") {
                    newData += "\nMotherName:" + MotherName.text.toString()
                } else if (arrayData2[0] == "MobileNo") {
                    newData += "\nMobileNo:" + MobileNo.text.toString()
                } else if (arrayData2[0] == "Gmail") {
                    newData += "\nGmail:" + Gmail.text.toString()
                } else if (arrayData2[0] == "Address") {
                    newData += "\nAddress:" + Address.text.toString()
                } else if (arrayData2[0] == "RegNo10th") {
                    newData += "\nRegNo10th:" + RegNo10th.text.toString()
                } else if (arrayData2[0] == "Marks10th") {
                    newData += "\nMarks10th:" + Marks10th.text.toString()
                } else if (arrayData2[0] == "RegNo12th") {
                    newData += "\nRegNo12th:" + RegNo12th.text.toString()
                } else if (arrayData2[0] == "Marks12th") {
                    newData += "\nMarks12th:" + Marks12th.text.toString()
                }
            }
            val newFileData = oldData.replace(oldData, newData)
            file.writeText(newFileData)
            if (image.drawable != null && (image.drawable as BitmapDrawable).bitmap != null) {
                val boas = ByteArrayOutputStream()
                val bitmap = image.drawable.toBitmap()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, boas)
                val encodedImage = Base64.encodeToString(boas.toByteArray(), Base64.DEFAULT)
                file2.writeText(encodedImage)
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}