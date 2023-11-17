using MediatR;
using Microsoft.AspNetCore.Mvc;
using TravelCost.Domain.Commands.Handler;
using TravelCost.Domain.Commands.Requests;
using TravelCost.Domain.Commands.Responses;

namespace TravelCost.Api.Controllers;

[ApiController]
[Route("[controller]")]
[ProducesResponseType(401)]
public class TravelCostController : ControllerBase
{
    private readonly IMediator _mediator;

    public TravelCostController(IMediator mediator)
    {
        _mediator = mediator;
    }

    [HttpPost("calculate")]
    public Task<TravelCostResponse> Calculate([FromBody] TravelCostRequest request)
    {
        return _mediator.Send(request);
    }
}