package no.agens.dugnadsgjengen

import android.app.Application
import timber.log.Timber

class DgApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}