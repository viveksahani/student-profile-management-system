package com.example.studentprofile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.File
import java.io.FileWriter

class TeacherFragment : Fragment() {
    lateinit var v: View
    lateinit var btn_login: Button
    lateinit var btn_registerHere: Button
    lateinit var btn_reset: Button
    lateinit var id: EditText
    lateinit var pass: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_teacher, container, false)
        init(container)
        return v
    }

    fun init(v1: ViewGroup?) {
        btn_login = v.findViewById(R.id.button)
        btn_registerHere = v.findViewById(R.id.button2)
        btn_reset = v.findViewById(R.id.button3)
        id = v.findViewById(R.id.editTextTextPersonName)
        pass = v.findViewById(R.id.editTextTextPassword)
        id.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (id.text.length < 5 || (id.text.length > 5 && id.text.length < 8) || id.text.length > 8) {
                    id.setError("Enter a valid ID")
                } else {
                    id.setError(null)
                }
            }
        }
        pass.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (pass.text.toString() == "") {
                    pass.setError("enter password")
                } else {
                    pass.setError(null)
                }
            }
        }
        btn_login.setOnClickListener {
            if (id.text != null && pass.text != null) {
                val fileDir = requireContext().getExternalFilesDir(null)
                val file = File(fileDir, "Teacher.txt")
                val seperator = "\n"
                if (file.exists()) {
                    val fileData = file.readText()
                    val arrayData = fileData.split(seperator).toTypedArray()
                    var flg = false
                    for (i in arrayData.indices) {
                        if (arrayData[i] == id.text.toString()) {
                            flg = true
                            var flg2 = false
                            for (j in arrayData.indices) {
                                if (arrayData[j] == pass.text.toString() && j == i + 1) {
                                    flg2 = true
                                    Toast.makeText(
                                        requireContext(),
                                        "welcome Teacher",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val fileStudent = File(fileDir, "${id.text}.txt")
                                    val fileStudentProfile = File(fileDir, "${id.text}profile.txt")
                                    if(fileStudent.exists() && fileStudentProfile.exists()) {
                                        val filedata1 = fileStudent.readText()
                                        val fileData2 = fileStudentProfile.readText()
                                        val arr1 = filedata1.split(seperator).toTypedArray()
                                        var flg_open = true
                                        for(k in arr1.indices) {
                                            val arr2 = arr1[k].split(":").toTypedArray()
                                            if(arr2[0] != "") {
                                                if(arr2[1] == "_") {
                                                    flg_open = false
                                                    break
                                                }
                                            }
                                        }
                                        if(fileData2 == "") {
                                            flg_open = false
                                        }
                                        if(flg_open == true) {
                                            val intent = Intent(requireContext(), TeacherProfile::class.java)
                                            intent.putExtra("Id", id.text.toString())
                                            intent.putExtra("Person", "Teacher")
                                            startActivity(intent)
                                        } else {
                                            val intent = Intent(requireContext(), TeacherRegistrationForm::class.java)
                                            intent.putExtra("RegNo", id.text.toString())
                                            startActivity(intent)
                                            break
                                        }
                                    } else {
                                        if(!fileStudent.exists()) {
                                            val writer = FileWriter(fileStudent, true)
                                            val dataToStore = "\nRegNo:${id.text}\nName:_\nFatherName:_\nMotherName:_\nMobileNo:_\nGmail:_\nAddress:_\nRegNo10th:_\nMarks10th:_\nRegNo12th:_\nMarks12th:_"
                                            writer.write(dataToStore)
                                            writer.close()
                                        }
                                        if(!fileStudentProfile.exists()) {
                                            val writer = FileWriter(fileStudentProfile, true)
                                            writer.write("")
                                            writer.close()
                                        }
                                        val intent = Intent(requireContext(), TeacherRegistrationForm::class.java)
                                        intent.putExtra("RegNo", id.text.toString())
                                        startActivity(intent)
                                        break
                                    }
                                }
                            }
                            if (flg2 == false) {
                                pass.setError("Password incorrect")
                            }
                            break
                        }
                    }
                    if (flg == false) {
                        Toast.makeText(
                            requireContext(),
                            "You are not a Teacher",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    val writer = FileWriter(file, true)
                    writer.write("\n12018349" + "\nAdmin")
                    writer.close()
                    Toast.makeText(
                        requireContext(),
                        "some issue occured, try again",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } else {
                id.setError("all fields are required")
                pass.setError("all fields are required")
            }
        }
        btn_registerHere.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.register_teacher, null)
            val newId: EditText = dialogView.findViewById(R.id.editTextNumber)
            val Id: EditText = dialogView.findViewById(R.id.editTextNumber2)
            val adminPass: EditText = dialogView.findViewById(R.id.editTextTextPassword2)
            val btnRegister: Button = dialogView.findViewById(R.id.button3)
            newId.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    if (newId.text.length < 5 || (newId.text.length > 5 && newId.text.length < 8) || newId.text.length > 8) {
                        newId.setError("Enter a valid ID")
                    } else {
                        newId.setError(null)
                    }
                }
            }
            Id.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    if (Id.text.length < 5 || (Id.text.length > 5 && Id.text.length < 8) || Id.text.length > 8) {
                        Id.setError("Enter a valid ID")
                    } else {
                        Id.setError(null)
                    }
                }
            }
            adminPass.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    if (adminPass.text.toString() == "") {
                        adminPass.setError("please enter password")
                    } else {
                        adminPass.setError(null)
                    }
                }
            }
            btnRegister.setOnClickListener {
                if (newId.text != null && Id.text != null && adminPass.text != null) {
                    val fileDir = requireContext().getExternalFilesDir(null)
                    val file = File(fileDir, "Teacher.txt")
                    val file2 = File(fileDir, "Admin.txt")
                    val seperator = "\n"
                    if (!file.exists()) {
                        val writer = FileWriter(file, true)
                        writer.write("\n12018349" + "\nAdmin")
                        writer.close()
                    }
                    if (!file2.exists()) {
                        val writer = FileWriter(file2, true)
                        writer.write("\n12018349" + "\nAdmin")
                        writer.close()
                    }
                    val fileData = file.readText()
                    val fileData2 = file.readText()
                    val arrayData = fileData.split(seperator).toTypedArray()
                    val arrayData2 = fileData2.split(seperator).toTypedArray()
                    var flg = false
                    for (i in arrayData2.indices) {
                        if (arrayData2[i].trim() == Id.text.toString()) {
                            var flg2 = false
                            if (Id == newId) {
                                Toast.makeText(
                                    requireContext(),
                                    newId.text.toString() + " is already a Teacher",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                flg = true
                                break
                            }
                            for (j in arrayData.indices) {
                                if (arrayData[j].trim() == newId.text.toString()) {
                                    Toast.makeText(
                                        requireContext(),
                                        newId.text.toString() + " is already a Teacher",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                    flg2 = true
                                    flg = true
                                    break
                                }
                            }
                            if (flg2 == false) {
                                var flg3 = false
                                for (j in arrayData.indices) {
                                    if (arrayData2[j].trim() == adminPass.text.toString()) {
                                        flg3 = true
                                        val writer = FileWriter(file, true)
                                        writer.write("\n" + newId.text.toString() + "\nAdmin")
                                        writer.close()
                                        Toast.makeText(
                                            requireContext(),
                                            newId.text.toString() + " added successfully",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                        break
                                    }
                                }
                                if (flg3 == false) {
                                    adminPass.setError("Password incorrect")
                                }
                                flg = true
                                break
                            }
                        }
                    }
                    if (flg == false) {
                        Toast.makeText(
                            requireContext(),
                            Id.text.toString() + " can't add user",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                } else {
                    newId.setError("all fields are required")
                    Id.setError("all fields are required")
                    adminPass.setError("all fields are required")
                }
            }
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Adding new-Teacher")
            builder.setMessage("Please enter the data carefully, Teacher will be able to see data of any student")
            builder.setView(dialogView)
            builder.setCancelable(false)
            builder.setNeutralButton("Cancle") { x, which ->
            }
            builder.create().show()
        }
        btn_reset.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.reset_password, null)
            val Id: EditText = dialogView.findViewById(R.id.editTextNumber)
            val oldPass: EditText = dialogView.findViewById(R.id.editTextTextPassword3)
            val newPass: EditText = dialogView.findViewById(R.id.editTextTextPassword2)
            val resetBtn: Button = dialogView.findViewById(R.id.button3)
            Id.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    if (Id.text.length < 5 || (Id.text.length > 5 && Id.text.length < 8) || Id.text.length > 8) {
                        Id.setError("Enter a valid ID")
                    } else {
                        Id.setError(null)
                    }
                }
            }
            newPass.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    if (newPass.text.toString() == "") {
                        newPass.setError("please enter password")
                    } else {
                        newPass.setError(null)
                    }
                }
            }
            resetBtn.setOnClickListener {
                if (Id.text != null && newPass.text != null && oldPass.text != null) {
                    val fileDir = requireContext().getExternalFilesDir(null)
                    val file = File(fileDir, "Teacher.txt")
                    if (file.exists()) {
                        val fileData = file.readText()
                        val seperator = "\n"
                        val arrayData = fileData.split(seperator).toTypedArray()
                        var flg = false
                        for (i in arrayData.indices) {
                            if (arrayData[i] == Id.text.toString()) {
                                flg = true
                                if (arrayData[i + 1] == oldPass.text.toString()) {
                                    val old = Id.text.toString() + "\n" + oldPass.text.toString()
                                    val new = Id.text.toString() + "\n" + newPass.text.toString()
                                    val newfileData = fileData.replace(old, new)
                                    file.writeText(newfileData)
                                    Toast.makeText(
                                        requireContext(),
                                        "password reset successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    break
                                } else {
                                    oldPass.setError("incorrect password")
                                }
                            }
                        }
                        if (flg == false) {
                            Id.setError("you not registered yet")
                        }
                    } else {
                        val writer = FileWriter(file, true)
                        writer.write("\n12018349" + "\nAdmin")
                        writer.close()
                        Toast.makeText(
                            requireContext(),
                            "some issue occured, try again",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                } else {
                    Id.setError("all fields are required")
                    Id.setError("all fields are required")
                    newPass.setError("all fields are required")
                }
            }
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Re-setting the password")
            builder.setMessage("Please enter a strong password, do not share with anyone and remember it")
            builder.setView(dialogView)
            builder.setCancelable(false)
            builder.setNeutralButton("Cancle") { x, which ->
            }
            builder.create().show()
        }
    }
}