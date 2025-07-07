package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.UserDto

data class User(
  var id: Long,
  var name: String,
  var email: String
) {
  fun toDto(): UserDto {
    return UserDto(id, name, email)
  }

  companion object {
    fun fromDto(it: UserDto): User {
      return User(it.id, it.name, it.email)
    }
  }
}