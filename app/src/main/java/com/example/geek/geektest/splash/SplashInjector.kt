package com.example.geek.geektest.splash

import android.content.Context
import com.example.geek.geektest.database.DbEmulator

class SplashInjector {

    fun inject(view: SplashContract.View, context: Context): SplashContract.Presenter =
        SplashPresenter(view, DbEmulator.getInstance(context))
}
