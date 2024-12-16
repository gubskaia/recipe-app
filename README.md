# MyRecipeApp

MyRecipeApp is an Android application built using Jetpack Compose, Retrofit, and Kotlin. It allows users to browse a list of recipe categories fetched from an external API, view detailed information about each category, and display them in a grid layout.

## Demo (gif)
<img src="/app/src/main/res/demo-recipeapp.gif" alt="Example GIF" width="200" />

## Features

- Displays a list of recipe categories.
- Each category includes an image and its name.
- Fetches data from an external API using Retrofit.
- Built with Jetpack Compose for UI rendering.
- **New Feature**: Added a detailed category screen that displays the category name, image, and description when a user selects a category from the list.

## Screens

### Recipe List Screen
Displays a grid of recipe categories with an image and name for each category.

### Category Detail Screen
When a user taps on a category, they are taken to a detail screen that shows:
- The category's name.
- The category's image.
- The category's description (if available).

## Technologies Used

- **Kotlin**: The programming language used for Android development.
- **Jetpack Compose**: Modern UI toolkit for Android.
- **Retrofit**: A type-safe HTTP client for Android to fetch data from a remote API.
- **Gson**: Library for JSON serialization and deserialization.
- **Coil**: For image loading and displaying images in Compose.

## Installation

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/gubskaia/recipe-app.git
2. Open the project in Android Studio.
3. Build the project.
4. Run the app on an emulator or a physical device.

## API Endpoints

The app fetches recipe categories from the following API endpoint:
- **GET** https://www.themealdb.com/api/json/v1/1/categories.php
