package com.example.studentsapp

object StudentsModel {
    private val students: MutableList<Student> = mutableListOf()

    fun getAllStudents(): List<Student> = students.toList()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun getStudentByPosition(position: Int): Student? {
        return if (position in students.indices) students[position] else null
    }

    fun updateStudent(position: Int, student: Student) {
        if (position in students.indices) {
            students[position] = student
        }
    }

    fun deleteStudent(position: Int) {
        if (position in students.indices) {
            students.removeAt(position)
        }
    }

    fun updateCheckedStatus(position: Int, isChecked: Boolean) {
        if (position in students.indices) {
            students[position].isChecked = isChecked
        }
    }
}
