package uz.hamroev.attendancecheck.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.hamroev.attendancecheck.room.entity.Check
import uz.hamroev.attendancecheck.room.entity.Data
import uz.hamroev.attendancecheck.room.entity.Student

// StudentDao.kt
@Dao
interface StudentDao {
    @Insert
    fun insert(student: Student)

    @Query("SELECT * FROM students")
    fun getAllStudents(): List<Student>
}

// DataDao.kt
@Dao
interface DataDao {
    @Insert
    fun insert(data: Data)

    @Query("SELECT * FROM data")
    fun getAllData(): List<Data>
}

// CheckDao.kt
@Dao
interface CheckDao {
    @Insert
    fun insert(check: Check)

    @Query("SELECT * FROM checks")
    fun getAllChecks(): List<Check>
}
