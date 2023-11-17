using TravelCost.Domain.Commands.Requests;

namespace TravelCost.Tests.Commons.Builders.Requests;

public class TravelCostRequestBuilder
{
    public double Distance = 100;
    public double Price = 5;
    public double Autonomy = 10;

    public TravelCostRequest Build() =>
        new TravelCostRequest
        {
            Distance = Distance,
            Price = Price,
            Autonomy = Autonomy
        };
}