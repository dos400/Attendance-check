package uz.hamroev.attendancecheck.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.hamroev.attendancecheck.room.entity.Student

@Dao
interface StudentDao {

    //create user
    @Insert
    fun insertUser(student: Student)

    //read users
    @Query("SELECT * FROM student")
    fun getAllUsers(): List<Student>

    //update user
    @Update
    fun updateUser(student: Student)

    //delete user
    @Delete
    fun deleteUser(student: Student)

    //deleteAll
    @Query("DELETE FROM student")
    fun deleteAll()

}
