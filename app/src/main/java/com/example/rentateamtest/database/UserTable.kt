package com.example.rentateamtest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = UserTable.tableName,
    indices = [Index(UserTable.userIdFieldName)]
)
data class UserTable (
    @PrimaryKey
    @ColumnInfo(name = userIdFieldName)
    val userId: Int,

    @ColumnInfo(name = emailFieldName)
    val email: String,

    @ColumnInfo(name = firstNameFieldName)
    val firstName: String,

    @ColumnInfo(name = lastNameFieldName)
    val lastName: String,

    @ColumnInfo(name = avatarUrlFieldName)
    val avatarUrl: String
) {
    companion object {
        const val tableName = "user_table"
        const val userIdFieldName = "user_id"
        const val emailFieldName = "email"
        const val firstNameFieldName = "first_name"
        const val lastNameFieldName = "last_name"
        const val avatarUrlFieldName = "avatar_url"
    }
}