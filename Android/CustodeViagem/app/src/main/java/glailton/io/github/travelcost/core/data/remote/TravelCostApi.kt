package glailton.io.github.travelcost.core.data.remote

import glailton.io.github.travelcost.domain.remote.request.TravelCostRequest
import glailton.io.github.travelcost.domain.remote.response.TravelCostResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TravelCostApi {

    @POST("calculate")
    suspend fun calculate(@Body request: TravelCostRequest): TravelCostResponse
}