# 🎨 ArtGallery

### Modern Android Gallery App built with Kotlin, MVVM, Clean Architecture & TDD

ArtGallery is a modern **Android image gallery application** that consumes the **Unsplash API** to discover and display high-quality images.

The project demonstrates production-oriented Android development practices including **MVVM, Clean Architecture, Kotlin Coroutines, Retrofit, OkHttp, Dependency Injection with Hilt, and Test-Driven Development (TDD)**.

---

## 📱 Overview

ArtGallery was created to demonstrate how to build a maintainable Android application using a clean separation of responsibilities and modern Android development patterns.

The application communicates with the **Unsplash API**, retrieves image data, and presents the results through a clean and responsive Android UI.

### ✨ Highlights

* 🖼️ Browse images from Unsplash
* 🌐 REST API integration
* 🏗️ Clean Architecture
* 🔄 MVVM architecture
* ⚡ Kotlin Coroutines
* 💉 Dependency Injection with Dagger Hilt
* 🌍 Retrofit + OkHttp networking
* 🧪 Test-Driven Development
* 📦 Separation of presentation, domain, and data responsibilities
* ♻️ Maintainable and testable codebase

---

## 🏗️ Architecture

The application follows **Clean Architecture + MVVM**, separating the application into logical layers.

```text
┌─────────────────────────────────────────────┐
│                 Presentation                │
│                                             │
│             UI / ViewModel                  │
└──────────────────────┬──────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────┐
│                   Domain                    │
│                                             │
│          Use Cases / Business Logic         │
└──────────────────────┬──────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────┐
│                    Data                     │
│                                             │
│     Repository / API / Network Models       │
└──────────────────────┬──────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────┐
│                Unsplash API                 │
└─────────────────────────────────────────────┘
```

### Architecture Benefits

* Clear separation of concerns
* Easier unit testing
* Reduced coupling between layers
* Better maintainability
* Easier feature expansion
* Reusable business logic

---

## 🛠️ Tech Stack

| Category                 | Technology             |
| ------------------------ | ---------------------- |
| Language                 | **Kotlin**             |
| Platform                 | **Android**            |
| Architecture             | **MVVM**               |
| Architecture Pattern     | **Clean Architecture** |
| Networking               | **Retrofit**           |
| HTTP Client              | **OkHttp**             |
| Asynchronous Programming | **Kotlin Coroutines**  |
| Dependency Injection     | **Dagger Hilt**        |
| API                      | **Unsplash API**       |
| Testing                  | **TDD / Unit Testing** |
| Build System             | **Gradle**             |

---

## 🔌 API Integration

ArtGallery uses the **Unsplash API** to retrieve image and gallery data.

```text
Android Application
        │
        ▼
   Repository
        │
        ▼
    Retrofit
        │
        ▼
     OkHttp
        │
        ▼
  Unsplash REST API
        │
        ▼
   JSON Response
        │
        ▼
     Domain
        │
        ▼
    ViewModel
        │
        ▼
        UI
```

This separation keeps networking concerns isolated from the presentation layer.

---

## 🔄 Application Flow

```text
User
 │
 ▼
Android UI
 │
 ▼
ViewModel
 │
 ▼
Use Case
 │
 ▼
Repository
 │
 ▼
Retrofit / OkHttp
 │
 ▼
Unsplash API
 │
 ▼
Response Mapping
 │
 ▼
Domain Model
 │
 ▼
ViewModel State
 │
 ▼
UI Update
```

---

## 🧪 Test-Driven Development

Testing is an important part of the project.

The application demonstrates a **TDD-oriented development approach**, helping ensure that business logic and application behavior remain reliable as the codebase evolves.

### Testing Goals

* Validate business logic
* Verify ViewModel behavior
* Test repository operations
* Reduce regression issues
* Improve code maintainability
* Encourage loosely coupled components

---

## 💉 Dependency Injection

