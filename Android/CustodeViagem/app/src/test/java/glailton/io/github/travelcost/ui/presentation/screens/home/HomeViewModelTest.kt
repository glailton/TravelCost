package glailton.io.github.travelcost.ui.presentation.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import glailton.io.github.travelcost.core.data.Response
import glailton.io.github.travelcost.core.data.repository.RemoteDataSourceImpl
import glailton.io.github.travelcost.domain.remote.request.TravelCostRequest
import glailton.io.github.travelcost.domain.remote.response.TravelCostResponse
import glailton.io.github.travelcost.utils.CoroutineRules
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineRules()

    private lateinit var vm: HomeViewModel
    private val dataSource: RemoteDataSourceImpl = mockk()

    @Before
    fun setup() {
        vm = HomeViewModel(dataSource)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should calculate successfully`() = runTest {
        val request = TravelCostRequest(100.0, 5.99, 10.0)
        val response = TravelCostResponse(result = 25.0)

        coEvery {
            dataSource.calculate(request)
        } returns Response.Success(response)

        vm.updateInfo(distance = request.distance.toString())
        vm.updateInfo(price = request.price.toString())
        vm.updateInfo(autonomy = request.autonomy.toString())
        vm.calculate()

        val state = vm.state.value

        state.distance shouldBe request.distance.toString()
        state.price shouldBe request.price.toString()
        state.autonomy shouldBe request.autonomy.toString()
        state.result shouldBe response.result.toString()
        state.isError shouldBe false
    }

    @Test
    fun `should update distance info`() {
        val distance = "100"

        vm.updateInfo(distance = distance)

        val state = vm.state.value

        state.distance shouldBe distance
        state.price shouldBe ""
        state.autonomy shouldBe ""
    }

    @Test
    fun `should update price info`() {
        val price = "100"

        vm.updateInfo(price = price)

        val state = vm.state.value

        state.distance shouldBe ""
        state.price shouldBe price
        state.autonomy shouldBe ""
    }

    @Test
    fun `should update autonomy info`() {
        val autonomy = "100"

        vm.updateInfo(autonomy = autonomy)

        val state = vm.state.value

        state.distance shouldBe ""
        state.price shouldBe ""
        state.autonomy shouldBe autonomy
    }
}