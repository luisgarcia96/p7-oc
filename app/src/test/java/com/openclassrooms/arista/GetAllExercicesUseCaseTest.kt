package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime


@RunWith(JUnit4::class)
class GetAllExercisesUseCaseTest {

  @Mock
  private lateinit var exerciseRepository: ExerciseRepository
  private lateinit var getAllExercisesUseCase: GetAllExercisesUseCase


  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    getAllExercisesUseCase = GetAllExercisesUseCase(exerciseRepository)
  }

  @After
  fun tearDown() {
    Mockito.framework().clearInlineMocks()
  }

  @Test
  fun `when repository returns exercises, use case should return them`() = runBlocking {
    val testExercises = listOf(
      Exercise(
        startTime = LocalDateTime.now(),
        duration = 30,
        category = ExerciseCategory.Running.toString(),
        intensity = 5
      ),
      Exercise(
        startTime = LocalDateTime.now().plusHours(1),
        duration = 45,
        category = ExerciseCategory.Riding.toString(),
        intensity = 7
      )
    )
    Mockito.`when`(exerciseRepository.getAllExercises()).thenReturn(testExercises)

    val result = getAllExercisesUseCase.execute()

    assertEquals(testExercises, result)
  }

  @Test
  fun `when repository returns empty list, use case should return empty list`() = runBlocking {
    Mockito.`when`(exerciseRepository.getAllExercises()).thenReturn(emptyList())

    val result = getAllExercisesUseCase.execute()

    assertTrue(result.isEmpty())
  }
}