The project uses **Dagger Hilt** for dependency injection.

Hilt helps manage dependencies such as:

```text
ViewModel
   │
   ├── Use Case
   │
   └── Repository
          │
          └── API Service
                 │
                 └── Retrofit / OkHttp
```

Benefits include:

* Better testability
* Reduced boilerplate
* Centralized dependency management
* Improved separation of concerns
* Easier component replacement during testing

---

## ⚡ Kotlin Coroutines

Kotlin Coroutines are used for asynchronous operations, particularly network-related work.

```text
UI
 │
 ▼
ViewModel
 │
 ▼
Coroutine
 │
 ▼
Repository
 │
 ▼
Network Request
```

This allows network operations to execute asynchronously without blocking the main UI thread.

---

## 📂 Project Structure

The project follows a layered Android architecture.

```text
ArtGallery/
│
├── app/
│   │
│   ├── src/
│   │   ├── main/
│   │   │
│   │   └── test/
│   │
│   └── ...
│
├── gradle/
│   └── wrapper/
│
├── build.gradle
├── settings.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have:

* Android Studio
* JDK
* Android SDK
* Git
* A valid Unsplash API access key

---

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Kavinrajan/ArtGallery.git
```

```bash
cd ArtGallery
```

---

### 2️⃣ Open in Android Studio

Open the project in **Android Studio** and allow Gradle to synchronize the project.

---

### 3️⃣ Configure Unsplash API

Create/configure your Unsplash API credentials according to the project's configuration.

> ⚠️ Never commit private API keys or secrets directly into source control.

---

### 4️⃣ Build the Project

```bash
./gradlew build
```

For Windows:

```bash
gradlew.bat build
```

---

### 5️⃣ Run the Application

Run the application from Android Studio on:

* Android Emulator
* Physical Android Device

---

## 🎯 Engineering Practices Demonstrated

This project demonstrates practical knowledge of:

```text
Kotlin
      ↓
Android Development
      ↓
MVVM
      ↓
Clean Architecture
      ↓
Repository Pattern
      ↓
REST API Integration
      ↓
Retrofit + OkHttp
      ↓
Coroutines
      ↓
Dependency Injection
      ↓
Dagger Hilt
      ↓
Unit Testing / TDD
```

---

## 💡 Why This Project?

The purpose of ArtGallery is to demonstrate how a real-world Android application can be structured for:

**Maintainability → Testability → Scalability → Separation of Concerns**

Rather than putting API calls and business logic directly inside UI components, the application separates responsibilities across architectural layers.

---

## 🔮 Future Improvements

Potential improvements for the project include:

* [ ] Jetpack Compose UI
* [ ] Paging 3 integration
* [ ] Offline caching
* [ ] Room database
* [ ] Image caching
* [ ] Search and filtering
* [ ] Favorites
* [ ] Dark mode
* [ ] UI tests
* [ ] CI/CD with GitHub Actions
* [ ] Modularized architecture
* [ ] Kotlin Flow-based state management

---

## 📊 Skills Demonstrated

### Android

* Android SDK
* Kotlin
* MVVM
* Clean Architecture
* Repository Pattern
* Dependency Injection
* Coroutines

### Networking

* REST APIs
* Retrofit
* OkHttp
* JSON data handling

### Software Engineering

* SOLID principles
* Separation of concerns
* Testability
* TDD
* Maintainable architecture

---

## 👨‍💻 Author

### Kavinrajan S M

**Senior Android Developer**

Focused on:

```text
Android Development
Kotlin
Jetpack Compose
Kotlin Multiplatform
Clean Architecture
AI/ML Integration
Backend Development
DevOps & CI/CD
```

---

## ⭐ Support

If you find this project useful or interesting, consider giving the repository a ⭐ **Star**.

It helps support continued learning and development.

---

## 📄 License

This project is intended for **learning, experimentation, and portfolio demonstration**.
