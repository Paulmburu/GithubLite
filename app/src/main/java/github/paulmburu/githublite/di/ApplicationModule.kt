package github.paulmburu.githublite.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.paulmburu.githublite.util.ConnectivityProvider

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun providesConnectionProvider(@ApplicationContext context: Context): ConnectivityProvider {
        return ConnectivityProvider(context)
    }
}