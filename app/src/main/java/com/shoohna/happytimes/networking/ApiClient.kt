import com.shoohna.happytimes.networking.interfaces.ups

import com.shoohna.happytimes.upsTask.ui.titles.TitelsViewModel
import com.shoohna.happytimes.util.base.SharedHelper
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {
    const val BASE_URL_UPS = "http://api.nytimes.com/svc/mostpopular/v2/"

    val upsModule = module {
        fun makeRetrofitService(): ups {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_UPS)
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .build().create(ups::class.java)
        }

        single { makeRetrofitService() }
    }
    val titlesServiceModuleMainActivity = module { viewModel { TitelsViewModel(get()) } }











}