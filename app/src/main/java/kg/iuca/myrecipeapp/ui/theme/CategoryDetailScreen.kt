package kg.iuca.myrecipeapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kg.iuca.myrecipeapp.network.Category

/**
 * Экран, который отображает подробности о выбранной категории.
 * На экране показываются:
 * - Название категории.
 * - Изображение категории.
 * - Описание категории (если оно есть).
 * @param category Объект категории, содержащий все данные, которые будут отображены на экране.
 */
@Composable
fun CategoryDetailScreen(category: Category) {
    // Главный контейнер с вертикальным прокручиванием и внутренними отступами
    Column(
        modifier = Modifier
            .fillMaxSize() // Занимает всю доступную площадь экрана
            .padding(16.dp) // Отступы по бокам для улучшения восприятия контента
            .verticalScroll(rememberScrollState()), // Вертикальная прокрутка, если контент превышает размер экрана
        horizontalAlignment = Alignment.CenterHorizontally // Выравнивание содержимого по центру по горизонтали
    ) {
        // Отображение названия категории
        Text(
            text = category.strCategory, // Текст названия категории
            textAlign = TextAlign.Center, // Выравнивание текста по центру
            modifier = Modifier.padding(bottom = 16.dp) // Отступ снизу от текста для отделения от следующего контента
        )

        // Отображение изображения категории
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb), // Используется библиотека Coil для асинхронной загрузки изображения
            contentDescription = "${category.strCategory} Thumbnail", // Описание изображения для доступности
            modifier = Modifier
                .wrapContentSize() // Контейнер для изображения с размером, соответствующим содержимому
                .aspectRatio(1f) // Соотношение сторон 1:1, изображение будет квадратным
        )

        // Отображение описания категории (если оно есть)
        Text(
            text = category.strCategoryDescription ?: "Описание категории отсутствует.", // Если описание есть, показываем его, если нет - текст по умолчанию
            textAlign = TextAlign.Justify, // Выравнивание текста по ширине
            modifier = Modifier.padding(top = 16.dp) // Отступ сверху от текста для отделения от изображения
        )
    }
}
