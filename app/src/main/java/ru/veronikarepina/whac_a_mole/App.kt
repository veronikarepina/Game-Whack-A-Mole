package ru.veronikarepina.whac_a_mole

import android.app.Application
import ru.veronikarepina.whac_a_mole.data.DataObject

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        DataObject.initData(this)
    }
}