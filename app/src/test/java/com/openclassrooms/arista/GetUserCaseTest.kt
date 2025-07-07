package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import com.openclassrooms.arista.domain.usecase.GetUserUsecase
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


@RunWith(JUnit4::class)
class GetUserUsecaseTest {

  @Mock
  private lateinit var userRepository: UserRepository
  private lateinit var getUserUsecase: GetUserUsecase

  @Before
  fun setup() {
    MockitoAnnotations.openMocks(this)
    getUserUsecase = GetUserUsecase(userRepository)
  }

  @After
  fun tearDown() {
    Mockito.framework().clearInlineMocks()
  }

  @Test
  fun `should get user from repository`(): Unit = runBlocking {
    val userData = User (
      id = 2,
      name = "JaneDoe",
      email = "janedoe@exemple.com"
    )
    val userFlow = flowOf(userData)
    Mockito.`when`(userRepository.getUserbyId(2)).thenReturn(userFlow)

    val result = getUserUsecase.execute(2)

    assertEquals(userData, result.first())
  }
}