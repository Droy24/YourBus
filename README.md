# YourBus

YourBus contains spring-boot apis targeted for online bus booking system.
The project was run on Eclipse and deployed on Localhost . 
The api are hit using Postman. 



First the new user needs to be created :
For users :
Post Request for creating new user: 
localhost/8080/users
Get Request for getting your details :
localhost/8080/users/{id}
Delete Request for deleting the user
localhost/8080/users
Post Request for logout user:
localhost/8080/logout


To use other apis the user needs to login :
Post Request for login existing user:
localhost/8080/login

The postrequest must have username and password in it's body.

The browser will then do authentication on authentication server by generating oauth token:
localhost/8080/oauth-token
A new access-token is generated which needs to be sent as bearer-token in every api request they are sending request to.

Create stations .
Create various routes using each station.
Create new bus give it a route set the number of seats(n) in the bus.
n number of seats for the given bus will be generated.

Booking and cancellation of ticket for a given seat in a bus.
To book a seat between various station of a bus you will need to specify the source station and destination station you want to book the seat for .If the seat is booked in between any of those station. error message will be shown .If the stations are invalid an error message will be given. 
The fare will be calculated and the booking details will be returned in the responsebody
On successful booking a mail will be sent on the user's email id .

Cancellation of ticket is based on the seats you select to be cancelled and whether the booking is valid and based on the cancellation date .
If cancellation date is more than 1 day before then 80% fare will be returned.
else 50% of total fare will be returned.
