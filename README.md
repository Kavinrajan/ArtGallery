# 🎨 ArtGallery — AI-Integrated Android Gallery

> **Modern Android application demonstrating AI/LLM integration with Kotlin, Jetpack Compose, Clean Architecture, MVVM, RAG, embeddings, MCP/tool calling and scalable Android engineering practices.**

ArtGallery is a modern Android application originally built around image discovery using the Unsplash API and evolved into an **AI integration showcase for Android**.

The project demonstrates how AI capabilities can be integrated into a maintainable Android architecture while keeping UI, business logic, networking, data access and AI orchestration separated.

The repository combines **modern Android engineering practices with Generative AI concepts** including LLM integration, prompt engineering, Retrieval-Augmented Generation (RAG), embeddings, tool calling and Model Context Protocol (MCP) concepts.

---

## 🚀 Project Highlights

### Android Engineering

* Kotlin
* Android SDK
* Jetpack Compose
* MVVM
* Clean Architecture
* Repository Pattern
* Kotlin Coroutines
* Kotlin Flow
* StateFlow
* Dependency Injection with Hilt
* Retrofit
* OkHttp
* REST API integration
* Unit Testing / TDD
* Gradle

### AI / Generative AI

* LLM Integration
* Gemini / Generative AI APIs
* Prompt Engineering
* AI-safe DTO design
* Structured LLM responses
* Context-aware AI interactions
* Retrieval-Augmented Generation (RAG)
* Embeddings
* Semantic retrieval
* Context injection
* Tool Calling
* Model Context Protocol (MCP)
* MCP-style tool server integration
* AI Agent concepts
* Agent orchestration
* AI response validation
* Error handling and fallback strategies

---

# 🤖 AI Integration Architecture

The AI layer is designed as an independent part of the Android application rather than coupling AI logic directly to Compose screens or ViewModels.

```text
                    ┌──────────────────────┐
                    │     Jetpack Compose  │
                    │          UI          │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      ViewModel       │
                    │   UI State / Events  │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │       Use Case       │
                    │   Business Logic     │
                    └──────────┬───────────┘
                               │
                ┌──────────────┴──────────────┐
                ▼                             ▼
      ┌──────────────────┐          ┌──────────────────┐
      │   AI Repository  │          │ Gallery Repository│
      └────────┬─────────┘          └────────┬─────────┘
               │                             │
               ▼                             ▼
      ┌──────────────────┐          ┌──────────────────┐
      │ AI Orchestrator  │          │  Unsplash API    │
      └────────┬─────────┘          └──────────────────┘
               │
        ┌──────┼───────────┐
        ▼      ▼           ▼
     LLM     RAG        MCP Tools
      │       │             │
      │       ▼             ▼
      │   Embeddings    Tool Calling
      │       │
      └───────┴──────────────┐
                             ▼
                    ┌──────────────────┐
                    │ AI Response      │
                    │ Validation       │
                    └──────────────────┘
```

---

# 🧠 AI Capabilities

## 1. LLM Integration

The application demonstrates how an Android application can communicate with a Large Language Model through an API abstraction.

```text
Android
   │
   ▼
ViewModel
   │
   ▼
Use Case
   │
   ▼
AI Repository
   │
   ▼
LLM Client
   │
   ▼
Gemini / LLM API
   │
   ▼
Structured Response
   │
   ▼
Domain Model
   │
   ▼
Compose UI
```

The LLM layer is isolated behind interfaces so that the underlying provider can be replaced without changing the presentation layer.

---

# ✍️ Prompt Engineering

The project demonstrates structured prompting rather than sending uncontrolled user input directly to an LLM.

Example prompt flow:

```text
User Query
    │
    ▼
Input Validation
    │
    ▼
System Instructions
    │
    ▼
Context
    │
    ▼
User Request
    │
    ▼
LLM
    │
    ▼
Structured Response
```

Prompt design considerations include:

* Clear system instructions
* Context separation
* Task-specific instructions
* Output constraints
* Grounding with retrieved information
* Response validation
* Handling malformed responses
* Preventing unnecessary sensitive data from being sent to the model

---

# 🔎 Retrieval-Augmented Generation (RAG)

RAG allows the application to retrieve relevant information before sending context to the LLM.

```text
User Query
     │
     ▼
Query Processing
     │
     ▼
Embedding Generation
     │
     ▼
Similarity Search
     │
     ▼
Relevant Context
     │
     ▼
Prompt Construction
     │
     ▼
LLM
     │
     ▼
Grounded Response
```

Instead of relying only on the model's general knowledge, relevant application data can be retrieved and supplied as context.

### Example

```text
Query:

"Show me information about modern abstract artwork."
```

