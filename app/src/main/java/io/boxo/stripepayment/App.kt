package io.boxo.stripepayment

import android.app.Application
import io.boxo.sdk.Boxo
import io.boxo.sdk.Config
import io.boxo.stripe.BoxoStripe

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Boxo.init(this)
            .setConfig(
                Config.Builder()
                    .setClientId("client_id")
                    .build()
            )
        BoxoStripe.init(
            this,
            publishableKey = "publishable_key",
            displayName = "HostAppName"
        )
    }
}