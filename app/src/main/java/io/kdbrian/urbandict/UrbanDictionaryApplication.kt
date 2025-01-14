package io.kdbrian.urbandict

import android.app.Application
import com.google.firebase.FirebaseApp

class UrbanDictionaryApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}