The system can:

1. Convert the query into an embedding.
2. Search available artwork metadata.
3. Retrieve relevant records.
4. Build a grounded prompt.
5. Send the context to the LLM.
6. Return a contextual response.

---

# 🧮 Embeddings

Embeddings represent text or other supported content as numerical vectors.

```text
Artwork Description
        │
        ▼
Embedding Model
        │
        ▼
Vector Representation
        │
        ▼
Similarity Search
        │
        ▼
Relevant Artwork
```

Embeddings can support:

* Semantic search
* Similar-content retrieval
* RAG
* Context discovery
* Recommendation scenarios

---

# 🔧 MCP / Tool Calling

The AI integration also demonstrates the concepts behind **Model Context Protocol (MCP)** and tool-enabled AI workflows.

Instead of allowing the LLM to directly access application internals, tools provide controlled operations.

```text
                 LLM
                  │
                  ▼
            Tool Selection
                  │
        ┌─────────┼─────────┐
        ▼         ▼         ▼
   Search Art   Get Image  Get Details
        │         │         │
        └─────────┼─────────┘
                  ▼
            Tool Response
                  │
                  ▼
                 LLM
                  │
                  ▼
             Final Answer
```

Example conceptual tools:

```text
searchArtwork(query)

getArtworkDetails(id)

searchGallery(category)

getRelatedArtwork(id)
```

The important architectural principle is that **tool access is explicitly defined and controlled**.

---

# 🧩 AI-safe DTO

AI-facing data is separated from internal application/domain models.

```text
Domain Model
     │
     ▼
AI Mapper
     │
     ▼
AI-safe DTO
     │
     ▼
LLM / AI Service
```

This helps prevent unnecessary application or sensitive fields from being included in an AI request.

Example:

```kotlin
data class ArtworkAiContext(
    val title: String,
    val artist: String?,
    val category: String?,
    val description: String?
)
```

The AI layer should receive only the information required for the requested operation.

---

# 🏦 Banking-Oriented AI Design Concepts

Although ArtGallery is an independent portfolio application, the AI architecture demonstrates patterns that can be applied to banking and financial applications.

Examples include:

### Customer Support

```text
Customer Question
       │
       ▼
RAG Retrieval
       │
       ▼
Banking FAQ / Product Information
       │
       ▼
LLM
       │
       ▼
Contextual Response
```

### Transaction Assistance

```text
User Request
     │
     ▼
Intent Detection
     │
     ▼
Controlled Tool
     │
     ▼
Transaction Information
     │
     ▼
LLM
     │
     ▼
User Response
```

### Important Banking Considerations

* Never expose credentials or secrets to an LLM.
* Minimize sensitive data sent to AI services.
* Validate tool arguments.
* Apply authorization before executing tools.
* Validate AI responses.
* Use deterministic APIs for financial operations.
* Treat LLM output as untrusted input.
* Provide fallback behaviour when AI services are unavailable.
* Keep auditability and security separate from conversational AI.

---

# 🏗️ Android Architecture

The application follows **Clean Architecture + MVVM**.

```text
┌─────────────────────────────┐
│        Presentation         │
│                             │
│   Compose UI                │
│   ViewModel                 │
│   UI State                  │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│           Domain            │
│                             │
│   Use Cases                 │
│   Domain Models             │
│   Repository Interfaces     │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│            Data             │
│                             │
│   Repository Implementations│
│   API Services              │
│   DTOs                      │
│   Mappers                   │
└──────────────┬──────────────┘
               │
        ┌──────┴───────┐
        ▼              ▼
   Unsplash API     AI Services
```

---

# 🛠️ Technology Stack

| Category        | Technologies                     |
| --------------- | -------------------------------- |
| Language        | Kotlin                           |
| Platform        | Android                          |
| UI              | Jetpack Compose                  |
| Architecture    | Clean Architecture, MVVM         |
| State           | StateFlow, Kotlin Flow           |
| Async           | Kotlin Coroutines                |
| DI              | Dagger Hilt                      |
| Networking      | Retrofit, OkHttp                 |
| API             | REST, Unsplash API               |
| AI              | LLM APIs, Gemini / Generative AI |
| AI Architecture | RAG, Embeddings, Tool Calling    |
| AI Integration  | MCP concepts, AI Agents          |
| Prompting       | Prompt Engineering               |
| Persistence     | Room where applicable            |
| Testing         | Unit Testing, TDD                |
| Build           | Gradle                           |
| Version Control | Git / GitHub                     |
| CI/CD           | GitHub Actions                   |

---

# 📂 Project Structure

