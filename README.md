# StockWatcher: An Interview Project

## Welcome, Candidate!

Welcome to the StockWatcher project. This repository is designed as a practical exercise for our Android engineering interviews. Its purpose is to create a realistic, collaborative environment where we can work together to understand, debug, and enhance an existing codebase.

This project intentionally contains a mix of good practices and common bugs, performance issues, and stylistic inconsistencies. We don't expect you to find every single flaw, but we are keen to see your thought process, how you approach debugging, and how you articulate your solutions.

Please feel free to ask questions at any point. Our goal is to have a technical discussion that feels like a typical pair programming session.

## Project Overview

StockWatcher is a simple application that displays a list of stocks with their ticker symbol, company name, and current price. The app is built using modern Android development tools and libraries, providing a good foundation for demonstrating your skills.

## Technical Deep Dive

This project is built with a modern Android tech stack. Familiarity with these technologies will be beneficial.

*   **Kotlin:** The entire project is written in idiomatic Kotlin.
*   **Jetpack Compose:** The UI is built declaratively using Jetpack Compose.
*   **MVVM Architecture:** The Model-View-ViewModel pattern is used for a clean separation of concerns.
*   **ViewModel:** Jetpack ViewModel is used to manage and store UI-related data.
*   **Coroutines & Flow:** Used for managing asynchronous operations and data streams.
*   **Hilt:** Used for dependency injection.

## Your Task

Your task is divided into several phases. We will guide you through each one.

### Phase 1: Architectural Review & Discussion

The first phase Assess the candidate's ability to think systemically and connect flaws across different architectural layers.
* The Data Layer
* The Logic/State Layer
* The UI Layer
### Phase 2: Prioritization & Hands-On Coding

Assess prioritization skills, hands-on coding ability, and code quality.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

*   Android Studio Iguana | 2023.2.1 or newer.
*   Android SDK targeting API level 34.

### Installation

1.  Clone the repo:
    ```sh
    git clone https://github.com/your_username_/StockWatcher.git
    ```
2.  Open the project in Android Studio.
3.  Allow Android Studio to sync the Gradle dependencies.
4.  Build and run the `app` module on an emulator or a physical device.
