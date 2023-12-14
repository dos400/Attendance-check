package uz.hamroev.attendancecheck.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Student.kt
@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val surname: String,
    val group: String,
)

// Data.kt
@Entity(tableName = "data")
class Data(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val date: String, // You might want to use a proper Date type here
)

// Check.kt
@Entity(tableName = "checks")
data class Check(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val student_id: Long,
    val date_id: Long,
    val is_have: Int,
)
