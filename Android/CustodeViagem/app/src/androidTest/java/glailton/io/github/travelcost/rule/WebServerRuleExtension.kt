package glailton.io.github.travelcost.rule

import glailton.io.github.travelcost.domain.remote.response.TravelCostResponse

fun WebServerRule.mockTravelCostResponse() {
    enqueueResponse(
        TravelCostResponse(20.0)
    )
}