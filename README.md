# Generated Spring CQRS +  Project

Ce projet a été généré automatiquement via le **Spring  CQRS Code Generator**. Il repose sur une architecture propre et modulaire en séparant les responsabilités via les patterns **CQRS**, **DDD** et **Event-Driven Architecture**.

## 🔧 Stack Technique

- Spring Boot WebFlux
- Spring Data R2DBC
- PostgreSQL (Reactive)
- Swagger / OpenAPI
- Java 17+

## 🌐 Architecture

```
project-root/
├── domain/
│   └── model, events, valueObjects
├── application/
│   └── usecases, commands, queries, handlers, dto
├── infrastructure/
│   └── repository, entity, config
├── presentation/
│   └── controller (REST, SSE)
```

## 📊 Fonctionnalités

- Génération de CRUD CQRS complets
- API REST pour les commandes
- API REST + SSE pour les queries
- Mapping DTO/Entity automatique

## ⚒️ Utilisation

Accéder à l’API REST via Swagger: `http://localhost:8090/swagger-ui.html`
 

## 📚 Domaines d'application

- Systèmes financiers en temps réel (transactions)
- ERP, CRM, outils de gestion modulaire
- Plateformes e-commerce à événements

## 🌐 SSE (Server-Sent Events)

Les endpoints `/api/v1/queries/entity/stream` permettent aux clients de s'abonner aux événements en temps réel, sans WebSocket.

 
---

Généré par **Spring CQRS Code Generator**.
 