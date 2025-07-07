package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime


@RunWith(JUnit4::class)
class GetAllSleepUsecaseTest {

  @Mock
  private lateinit var sleepRepository: SleepRepository
  private lateinit var getAllSleepUsecase: GetAllSleepsUseCase


  @Before
  fun setup() {
    MockitoAnnotations.openMocks(this)
    getAllSleepUsecase = GetAllSleepsUseCase(sleepRepository)
  }

  @After
  fun tearDown() {
    Mockito.framework().clearInlineMocks()

  }

  @Test
  fun `when repository returns sleeps, use case should return them`(): Unit = runBlocking {
    val testSleep = listOf(
      Sleep(
        startTime = LocalDateTime.now(),
        duration = 30,
        quality = 9,
        id = 1
      )
    )
    val flowSleep = flowOf(testSleep)
    Mockito.`when`(sleepRepository.getAllSleeps()).thenReturn(flowSleep)

    val result = getAllSleepUsecase.execute(1)

    assertEquals(testSleep, result.first())
  }
}