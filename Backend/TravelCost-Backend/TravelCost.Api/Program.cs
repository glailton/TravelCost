using System.Net.NetworkInformation;
using System.Reflection;
using MediatR;
using Serilog;
using TravelCost.Api;
using TravelCost.Domain.Commands.Requests;
using TravelCost.Domain.Commands.Responses;
using TravelCost.Infrastructure;

var builder = WebApplication.CreateBuilder(args);
builder.Services
    .AddApplication()
    .AddInfrastructure()
    .AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddMediatR(cfg =>
{
    cfg.RegisterServicesFromAssemblies(typeof(TravelCostRequest).Assembly, typeof(TravelCostResponse).Assembly);
});

builder.Host.UseSerilog((context, configuration) => 
    configuration.ReadFrom.Configuration(context.Configuration));

var app = builder.Build();

app.UseSerilogRequestLogging();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();

app.Run();