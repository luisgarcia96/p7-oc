package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import java.time.LocalDateTime


@RunWith(JUnit4::class)
class AddNewExerciseTestcase {

  @Mock
  private lateinit var exerciseRepository: ExerciseRepository
  private lateinit var addNewExerciseUseCase: AddNewExerciseUseCase


  @Before
  fun setUp() {
    MockitoAnnotations.openMocks(this)
    addNewExerciseUseCase = AddNewExerciseUseCase(exerciseRepository)
  }

  @Test
  fun `use case should add new exercise successfully`() = runBlocking {
    // Create a fake exercise
    val testExercise = Exercise(
      startTime = LocalDateTime.now(),
      duration = 30,
      category = ExerciseCategory.Riding.toString(),
      intensity = 5
    )
    addNewExerciseUseCase.execute(testExercise)

    verify(exerciseRepository).addExercise(testExercise)
  }
}