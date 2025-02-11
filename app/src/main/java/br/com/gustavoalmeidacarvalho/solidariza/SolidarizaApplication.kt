package br.com.gustavoalmeidacarvalho.solidariza

import android.app.Application
import br.com.gustavoalmeidacarvalho.solidariza.data.AppContainer
import br.com.gustavoalmeidacarvalho.solidariza.data.AppDataContainer

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