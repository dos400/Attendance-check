package uz.hamroev.attendancecheck.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "student"
)
class Student {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

    var name: String = ""
    var surname: String = ""
    var group: String = ""



    constructor()


    constructor(name: String, surname: String, group: String) {
        this.name = name
        this.surname = surname
        this.group = group
    }




}