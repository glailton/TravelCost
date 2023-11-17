package glailton.io.github.travelcost.ui.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import glailton.io.github.travelcost.core.data.Response
import glailton.io.github.travelcost.core.data.repository.RemoteDataSource
import glailton.io.github.travelcost.domain.remote.request.TravelCostRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val dataSource: RemoteDataSource) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun calculate() {
        viewModelScope.launch {
            val state = state.value

            if (isValid()) {
                val request = TravelCostRequest(
                    distance = state.distance.toDouble(),
                    price = state.price.toDouble(),
                    autonomy = state.autonomy.toDouble()
                )

                when (val result = dataSource.calculate(request)) {
                    is Response.Success -> {
                        _state.update { it.copy(result = result.data.result.toString(), isError = false) }
                    }
                    is Response.Error -> {
                        _state.update { it.copy(isError = true) }
                    }
                }
            } else {
                _state.update { it.copy(isError = true) }
            }
        }
    }

    fun updateInfo(distance: String = "", price: String = "", autonomy: String = "") {
        when {
            distance.isNotEmpty() -> _state.update { it.withDistance(distance) }
            price.isNotEmpty() -> _state.update { it.withPrice(price) }
            autonomy.isNotEmpty() -> _state.update { it.withAutonomy(autonomy) }
        }
    }

    private fun isValid(): Boolean {
        return try {
            val distance = state.value.distance.toDouble()
            val price = state.value.price.toDouble()
            val autonomy = state.value.autonomy.toDouble()

            distance >= 0 && price >= 0 && autonomy >= 0
        } catch (e: Exception) {
            false
        }
    }
}