```text
ArtGallery/
│
├── app/
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── ...
│       │   │
│       │   └── res/
│       │
│       └── test/
│
├── .github/
│   └── workflows/
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

# 🔄 Application Flow

```text
User
 │
 ▼
Jetpack Compose
 │
 ▼
ViewModel
 │
 ▼
Use Case
 │
 ├───────────────┐
 ▼               ▼
Gallery       AI Feature
 │               │
 ▼               ▼
Repository    AI Repository
 │               │
 ▼               ├── LLM
Unsplash API     ├── RAG
                 ├── Embeddings
                 └── Tools / MCP
                         │
                         ▼
                  AI Response
                         │
                         ▼
                    ViewModel
                         │
                         ▼
                        UI
```

---

# 🧪 Testing Strategy

The project follows a testable architecture with separation between UI, domain and data layers.

Testing areas include:

* ViewModel tests
* Use Case tests
* Repository tests
* API response mapping
* AI request/response mapping
* Error handling
* Input validation
* Prompt construction
* AI response parsing
* Mocked AI service testing

---

# 🔐 AI Security Principles

AI integration should be treated as an external/untrusted dependency.

The project follows these design principles:

* No API keys hardcoded in source code
* Avoid sending unnecessary sensitive data
* Validate user input
* Validate AI output
* Validate tool arguments
* Apply authorization before sensitive operations
* Use timeouts
* Handle API failures
* Implement graceful fallback behaviour
* Keep business-critical decisions outside the LLM
* Separate domain models from AI DTOs

---

# ⚡ Error Handling

AI and network operations can fail for several reasons:

```text
User Request
     │
     ▼
Validation
     │
     ▼
AI Request
     │
     ├── Success ──────► Parse Response
     │
     ├── Timeout ───────► Retry / Fallback
     │
     ├── Network Error ─► Offline/Error State
     │
     ├── API Error ─────► Error Mapping
     │
     └── Invalid Output ► Validation Failure
```

The UI should never depend on an AI response being successful.

---

# 📡 API Integration

The original ArtGallery functionality uses the Unsplash REST API.

```text
Compose UI
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
Retrofit
    │
    ▼
OkHttp
    │
    ▼
Unsplash API
```

The same repository-based approach is extended for AI services so networking concerns remain isolated from the UI layer.

---

# 🚀 Getting Started

## Prerequisites

* Android Studio
* JDK
* Android SDK
* Git
* Unsplash API credentials
* AI provider credentials where required

## Clone

```bash
git clone https://github.com/Kavinrajan/ArtGallery.git

cd ArtGallery
```

Open the project in Android Studio and allow Gradle synchronization to complete.

## Build

### macOS / Linux

```bash
./gradlew build
```

### Windows

```bash
gradlew.bat build
```

> Never commit API keys, tokens or other secrets to the repository.

---

# 🎯 Skills Demonstrated

### Android

* Kotlin
* Android SDK
* Jetpack Compose
* MVVM
* Clean Architecture
* Repository Pattern
* Hilt
* Coroutines
* Flow / StateFlow

### Networking

* REST APIs
* Retrofit
* OkHttp
* JSON serialization
* Error handling

### Generative AI

* LLM integration
* Gemini / Generative AI
* Prompt Engineering
* RAG
* Embeddings
* Semantic retrieval
* Tool Calling
* MCP
* AI Agent concepts
* Structured AI responses
* AI-safe DTOs

### Software Engineering

* SOLID principles
* Separation of concerns
* Dependency inversion
* Testability
* TDD
* Error handling
* Secure API integration
* Scalable architecture

---

# 💼 Portfolio / Interview Relevance

ArtGallery demonstrates how a senior Android engineer can extend an existing mobile architecture with AI capabilities without coupling AI functionality directly to the UI.

The architecture provides a foundation for integrating AI into domains such as:

* Banking
* Financial Services
* Healthcare
* E-commerce
* Customer Support
* Enterprise applications

The same patterns can be adapted for:

```text
LLM APIs
   +
RAG
   +
Embeddings
   +
Tool Calling
   +
MCP
   +
Android Clean Architecture
   =
AI-enabled Mobile Applications
```

---

# 👨‍💻 Author

**Kavinrajan S M**

Senior Android Developer

Areas of focus:

* Native Android Development
* Kotlin
* Jetpack Compose
* Clean Architecture
* AI/ML Integration
* LLM Integration
* Generative AI
* Backend Development
* DevOps / CI/CD

---

# ⭐ Project Purpose

This repository is maintained as a **technical learning, experimentation and portfolio project** demonstrating modern Android engineering and AI integration patterns.

It is intended to showcase architectural understanding and implementation patterns rather than represent a production banking system.

---

## 📄 License

This project is intended for learning, experimentation and portfolio demonstration.
