package com.appboxo.stripe

import android.app.Application
import com.appboxo.js.params.PaymentData
import com.appboxo.sdk.Miniapp
import com.appboxo.ui.main.AppboxoActivity
import com.stripe.android.PaymentConfiguration

object AppboxoStripe {
    var merchantDisplayName: String = ""

    fun init(
        application: Application,
        publishableKey: String,
        displayName: String
    ) {
        merchantDisplayName = displayName
        PaymentConfiguration.init(application, publishableKey)
    }

    fun handleStripePayment(activity: AppboxoActivity, miniapp: Miniapp, paymentData: PaymentData) {
        StripePaymentDialog().apply {
            this.miniapp = miniapp
            this.paymentData = paymentData
        }.show(activity.supportFragmentManager, "appboxo_stripe_payment")
    }
}