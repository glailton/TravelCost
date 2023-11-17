using System.Reflection;
using FluentValidation;
using MediatR;
using TravelCost.Domain.Commands.Handler;

namespace TravelCost.Api;

public static class DependencyInjection
{
    public static IServiceCollection AddApplication(this IServiceCollection services)
    {
        var assembly = typeof(DependencyInjection).Assembly;
        services.AddValidatorsFromAssembly(assembly);
        return services;
    }
}