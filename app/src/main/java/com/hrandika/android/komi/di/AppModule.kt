package com.hrandika.android.komi.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hrandika.android.komi.data._common.AppDatabase
import com.hrandika.android.komi.data.carrier.local.CarrierDao
import com.hrandika.android.komi.data.carrier.repository.CarrierRepository
import com.hrandika.android.komi.data.carrier.rest.CarrierRestApi
import com.hrandika.android.komi.data.carrier.rest.CarrierService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    // ------- REST API -------

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.3:8080/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideCarrierService(retrofit: Retrofit): CarrierService =
        retrofit.create(CarrierService::class.java)

    @Provides
    fun provideCarrierRestApi(carrierService: CarrierService): CarrierRestApi =
        CarrierRestApi(carrierService)

    // ------- Database -------

    @Singleton
    @Provides
    fun database(@ApplicationContext appContext: Context): AppDatabase =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun carrierDao(db: AppDatabase): CarrierDao = db.carrierDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: CarrierRestApi,
        localDataSource: CarrierDao
    ) =
        CarrierRepository(remoteDataSource, localDataSource)
}