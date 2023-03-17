/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fatec.solidariza.data

import android.content.Context
import com.fatec.solidariza.data.network.SolidarizaApiService
import com.fatec.solidariza.data.repository.SolidarizaRepositoryImpl
import com.fatec.solidariza.data.repository.SolidarizaRepository
import com.fatec.solidariza.data.room.SolidarizaDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val solidarizaRepository: SolidarizaRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    private val baseUrl =
        "https://solidariza-api-production.up.railway.app/"
//        "http://192.168.0.21:8080/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: SolidarizaApiService by lazy {
        retrofit.create(SolidarizaApiService::class.java)
    }

    override val solidarizaRepository: SolidarizaRepository by lazy {
        SolidarizaRepositoryImpl(
            retrofitService,
            SolidarizaDatabase.getDatabase(context).solidarizaDao()
        )
    }
}