package glailton.io.github.travelcost.domain.remote.request

data class TravelCostRequest(
    val distance: Double,
    val price: Double,
    val autonomy: Double
)