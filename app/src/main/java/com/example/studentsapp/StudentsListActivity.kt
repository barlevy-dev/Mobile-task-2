package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentsListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        supportActionBar?.title = "Students List"

        recyclerView = findViewById(R.id.studentsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab: FloatingActionButton = findViewById(R.id.addStudentFab)
        fab.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        setupAdapter()
    }

    private fun setupAdapter() {
        adapter = StudentsAdapter(
            students = StudentsModel.getAllStudents(),
            onItemClick = { position ->
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra("STUDENT_POSITION", position)
                startActivity(intent)
            },
            onCheckChanged = { position, isChecked ->
                StudentsModel.updateCheckedStatus(position, isChecked)
            }
        )
        recyclerView.adapter = adapter
    }
}
