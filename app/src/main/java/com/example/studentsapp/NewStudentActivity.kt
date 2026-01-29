package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewStudentActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var checkedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        supportActionBar?.title = "New Students"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nameEditText = findViewById(R.id.nameEditText)
        idEditText = findViewById(R.id.idEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        addressEditText = findViewById(R.id.addressEditText)
        checkedCheckBox = findViewById(R.id.checkedCheckBox)

        val cancelButton: Button = findViewById(R.id.cancelButton)
        val saveButton: Button = findViewById(R.id.saveButton)

        cancelButton.setOnClickListener {
            finish()
        }

        saveButton.setOnClickListener {
            val student = Student(
                id = idEditText.text.toString(),
                name = nameEditText.text.toString(),
                phone = phoneEditText.text.toString(),
                address = addressEditText.text.toString(),
                isChecked = checkedCheckBox.isChecked
            )
            StudentsModel.addStudent(student)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
