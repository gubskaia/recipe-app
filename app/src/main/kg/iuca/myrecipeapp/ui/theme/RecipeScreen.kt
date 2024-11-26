package kg.iuca.myrecipeapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import kg.iuca.myrecipeapp.network.Category
import kg.iuca.myrecipeapp.viewmodel.MainViewModel

/**
 * Главный экран рецептов, который показывает состояние загрузки,
 * ошибки или список категорий рецептов.
 * @param modifier Модификатор для кастомизации расположения и поведения.
 */
@Composable
fun RecipeScreen(modifier: Modifier = Modifier) {
    // Подключаем ViewModel для получения состояния категорий
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    // Box для размещения элементов поверх друг друга
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            // Показать индикатор загрузки, если данные еще загружаются
            viewState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            // Показать сообщение об ошибке, если загрузка завершилась с ошибкой
            viewState.error != null -> {
                Text(
                    text = "Error occurred: ${viewState.error}",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Red
                )
            }
            // Показать список категорий, если данные успешно загружены
            else -> {
                CategoryScreen(categories = viewState.list)
            }
        }
    }
}

/**
 * Экран отображения списка категорий в виде сетки.
 * @param categories Список категорий для отображения.
 */
@Composable
fun CategoryScreen(categories: List<Category>) {
    // LazyVerticalGrid для создания прокручиваемой сетки с фиксированным количеством колонок
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        // Для каждого элемента списка категорий создаем CategoryItem
        items(categories) { category ->
            CategoryItem(category = category)
        }
    }
}

/**
 * Карточка категории, которая отображает изображение и название категории.
 * @param category Объект категории, содержащий данные для отображения.
 */
@Composable
fun CategoryItem(category: Category) {
    // Колонка для вертикального расположения элементов
    Column(
        modifier = Modifier
            .padding(8.dp) // Отступы вокруг карточки
            .fillMaxSize(), // Заполнение доступного пространства
        horizontalAlignment = Alignment.CenterHorizontally // Центровка по горизонтали
    ) {
        // Изображение категории
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb), // Загрузка изображения через Coil
            contentDescription = null, // Контентное описание (можно добавить для accessibility)
            modifier = Modifier
                .fillMaxSize() // Заполнение доступного пространства
                .aspectRatio(1f) // Соотношение сторон 1:1
        )
        // Текст с названием категории
        Text(
            text = category.strCategory, // Название категории
            color = Color.Black, // Цвет текста
            style = TextStyle(fontWeight = FontWeight.Bold), // Жирный текст
            modifier = Modifier.padding(top = 4.dp) // Отступ сверху
        )
    }
}
