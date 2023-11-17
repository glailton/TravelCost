package glailton.io.github.travelcost.core.data.repository

import glailton.io.github.travelcost.core.data.Response
import glailton.io.github.travelcost.core.data.remote.TravelCostApi
import glailton.io.github.travelcost.domain.remote.request.TravelCostRequest
import glailton.io.github.travelcost.domain.remote.response.TravelCostResponse

class RemoteDataSourceImpl(private val api: TravelCostApi): RemoteDataSource {
    override suspend fun calculate(request: TravelCostRequest): Response<TravelCostResponse> {
        return try {
            val result = api.calculate(request)
            Response.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e)
        }
    }
}