# AI Architecture

This project keeps AI optional and isolated behind clean interfaces so the original gallery experience keeps working even when AI features are unavailable.

## Architecture

Presentation -> Domain -> Data -> Unsplash / Local AI Capabilities

The AI layer sits underneath the domain as an infrastructure capability. ViewModels do not call Gemini, ML Kit, or database code directly.

## Gemini Nano / AICore

The app is structured to support on-device Gemini Nano / Android AICore when the device and API level support it. The implementation is guarded by availability checks and graceful fallback behavior.

## ML Kit GenAI

On-device ML Kit GenAI paths are considered optional and should be activated only when the selected API can run on the target device and SDK combination.

## Prompt Engineering

Prompts are centralized in `ArtworkPromptBuilder` to avoid scattering instruction strings across ViewModels or fragments.

## Embeddings

Artwork metadata is converted into a searchable embedding representation and then matched with a local cosine-similarity-based vector search engine.

## RAG

The `OnDeviceRagEngine` performs the following flow:

1. User question
2. Query embedding
3. Local vector search
4. Relevant artwork context
5. RAG prompt
6. Local text generation
7. Answer + source artwork IDs

## AppFunctions / MCP

Experimental Android AppFunctions / MCP-style tools are isolated in a separate integration layer and are not required to run the main gallery app.

## A2A

This repo demonstrates application-level agent orchestration with `GallerySearchAgent` and `RecommendationAgent`. This is not advertised as full A2A protocol compliance.

## Security

Artwork metadata is treated as untrusted input. Prompts are structured as system instructions + trusted context + untrusted data + user query to prevent prompt injection from overriding application rules.

## Testing

AI logic is covered through targeted unit tests for prompt generation, local vector math, semantic search, and assistant flows without requiring Gemini Nano on the test device.

## Offline behavior

The app intentionally fails gracefully when the device is unsupported, the model is unavailable, the embeddings fail, or the network is down.

## Device requirements

- Android API 24+
- Kotlin 1.9+
- Hilt-driven dependency injection
- Optional on-device AI support based on device capability and SDK compatibility
