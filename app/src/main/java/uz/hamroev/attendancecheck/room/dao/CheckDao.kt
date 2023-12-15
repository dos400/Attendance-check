package uz.hamroev.attendancecheck.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import uz.hamroev.attendancecheck.room.entity.Check

@Dao
interface CheckDao {

    //create user
    @Insert
    fun insertCheck(check: Check)

    //read users
    @Query("SELECT * FROM check2")
    fun getAllChecks(): List<Check>

    @Query("SELECT * FROM check2 where date=:date")
    fun getAllChecksByDate(date: String): List<Check>

    //update user
    @Update
    fun updateCheck(check: Check)

    //delete user
    @Delete
    fun deleteCheck(check: Check)

    //deleteAll
    @Query("DELETE FROM check2")
    fun deleteAll()

}
