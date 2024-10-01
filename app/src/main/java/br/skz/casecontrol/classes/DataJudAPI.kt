package br.skz.casecontrol.classes

import ImportantDates
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DataJudApi {
    @GET("process/{processNumber}/dates")
    suspend fun getImportantDates(@Path("processNumber") processNumber: String): List<ImportantDates>
}

object RetrofitInstance {
    val api: DataJudApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://datajud-api.example.com/") // substitua pela URL correta
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataJudApi::class.java)
    }
}