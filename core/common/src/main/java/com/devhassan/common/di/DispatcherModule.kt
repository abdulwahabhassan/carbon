package com.devhassan.common.di

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesDispatcherIO(
        @ApplicationContext appContext: Context
    ): CoroutineDispatcher {
        return Dispatchers.IO
    }
}