# Hilt MVVM with ViewBinding

This Android app demonstrates the use of Hilt and MVVM architecture principles with ViewBinding. The app fetches and displays data from a RESTful Web API. It supports both portrait and landscape orientations on both phones and tablets.

## Features

- Hilt for dependency injection
- MVVM architecture principles
- ViewBinding for view access
- Retrofit for API calls
- RecyclerView for displaying character list
- Search functionality to filter character list
- Detail view for each character, including image, title, and description
- Support for multiple variants with shared codebase

## Installation

To run the app, follow these steps:

1. Clone the project repository to your local machine.
2. Open the project in Android Studio.
3. Build the project and run the app on an emulator or physical device.

## Usage

The app consists of two parts: a character list and a character detail view. On phones, the list and detail are separate screens, while on tablets, they appear on the same screen.

The character list displays character names in a vertically scrollable list. You can use the search functionality to filter the list based on character titles or descriptions.

Clicking on a character in the list will load the detail view of that character. The detail view includes the character's image, title, and description.

## Variants

The app supports multiple variants with different data sources. Each variant has a different name, package name, and URL for fetching data. You can create new variants by modifying the configuration files.

To create a new variant:
1. Duplicate the existing configuration file for a variant (e.g., `variant1.properties`) and rename it.
2. Update the variant name, package name, and API URL in the new configuration file.
3. Build and run the app with the new variant selected.

## Additional Notes

- The app is written in Kotlin, utilizing ViewBinding for view access.
- The code follows MVVM architecture principles, with separation of concerns and data binding.
- Hilt is used for dependency injection, providing a clean and modular architecture.
- Retrofit is used for making API calls, allowing seamless data retrieval from a RESTful Web API.
- RecyclerView is used to efficiently display the character list, supporting both vertical and horizontal scrolling.
- The app provides search functionality to filter characters based on their titles or descriptions.
- The app supports different screen orientations and adapts its layout accordingly.
- You can customize the app by creating new variants with different data sources.
- Open-source libraries can be used, but please ensure the project remains buildable and runnable in the IDE.

Feel free to reach out if you have any questions or need further clarification.
