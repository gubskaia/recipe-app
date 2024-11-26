package kg.iuca.myrecipeapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.iuca.myrecipeapp.network.Category
import kg.iuca.myrecipeapp.network.RecipeService
import kotlinx.coroutines.launch

/**
 * Состояние экрана рецептов.
 * @property loading Флаг, указывающий, что данные загружаются.
 * @property list Список категорий рецептов.
 * @property error Сообщение об ошибке, если она произошла.
 */
data class RecipeState(
    val loading: Boolean = true, // По умолчанию данные загружаются
    val list: List<Category> = emptyList(), // Пустой список категорий
    val error: String? = null // Сообщение об ошибке, если она есть
)

/**
 * ViewModel для управления состоянием и логикой экрана рецептов.
 */
class MainViewModel : ViewModel() {
    // Внутреннее состояние категорий, которое может изменяться
    private val _categoriesState = mutableStateOf(RecipeState())

    // Публичное неизменяемое состояние для наблюдения в UI
    val categoriesState: State<RecipeState> = _categoriesState

    // Экземпляр RecipeService для работы с API
    private val recipeService = RecipeService.create()

    // Инициализация ViewModel: вызов функции для загрузки данных
    init {
        fetchCategories()
    }

    /**
     * Функция для загрузки списка категорий с использованием RecipeService.
     * Результат обновляет состояние _categoriesState.
     */
    private fun fetchCategories() {
        // Выполняем запрос в фоновом потоке с использованием viewModelScope
        viewModelScope.launch {
            try {
                // Получаем ответ от API
                val response = recipeService.getCategories()

                // Обновляем состояние с загруженными категориями
                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                // Обновляем состояние в случае ошибки
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching categories: ${e.message}"
                )
            }
        }
    }
}
