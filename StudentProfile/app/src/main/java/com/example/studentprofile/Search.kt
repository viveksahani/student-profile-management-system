package com.example.studentprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import java.io.File
import java.io.FileWriter
import java.util.zip.Inflater

class Search : Fragment() {
    lateinit var v:View
    lateinit var searchBox: SearchView
    lateinit var btnDelete:Button
    lateinit var btnEdit:Button
    lateinit var btnGivefeed:Button
    var context = Feedback()
    var person = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_search, container, false)
        val name = arguments?.getString("Person")
        person = name?:"Teacher"
        btnDelete = v.findViewById(R.id.delete)
        btnEdit = v.findViewById(R.id.edit)
        btnGivefeed = v.findViewById(R.id.givefeedback)
        if(person == "Teacher") {
            btnDelete.isEnabled = false
            btnEdit.isEnabled = false
        } else if(person == "Admin") {
            btnDelete.isEnabled = true
            btnEdit.isEnabled = true
        }
        init(container)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun init(v1:ViewGroup?) {
        searchBox = v.findViewById(R.id.searchBox)
        var regNo = ""
        val fileDir = requireContext().getExternalFilesDir(null)
        searchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                regNo = query
                if(regNo.length < 5 || regNo.length > 5 && regNo.length < 8 || regNo.length > 8) {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Search method")
                        .setMessage("Search Registration number should be of length 5 or 8")
                        .setCancelable(true)
                        .setPositiveButton("Ok") {x, which ->}
                        .create().show()
                } else {
                    val file = File(fileDir, "${regNo}.txt")
                    val file2 = File(fileDir, "${regNo}profile.txt")
                    val fragment = Common_Profile()
                    if(file.exists() && file2.exists()) {
                        val bundle = Bundle()
                        bundle.putString("Id", regNo)
                        fragment.arguments = bundle
                        childFragmentManager.beginTransaction()
                            .replace(R.id.searchFrame, fragment)
                            .addToBackStack(null)
                            .commit()
                    } else {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.searchFrame, BlankFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        btnGivefeed.setOnClickListener {
            val file = File(fileDir, "${regNo}feedback.txt")
            if (!file.exists()) {
                file.writeText("")
            }
            val builder = AlertDialog.Builder(requireContext())
            builder.setView(R.layout.activity_feedback)
            builder.setPositiveButton("Submit") { x, which ->
                val dialog = builder.create()
                val ratingBar = dialog.findViewById<RatingBar>(R.id.ratingBar6)
                var rating = ratingBar?.rating
            }
            builder.setNegativeButton("Cancel", null)
            val dialog = builder.create()

            dialog.setOnShowListener {
                val ratingBar = dialog.findViewById<RatingBar>(R.id.ratingBar6)
                var rating = ratingBar?.rating
            }
            dialog.show()
        }
    }
}