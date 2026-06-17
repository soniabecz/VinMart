# VinMart – System Rezerwacji Płyt Winylowych

## Opis projektu

VinMart to aplikacja biznesowa oparta na architekturze mikrousług, umożliwiająca zarządzanie katalogiem płyt winylowych, składanie rezerwacji, realizację płatności, obsługę powiadomień oraz uwierzytelnianie użytkowników.

Projekt został wykonany w ramach przedmiotu **Modelowanie i Implementacja Aplikacji Biznesowych**.

---

## Architektura systemu

System składa się z czterech mikrousług:

### catalog-service

Odpowiada za zarządzanie katalogiem płyt winylowych.

- dodawanie winyli,
- edycja winyli,
- usuwanie winyli,
- przeglądanie katalogu.

### reservation-service

Odpowiada za obsługę rezerwacji.

- tworzenie rezerwacji,
- pobieranie listy rezerwacji,
- obsługa płatności,
- automatyczne anulowanie nieopłaconych rezerwacji.

### notification-service

Odpowiada za obsługę powiadomień.

- odbieranie komunikatów z innych mikrousług,
- przetwarzanie powiadomień z wykorzystaniem programowania reaktywnego.

### auth-service

Odpowiada za uwierzytelnianie użytkowników.

- rejestracja użytkowników,
- logowanie użytkowników.

### PostgreSQL

Relacyjna baza danych wykorzystywana przez aplikację.

---

## Wykorzystane technologie

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Spring AOP
- Spring Web
- Spring WebFlux
- PostgreSQL
- Maven
- Docker
- Docker Compose
- Lombok
- OpenAPI / Swagger

---

## Realizacja wymagań projektowych

### 1. Narzędzie linii komend

Do automatyzacji procesu budowania wykorzystano Maven.

Przykładowe polecenia:

```bash
mvn clean package
mvn test
```

### 2. Kontener IoC / Dependency Injection

Wykorzystano mechanizm Spring IoC oraz wstrzykiwanie zależności.

### 3. ORM

Zastosowano Spring Data JPA oraz Hibernate.

### 4. Aspekty (AOP)

Zaimplementowano:

- LoggingAspect – logowanie wywołań metod,
- AuditAspect – audyt operacji biznesowych.

### 5. REST

Wszystkie mikrousługi udostępniają funkcjonalność poprzez REST API.

### 6. Programowanie reaktywne

W notification-service wykorzystano:

- Flux,
- Mono,
- Sinks.Many.

### 7. Docker

Każda mikrousługa posiada własny Dockerfile.

Uruchamianie systemu realizowane jest przez Docker Compose.

### 8. Mikrousługi

System został podzielony na niezależne mikrousługi:

- catalog-service,
- reservation-service,
- notification-service,
- auth-service.

---

## Uruchomienie projektu

### Wymagania

- Docker Desktop
- Java 21
- Maven

### Budowanie aplikacji

Dla każdej mikrousługi:

```bash
mvn clean package
```

### Uruchomienie

Z głównego katalogu projektu:

```bash
docker compose up --build
```

---

## Dostępne porty

| Usługa | Port |
|---------|---------|
| catalog-service | 8080 |
| reservation-service | 8081 |
| notification-service | 8082 |
| auth-service | 8083 |
| PostgreSQL | 5433 |

---

## Dokumentacja API

Swagger/OpenAPI:

- http://localhost:8080/swagger-ui/index.html
- http://localhost:8081/swagger-ui/index.html
- http://localhost:8083/swagger-ui/index.html

---

## Scenariusz demonstracyjny

1. Rejestracja użytkownika.
2. Logowanie użytkownika.
3. Dodanie nowej płyty winylowej.
4. Pobranie katalogu płyt.
5. Utworzenie rezerwacji.
6. Wygenerowanie powiadomienia.
7. Opłacenie rezerwacji.
8. Sprawdzenie wpisów audytowych.
9. Automatyczne anulowanie nieopłaconej rezerwacji.
10. Uruchomienie całego systemu za pomocą Docker Compose.

---

## Struktura projektu

```text
vinmart
├── catalog-service
├── reservation-service
├── notification-service
├── auth-service
├── docker-compose.yml
└── README.md
```

---

## Autor Sonia Becz

Projekt wykonany w ramach przedmiotu:

**Modelowanie i Implementacja Aplikacji Biznesowych**
