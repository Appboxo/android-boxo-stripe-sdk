## Stripe Payment for Boxo SDK

### Getting started

Add the below code to your app `build.gradle` file (not your root build.gradle file).

more about [Boxo SDK](https://boxo.mintlify.app/host-apps/BoxoSDK#android-sdk)

Latest version: ![Maven Central Version](https://img.shields.io/maven-central/v/io.boxo.sdk/boxo-stripe)

```gradle
dependencies {
    implementation 'io.boxo.sdk:boxo-android:x.x.x'
    implementation 'io.boxo.sdk:boxo-stripe:x.x.x'
}
```

Init Boxo and Boxo-Stripe SDKs in your Application class.

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Boxo.init(this)
            .setConfig(
                Config.Builder()
                    .setClientId("CLIENT_ID")
                    .build()
            )
        BoxoStripe.init(
            this,
            "[stripe_publishable_key]",
            "App name" // for Stripe PaymentSheet
        )
    }
}
```

Open miniapp on your Activity

```kotlin
 Boxo.getMiniapp("[app_id]")
                .setPaymentEventListener { boxoActivity, miniapp, paymentData ->
                    AppboxoStripe.handleStripePayment(boxoActivity, miniapp, paymentData)
                }
                .setAuthListener { boxoActivity, miniapp ->
                    // get auth_code from your backend
                    miniapp.setAuthCode("[auth_code]")
                }
                .open(activity)
```
