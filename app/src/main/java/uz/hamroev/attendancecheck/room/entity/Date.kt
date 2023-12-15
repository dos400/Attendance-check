package uz.hamroev.attendancecheck.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date")
class Date {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var date: String = ""


    constructor()
    constructor(id: Long, date: String) {
        this.id = id
        this.date = date
    }

    constructor(date: String) {
        this.date = date
    }


}