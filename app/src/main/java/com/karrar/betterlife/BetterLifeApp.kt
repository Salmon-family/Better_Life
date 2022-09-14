package com.karrar.betterlife

import android.app.Application
import android.content.Context

class BetterLifeApp  : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: BetterLifeApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }


}