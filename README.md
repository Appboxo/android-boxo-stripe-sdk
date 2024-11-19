## Stripe Payment for Appboxo SDK

### Getting started

Add the below code to your app `build.gradle` file (not your root build.gradle file).

more about [Appboxo SDK]([https://github.com/Appboxo/sample-android-hostapp](https://github.com/Appboxo/android-sdk-packages))

```gradle
dependencies {
    implementation 'com.appboxo:sdk:x.x.x'
    implementation 'com.appboxo:stripe:x.x'
}
```

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
        maven {
            url "https://maven.pkg.github.com/Appboxo/android-sdk-packages"
            credentials {
                username = "appboxoandroidsdk"
                password = "\u0037\u0039\u0064\u0031\u0065\u0064\u0061\u0036\u0030\u0034\u0063\u0061\u0031\u0066\u0030\u0032\u0066\u0031\u0037\u0066\u0032\u0061\u0039\u0033\u0064\u0035\u0039\u0039\u0061\u0035\u0035\u0062\u0066\u0065\u0031\u0064\u0066\u0064\u0038\u0038"
            }
        }
    }
}
```


Init Appboxo and Stripe SDKs in your Application class.

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Appboxo.init(this)
            .setConfig(
                Config.Builder()
                    .setClientId("CLIENT_ID")
                    .build()
            )
        AppboxoStripe.init(
            this,
            "[stripe_publishable_key]",
            "App name" // for Stripe PaymentSheet
        )
    }
}
```

Open miniapp on your Activity

```kotlin
 Appboxo.getMiniapp("[app_id]")
                .setPaymentEventListener { appboxoActivity, miniapp, paymentData ->
                    AppboxoStripe.handleStripePayment(appboxoActivity, miniapp, paymentData)
                }
                .setAuthListener { appboxoActivity, miniapp ->
                    .. get auth_code from your backend
                    miniapp.setAuthCode("[auth_code]")
                }
                .open(activity)
```
