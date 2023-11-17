package glailton.io.github.travelcost.ui.presentation.screens.home

data class HomeState(
    val distance: String = "",
    val price: String = "",
    val autonomy: String = "",
    val result: String = "0",
    val isError: Boolean = false
) {
    fun withDistance(distance: String) = copy(distance = distance)
    fun withPrice(price: String) = copy(price = price)
    fun withAutonomy(autonomy: String) = copy(autonomy = autonomy)
}