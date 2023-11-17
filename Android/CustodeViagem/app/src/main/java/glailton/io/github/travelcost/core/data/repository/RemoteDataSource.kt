package glailton.io.github.travelcost.core.data.repository

import glailton.io.github.travelcost.core.data.Response
import glailton.io.github.travelcost.domain.remote.request.TravelCostRequest
import glailton.io.github.travelcost.domain.remote.response.TravelCostResponse

interface RemoteDataSource {
    suspend fun calculate(request: TravelCostRequest): Response<TravelCostResponse>
}