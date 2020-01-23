# Resource Server

This project is an Oauth 2.0 resource server implemented using Srping security and spring security oauth.

## Building the project
You can build the project using maven. Run the following command in command line.

```
$ mvn clean install
```
This should create the required executables files.

## Prerequisites

The resource server will require an authorization server running that will provide access token to access protected resources exposed on resource server.

## Starting Resource Server

```
$ java -jar ./target/resource-server-<SERVICE_VERSION>.jar
```

OR

You can start the service using Spring Boot maven plugin by running below command.

```
mvn spring-boot:run
```

Once it is up, you can access protected resource as follows.

```
GET
http://<hostname>:<port>/me
Headers: 
Authorization: BEARER <ACCESS_TOKEN>

Response:
{
  "authorities": [
    {
      "authority": "ROLE_USER"
    }
  ],
  "details": {
    "remoteAddress": "192.168.99.1",
    "sessionId": null,
    "tokenValue": "<ACCESS_TOKEN>",
    "tokenType": "Bearer",
    "decodedDetails": null
  },
  "authenticated": true,
  "userAuthentication": {
    "authorities": [
      {
        "authority": "ROLE_USER"
      }
    ],
    "details": null,
    "authenticated": true,
    "principal": <USERNAME>,
    "credentials": "N/A",
    "name": <USERNAME>
  },
  "principal": <USERNAME>,
  "credentials": "",
  "oauth2Request": {
    "clientId": "clientId",
    "scope": [
      "read",
      "write"
    ],
    "requestParameters": {
      "client_id": "clientId"
    },
    "resourceIds": [],
    "authorities": [],
    "approved": true,
    "refresh": false,
    "redirectUri": null,
    "responseTypes": [],
    "extensions": {},
    "refreshTokenRequest": null,
    "grantType": null
  },
  "clientOnly": false,
  "name": "jaikishan@example.com"<USERNAME>
}
```

## Acknowledgments






 


 
