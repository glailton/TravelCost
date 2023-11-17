using System.Threading;
using System.Threading.Tasks;
using MediatR;
using TravelCost.Domain.Commands.Requests;
using TravelCost.Domain.Commands.Responses;

namespace TravelCost.Domain.Commands.Handler
{
    public class TravelCostHandler : IRequestHandler<TravelCostRequest, TravelCostResponse>
    {
        public Task<TravelCostResponse> Handle(TravelCostRequest request, CancellationToken cancellationToken)
        {
            var distance = request.Distance;
            var price = request.Price;
            var autonomy = request.Autonomy;

            var result = (distance * price) / autonomy;

            var response = new TravelCostResponse
            {
                Result = result
            };

            return Task.FromResult(response);
        }
    }
}