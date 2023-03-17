package com.fatec.solidariza

import android.app.Application
import com.fatec.solidariza.data.AppContainer
import com.fatec.solidariza.data.AppDataContainer

class SolidarizaApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}