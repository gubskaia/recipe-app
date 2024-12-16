package kg.iuca.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kg.iuca.myrecipeapp.network.Category
import kg.iuca.myrecipeapp.ui.theme.CategoryDetailScreen
import kg.iuca.myrecipeapp.ui.theme.RecipeScreen
import kg.iuca.myrecipeapp.viewmodel.MainViewModel

/**
 * Главный компонент приложения RecipeApp, который управляет навигацией
 * между экранами с помощью NavHostController.
 * @param navController Контроллер навигации, который управляет переходами между экранами.
 */
@Composable
fun RecipeApp(navController: NavHostController) {
    // Получаем состояние категорий из ViewModel
    val recipeViewModel: MainViewModel = viewModel() // Инициализация ViewModel
    val viewState = recipeViewModel.categoriesState.value // Состояние категорий (данные или ошибки)

    // Навигационный хост, управляющий экранами
    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        // Первый экран (экран рецептов), который отображает список категорий
        composable(route = Screen.RecipeScreen.route) {
            // Передаем состояние и функцию для навигации к экрану деталей категории
            RecipeScreen(viewState = viewState, navigateToDetail = {
                // Сохраняем выбранную категорию в состояние навигации
                navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                // Переходим к экрану деталей категории
                navController.navigate(Screen.DetailScreen.route)
            })
        }

        // Экран деталей категории
        composable(route = Screen.DetailScreen.route) {
            // Получаем категорию из сохраненного состояния навигации
            val category = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Category>("category") ?: Category() // Если категория не найдена, создаем пустой объект
            // Отображаем экран с деталями выбранной категории
            CategoryDetailScreen(category = category)
        }
    }
}
