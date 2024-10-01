import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class ImportantDates(
    val date: String,
    val description: String,
    val status: String
)



