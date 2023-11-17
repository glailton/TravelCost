using MediatR;
using TravelCost.Domain.Commands.Responses;

namespace TravelCost.Domain.Commands.Requests
{
    public class TravelCostRequest : IRequest<TravelCostResponse>
    {
        public double Distance { get; set; }
        public double Price { get; set; }
        public double Autonomy { get; set; }
    }
}