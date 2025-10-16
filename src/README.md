Library Management API Documentation:

Base URL : http://localhost:8081

1. Book APIs (/books)

GET /api/books

Description: Fetch all books in the library.

Request: GET http://localhost:8081/api/books

Headers:

Accept: application/json

Response (200 OK):

[
  {
    "bookId": 1,
    "isbnNo": "12345",
    "title": "Davinci Code",
    "author": "Davinci",
    "available": true,
    "borrowedBy": null
  }
]

GET /api/books/{id}

Description: Fetch a single book by ID.

Request: GET http://localhost:8081/api/books/1

Response (200 OK):

{
  "bookId": 1,
  "isbnNo": "12345",
  "title": "Davinci Code",
  "author": "Davinci",
  "available": true,
  "borrowedBy": null
}

POST /api/books

Description: Create a new book entry.

Request Body (JSON):

{
  "isbnNo": "987654321",
  "title": "Java Fundamentals",
  "author": "James"
}


Response (200 OK):

{
  "bookId": 2,
  "isbnNo": "987654321",
  "title": "Java Fundamental",
  "author": "James",
  "available": true,
  "borrowedBy": null
}


2. Borrower APIs (/borrower)
POST /api/borrower/create

Description: Create a new borrower.

Request Body:

{
  "name": "Rocky",
  "email": "rocky@test.com"
}

Response (200 OK):

{
  "id": 1,
  "name": "Rocky",
  "email": "rocky@test.com"
}

GET /api/borrower/{id}

Description: Fetch borrower details by ID.

Request:

GET http://localhost:8081/api/borrower/1


Response (200 OK):

{
  "id": 1,
  "name": "Rocky",
  "email": "rocky@test.com"
}

Borrow APIs (/borrow)
POST /api/borrow/getBook

Description: Borrow a book by providing the borrower and book IDs.

Request Body:

{
  "borrowerId": 1,
  "bookId": 2
}


Response (200 OK):

{
  "bookId": 2,
  "isbnNo": "987654321",
  "title": "Java Fundamental",
  "author": "James",
  "available": false,
  "borrowedBy": {
    "id": 1,
    "name": "Rocky"
  }
}


Error (400 / 409):

{
  "error": "Book is already borrowed."
}

POST /api/borrow/returnBook

Description: Return a borrowed book to the library.

Request Body:

{
  "borrowerId": 1,
  "bookId": 2
}


Response (200 OK):

{
  "bookId": 2,
  "isbnNo": "987654321",
  "title": "Java Fundamental",
  "author": "James",
  "available": true,
  "borrowedBy": null
}


Error (400 / 409):

{
  "error": "The book is not borrowed by this user!"
}


--------------------------------------------------------------------------------------

---------------------------------------------------------------------------------------------

Technical Notes:

Feature         Description

Framework       Spring Boot 3.4.10

Database        H2 (in-memory) for testing in local, For real-time deployment we should use MySQL or PostgreSQL for reliability and scalability.

Validation      jakarta.validation annotations

JSON Handling   Jackson (auto-configured by Spring Boot)

Port            8081 (configured in application.properties)

Build           mvn clean install;

Run             mvn spring-boot:run