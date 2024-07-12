# InventoryItems

## Description

InventoryItems is a simple Java EE application that allows users to manage warehouse items. It provides RESTful endpoints to create, read, update, and delete items from the inventory using a PostgresSQL database.

## Table of Contents

- [Description](#description)
- [Running the app](#running-the-app)
- [API Endpoints](#api-endpoints)
  

## Running the app
Firstly, to build the project, go to its directory and then you should run the following command in the terminal:
```sh
mvn clean install
```

Secondly, if you want to test the endpoints, after building the project, you should write in the terminal:
```sh
mvn jetty:run
```

### Testing the API Endpoints

You can use Postman or any other API client to test the endpoints.

## API Endpoints

- **POST http://localhost:8080/api/items/save**: Save a new item.
  - Request Body (JSON):
    ```json
    {
        "price": 300,
        "name": "item name"
    }
    ```

- **GET http://localhost:8080/api/items/getAll**: Retrieve all items.

- **GET http://localhost:8080/api/items/get/{uuid}**: Retrieve a single item by UUID.
  - Just write the following URL: 
http://localhost:8080/api/items/get/your_uuid
  -uui should be written without any quotes and parentheses. As usually choose the GET endpoint and send it without json.

- **PUT http://localhost:8080/api/items/update**: Update an existing item.
  - Request Body (JSON):
    ```json
    {
        "uuid": "item-uuid",
        "price": 500,
        "name": "updated item name"
    }
    ```

- **DELETE /api/items/delete/{uuid}**: Delete an item by UUID.
  - Just follow the steps as in the GET endpoint, but change the endpoint to the DELETE.

