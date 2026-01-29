package com.example.studentsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentsAdapter(
    private val students: List<Student>,
    private val onItemClick: (Int) -> Unit,
    private val onCheckChanged: (Int, Boolean) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.studentImage)
        val nameTextView: TextView = itemView.findViewById(R.id.studentName)
        val idTextView: TextView = itemView.findViewById(R.id.studentId)
        val checkBox: CheckBox = itemView.findViewById(R.id.studentCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.nameTextView.text = student.name
        holder.idTextView.text = student.id
        holder.imageView.setImageResource(R.drawable.student_avatar)

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = student.isChecked

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            onCheckChanged(position, isChecked)
        }

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int = students.size
}
