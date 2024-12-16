package kg.iuca.myrecipeapp

/**
 * Класс Screen представляет маршруты для экрана в навигации.
 * Это sealed class, который используется для определения экранов и их путей.
 */
sealed class Screen(val route: String) {
    // Экран рецептов, на котором отображается список категорий рецептов. Это главный экран приложения.

    object RecipeScreen : Screen("recipescreen")


     // Экран подробностей категории, где отображается информация о выбранной категории.
    object DetailScreen : Screen("detailscreen")
}
