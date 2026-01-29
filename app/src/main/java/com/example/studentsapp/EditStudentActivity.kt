package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {

    private var studentPosition: Int = -1
    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var checkedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        supportActionBar?.title = "Edit Students"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        studentPosition = intent.getIntExtra("STUDENT_POSITION", -1)

        nameEditText = findViewById(R.id.nameEditText)
        idEditText = findViewById(R.id.idEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        addressEditText = findViewById(R.id.addressEditText)
        checkedCheckBox = findViewById(R.id.checkedCheckBox)

        loadStudentData()

        val cancelButton: Button = findViewById(R.id.cancelButton)
        val deleteButton: Button = findViewById(R.id.deleteButton)
        val saveButton: Button = findViewById(R.id.saveButton)

        cancelButton.setOnClickListener {
            finish()
        }

        deleteButton.setOnClickListener {
            StudentsModel.deleteStudent(studentPosition)
            finish()
        }

        saveButton.setOnClickListener {
            val updatedStudent = Student(
                id = idEditText.text.toString(),
                name = nameEditText.text.toString(),
                phone = phoneEditText.text.toString(),
                address = addressEditText.text.toString(),
                isChecked = checkedCheckBox.isChecked
            )
            StudentsModel.updateStudent(studentPosition, updatedStudent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun loadStudentData() {
        val student = StudentsModel.getStudentByPosition(studentPosition)
        if (student == null) {
            finish()
            return
        }

        nameEditText.setText(student.name)
        idEditText.setText(student.id)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)
        checkedCheckBox.isChecked = student.isChecked
    }
}
