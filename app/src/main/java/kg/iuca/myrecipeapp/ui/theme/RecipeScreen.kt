package kg.iuca.myrecipeapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import coil.compose.rememberAsyncImagePainter
import kg.iuca.myrecipeapp.network.Category
import kg.iuca.myrecipeapp.viewmodel.RecipeState

/**
 * Главный экран рецептов, который показывает состояние загрузки,
 * ошибки или список категорий рецептов.
 * @param viewState Состояние экрана, которое может содержать данные, ошибку или состояние загрузки.
 * @param navigateToDetail Функция для навигации на экран подробностей категории.
 * @param modifier Модификатор для кастомизации расположения и поведения.
 */
@Composable
fun RecipeScreen(
    viewState: RecipeState,
    navigateToDetail: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    // Оборачиваем экран в Box для позиционирования элементов на экране
    Box(modifier = Modifier.fillMaxSize()) {
        // В зависимости от состояния отображаем:
        // 1. Индикатор загрузки
        // 2. Сообщение об ошибке
        // 3. Экран с категориями
        when {
            viewState.loading -> {
                // Круглый индикатор загрузки, выравнивание по центру
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                // Если произошла ошибка, выводим сообщение
                Text(
                    text = "Error occurred: ${viewState.error}",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Red
                )
            }
            else -> {
                // Если данные загружены успешно, показываем список категорий
                CategoryScreen(categories = viewState.list, navigateToDetail = navigateToDetail)
            }
        }
    }
}

/**
 * Экран, отображающий список категорий в виде сетки.
 * @param categories Список категорий для отображения.
 * @param navigateToDetail Функция для навигации на экран подробностей категории.
 */
@Composable
fun CategoryScreen(categories: List<Category>, navigateToDetail: (Category) -> Unit) {
    // Используем LazyVerticalGrid для отображения категорий в виде сетки
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        // Для каждой категории отображаем элемент CategoryItem
        items(categories) { category ->
            CategoryItem(category = category, navigateToDetail = navigateToDetail)
        }
    }
}

/**
 * Элемент сетки, отображающий изображение и название категории.
 * @param category Категория, которую нужно отобразить.
 * @param navigateToDetail Функция для навигации на экран подробностей категории.
 */
@Composable
fun CategoryItem(category: Category, navigateToDetail: (Category) -> Unit) {
    // Контейнер для каждого элемента категории с кликабельностью
    Column(
        modifier = Modifier
            .padding(8.dp) // Отступы вокруг элемента
            .fillMaxSize() // Заполняет всю доступную площадь
            .clickable { navigateToDetail(category) }, // Навигация при клике
        horizontalAlignment = Alignment.CenterHorizontally // Выравнивание элементов по горизонтали
    ) {
        // Отображаем изображение категории с сохранением соотношения сторон 1:1
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null, // Описание изображения отсутствует
            modifier = Modifier
                .fillMaxSize() // Заполнение всего доступного пространства
                .aspectRatio(1f) // Соотношение сторон 1:1 (квадрат)
        )
        // Отображаем название категории
        Text(
            text = category.strCategory,
            color = Color.Black, // Цвет текста
            style = TextStyle(fontWeight = FontWeight.Bold), // Жирный шрифт
            modifier = Modifier.padding(top = 4.dp) // Отступ сверху
        )
    }
}
