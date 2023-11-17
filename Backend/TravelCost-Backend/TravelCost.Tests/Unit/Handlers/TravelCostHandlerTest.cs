using Shouldly;
using TravelCost.Domain.Commands.Handler;
using TravelCost.Tests.Commons.Builders.Requests;
using TravelCost.Tests.Commons.Builders.Responses;
using Xunit;

namespace TravelCost.Tests.Unit.Handlers
{
    public class TravelCostHandlerTest
    {
        [Fact]
        public void Should_return_response_correctly()
        {
            //Arrange
            var request = new TravelCostRequestBuilder().Build();
            var result = new TravelCostResponseBuilder().Build();
            
            //Act
            var response = new TravelCostHandler().Handle(request, new CancellationToken());
            
            //Assert
            response.Result.Result.ShouldBe(50);
        }
    }
}
