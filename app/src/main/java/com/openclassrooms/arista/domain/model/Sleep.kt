package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.SleepDto
import java.time.LocalDateTime
import java.time.ZoneOffset

data class Sleep(@JvmField
                 var id: Long,
                 var startTime: LocalDateTime,
                 var duration: Int,
                 var quality: Int
) {
  companion object {
    fun fromDto(it: List<SleepDto>): List<Sleep> {
      return it.map { Sleep(
        it.id,
        LocalDateTime.ofEpochSecond(it.startTime / 1000, 0, ZoneOffset.UTC),
        it.duration,
        it.quality) }
    }
  }
}