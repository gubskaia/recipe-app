package kg.iuca.myrecipeapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.iuca.myrecipeapp.network.Category
import kg.iuca.myrecipeapp.network.RecipeService
import kotlinx.coroutines.launch

data class RecipeState(
    val loading: Boolean = true,
    val list: List<Category> = emptyList(),
    val error: String? = null
)

class MainViewModel : ViewModel() {
    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    private val recipeService = RecipeService.create()

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching categories: ${e.message}"
                )
            }
        }
    }
}