package uz.hamroev.attendancecheck.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "check2"
)
class Check {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

    var student_id: Long = 0L
    var date: String = ""
    var has_student: String = ""

    var name: String = ""
    var surname: String = ""
    var group: String = ""





    constructor()
    constructor(student_id: Long, date: String, has_student: String) {
        this.student_id = student_id
        this.date = date
        this.has_student = has_student
    }

    constructor(student_id: Long, date: String, has_student: String, name: String, surname: String, group: String) {
        this.student_id = student_id
        this.date = date
        this.has_student = has_student
        this.name = name
        this.surname = surname
        this.group = group
    }


}