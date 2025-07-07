package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.ExerciseDto
import java.time.LocalDateTime
import java.time.ZoneOffset

data class Exercise(
    val id: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var category: String,
    var intensity: Int
) {
    fun toDto(): ExerciseDto {
        return ExerciseDto(
            id,
            startTime.toEpochSecond(ZoneOffset.UTC) * 1000,
            duration,
            category,
            intensity)
    }

    companion object {
        fun fromDto(it: ExerciseDto): Exercise {
            return Exercise(
                it.id,
                LocalDateTime.ofEpochSecond(it.startTime / 1000, 0, ZoneOffset.UTC),
                it.duration,
                it.category,
                it.intensity)
        }
    }
}