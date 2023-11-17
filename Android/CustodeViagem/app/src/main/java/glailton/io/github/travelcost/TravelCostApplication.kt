package glailton.io.github.travelcost

import android.app.Application
import glailton.io.github.travelcost.core.di.travelCostModules
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TravelCostApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@TravelCostApplication)
            modules(travelCostModules)
        }
    }
}