using TravelCost.Domain.Commands.Requests;
using TravelCost.Domain.Commands.Responses;

namespace TravelCost.Tests.Commons.Builders.Responses;

public class TravelCostResponseBuilder
{
    public double Result = 10;

    public TravelCostResponse Build() =>
        new TravelCostResponse
        {
            Result = Result
        };
}