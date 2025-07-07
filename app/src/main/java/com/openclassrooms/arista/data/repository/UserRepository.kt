package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val userDao: UserDtoDao) {

    suspend fun getUserbyId(id:Long): Flow<User> {
        return userDao.getUserById(id)
            .map {
                if (it != null) {
                    User.fromDto(it)
                }
                else {
                    User(1, "jane", "doe")
                }
            }
    }
}