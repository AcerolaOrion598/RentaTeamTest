package com.example.rentateamtest.database

import com.example.rentateamtest.model.User

abstract class UserConverter {
    companion object {
        fun fromUserToTable(user: User): UserTable {
            return UserTable(
                user.id,
                user.email,
                user.firstName,
                user.lastName,
                user.avatarUrl
            )
        }

        fun fromTableToUser(userTable: UserTable): User {
            return User(
                userTable.userId,
                userTable.email,
                userTable.firstName,
                userTable.lastName,
                userTable.avatarUrl
            )
        }
    }
}