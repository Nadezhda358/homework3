# Hotel

This is a RESTful API for managing hotels. 
The API provides endpoints for creating, reading, 
updating, and deleting hotel information.

## Usage
The API endpoints are accessible at http://localhost:8082/api/v1/hotels.

Here are the available endpoints:

* GET /: returns a list of all hotels.
* GET /{id}: returns a single hotel with the given ID.
* POST /: creates a new hotel.
* PUT /{id}: updates an existing hotel with the given ID.
* DELETE /{id}: deletes a hotel with the given ID.

For example, to get a list of all hotels, you can send a GET request to http://localhost:8082/api/v1/hotels.
To create a new hotel, you can send a POST request with a JSON payload that contains the hotel information.