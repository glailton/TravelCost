package glailton.io.github.travelcost.core.di

import glailton.io.github.travelcost.core.data.repository.RemoteDataSource
import glailton.io.github.travelcost.core.data.repository.RemoteDataSourceImpl
import glailton.io.github.travelcost.ui.presentation.screens.home.HomeViewModel
import glailton.io.github.travelcost.utils.ApiEndpoint
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val travelCostModules = module {
    single { ApiEndpoint() }
    factory { provideHttpClient() }
    factory { provideTravelCostApi(get()) }
    single { provideRetrofit(get(), get()) }
    factory { RemoteDataSourceImpl(get()) }

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    viewModelOf(::HomeViewModel)
}