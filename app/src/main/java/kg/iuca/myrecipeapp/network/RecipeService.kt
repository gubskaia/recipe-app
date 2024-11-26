package kg.iuca.myrecipeapp.network

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Модель данных для категории рецептов.
 * @property strCategory Название категории.
 * @property strCategoryThumb URL изображения категории.
 */
data class Category(
    @SerializedName("strCategory") val strCategory: String, // Поле из JSON соответствует "strCategory"
    @SerializedName("strCategoryThumb") val strCategoryThumb: String // Поле из JSON соответствует "strCategoryThumb"
)

/**
 * Модель ответа API, содержащая список категорий.
 * @property categories Список объектов [Category], полученных из API.
 */
data class CategoriesResponse(
    @SerializedName("categories") val categories: List<Category> // Поле из JSON соответствует "categories"
)

/**
 * Интерфейс сервиса для взаимодействия с API рецептов.
 */
interface RecipeService {
    /**
     * Метод для получения списка категорий.
     * @return Объект [CategoriesResponse] с категориями.
     */
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse

    companion object {
        /**
         * Фабричный метод для создания экземпляра [RecipeService].
         * @return Экземпляр [RecipeService], настроенный для работы с API.
         */
        fun create(): RecipeService {
            return Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/") // Базовый URL API
                .addConverterFactory(GsonConverterFactory.create()) // Добавляем конвертер для обработки JSON
                .build()
                .create(RecipeService::class.java) // Создаем реализацию интерфейса
        }
    }
}
