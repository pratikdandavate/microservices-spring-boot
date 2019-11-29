# Authorization Server

This project is an authorization server implemented using OAuth 2.0.

## Building the project
You can build the project using maven. Run the following command in command line.

```
$ mvn clean install
```
This should create the required executables files.

## Starting Authorization Server

```
$ java -jar ./target/authorization-server-<SERVICE_VERSION>.jar
```

OR

You can start the service using Spring Boot maven plugin by running below command.

```
mvn spring-boot:run
```

Once it is up, you can register new user as follows:

```
POST
http://<hostname>:<port>/signup
Request:
{
	"firstName":"Bob",
	"lastName":"Dylan",
	"email": "bob.dylan@example.com",
	"password": "password"
}

Response:
{
    "success": true,
    "message": "User registered successfully"
}
```

You can request access token for this registered user using following curl command.

```bash
curl clientId:secret@localhost:8081/oauth/token -d grant_type=password -d username=bob.dylan@example.com -d password=password
```

This will respond with something like:

```json
{
	"access_token":"ab23f7df-6e28-4a2d-8ea8-ce9ef847edc2",
	"token_type":"bearer",
	"refresh_token":"de416434-3b30-485c-8a61-cc7729d7f19b",
	"expires_in":299,
	"scope":"read write"
}
```

## Acknowledgments

* https://github.com/spring-projects/spring-security/tree/master/samples/boot/oauth2authorizationserver
* https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/html5/
* https://blog.marcosbarbero.com/oauth2-centralized-authorization-opaque-jdbc-spring-boot2/




 


 
