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
