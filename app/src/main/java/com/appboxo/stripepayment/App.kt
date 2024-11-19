package com.appboxo.stripepayment

import android.app.Application
import com.appboxo.sdk.Appboxo
import com.appboxo.sdk.Config
import com.appboxo.stripe.AppboxoStripe

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Appboxo.init(this)
            .setConfig(
                Config.Builder()
                    .setClientId("client_id")
                    .build()
            )
        AppboxoStripe.init(
            this,
            publishableKey = "publishable_key",
            displayName = "HostAppName"
        )
    }
}