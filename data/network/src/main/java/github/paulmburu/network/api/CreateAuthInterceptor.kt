package github.paulmburu.network.api

import github.paulmburu.network.BuildConfig
import github.paulmburu.network.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class CreateAuthInterceptor :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(Constants.HttpHeaders.AUTHORIZATION, BuildConfig.ACCESS_TOKEN)
            .build()

        return chain.proceed(request)
    }
}