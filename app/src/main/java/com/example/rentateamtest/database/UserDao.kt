package com.example.rentateamtest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userTable: UserTable)

    @Query("select * from ${UserTable.tableName}")
    fun getUserList(): List<UserTable>

    @Query("delete from ${UserTable.tableName}")
    fun clearUserData()
}