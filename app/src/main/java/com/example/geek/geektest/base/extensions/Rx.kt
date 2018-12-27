package com.example.geek.geektest.base.extensions

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.runInBackground(): Single<T> = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun Completable.runInBackground(): Completable = subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())