package kg.iuca.myrecipeapp.network

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Category(
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strCategoryThumb") val strCategoryThumb: String
)

data class CategoriesResponse(
    @SerializedName("categories") val categories: List<Category>
)

interface RecipeService {
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    companion object {
        fun create(): RecipeService {
            return Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RecipeService::class.java)
        }
    }
}