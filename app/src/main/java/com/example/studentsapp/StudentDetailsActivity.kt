package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {

    private var studentPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        supportActionBar?.title = "Students Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        studentPosition = intent.getIntExtra("STUDENT_POSITION", -1)

        val editButton: Button = findViewById(R.id.editButton)
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("STUDENT_POSITION", studentPosition)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadStudentDetails()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun loadStudentDetails() {
        val student = StudentsModel.getStudentByPosition(studentPosition)
        if (student == null) {
            finish()
            return
        }

        val imageView: ImageView = findViewById(R.id.studentImage)
        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val idTextView: TextView = findViewById(R.id.idTextView)
        val phoneTextView: TextView = findViewById(R.id.phoneTextView)
        val addressTextView: TextView = findViewById(R.id.addressTextView)
        val checkedCheckBox: CheckBox = findViewById(R.id.checkedCheckBox)

        imageView.setImageResource(R.drawable.student_avatar)
        nameTextView.text = "name: ${student.name}"
        idTextView.text = "id: ${student.id}"
        phoneTextView.text = "phone: ${student.phone}"
        addressTextView.text = "address: ${student.address}"
        checkedCheckBox.isChecked = student.isChecked
        checkedCheckBox.isEnabled = false
    }
}
