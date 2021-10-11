# file data validation
This has a simple project for RSSB file validation

This application has a REST API-based backend system that does this:

* Has endpoint to receive a file of 50,000 users. (Excel file generated on your
end)

* Each record of the file has dummy Names, NID, phone number, gender,
email

* It validates each record, (as sometimes NID is not valid, phone number is
incorrect, etc,) and be keep validation failure.

* Add a new endpoint to display the uploaded list with its validation failure
for each record. (This endpoint will be used by the front-end to display
paginated results)

* Add endpoint to be called in other to commit the list uploaded in a SQL
database

* All these endpoints are accessible only via a JWT Token-based authentication.

## Requirements

This project requires that PostgreSQL 10-12 be installed.


## Installation

After cloning the project, you'll need to have the maven installed to build the project. Run the following:

```
mvn install
```

Build using the following command if you want to skips tests while building

```
mvn install -DskipTests
```

Once that is done, you'll need to configure the project to connect to your Postgres database:

```
db = postgres
username = postgres
passowrd = root
```

## Running

To start the server, you will need to run this command `mvn spring-boot:run` via the commandline

## HTTP Endpoints

The service provides three HTTP endpoints for answering the questions asked in the "in the exercise" document.

#### authenticate to get the jwt Bearer token

	Method: POST
    URL: localhost:8080/authenticate
    REQUEST BODY:
        username:rssb
        password:Test#2021

#### Get a list of all valid customers

	Method: GET
    URL: localhost:8080/request/data/list
    Headers:
        Authertion: Bearer ...

#### Get a list of all customers whose records failed to be validated

	Method: GET
    URL: localhost:8080/request/data/list/failed-validations
    Headers:
        Authertion: Bearer ...

#### Commit validated customers to the DB

	Method: POST
    URL: localhost:8080/request/data/save
    Headers:
        Authertion: Bearer ...

#### Upload the excel sheet with customers details

	Method: POST
    URL: localhost:8080/request/data/upload
    Headers:
        Authertion: Bearer ...

[NB]: There is an excel sheet in the source folder, which has the format accepted by the system
