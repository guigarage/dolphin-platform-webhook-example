# Dolphin Platform WebHook Sample

![Dolphin Platform Logo](http://www.guigarage.com/wordpress/wp-content/uploads/2015/10/logo.png)

This project shows how several clients can automatically be updated by using webhooks.
The Maven projects contains 3 modules:

* The __common__ module contains the model definition and constants that are shared between client and server
* The __server__ module contains the Spring Boot server Application 
* The __client__ module contains the JavaFX client

## Description
The project shows how a [webhook](https://en.wikipedia.org/wiki/Webhook) on the server side can be used to update several clients. On the server a REST endpoint is defined (see class WebhookController). This REST controller defines a POST endpoint under http://localhost:8080/todo that can be used to add a todo item to the application. The name of the todo is defined as a request param. If you want to call the endpoint manually you can simply call the POST endpoint http://localhost:8080/todo?name=AnyTodoName by using a tool like [Paw](https://luckymarmot.com/paw) or [Advanced REST client](https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo).

As you can see in the WebhookController class the incoming todo will be published to the Dolphin Platform event bus. By doing so any Dolphin Platform controller that is registered to the given topic will receive the todo item.

![WebHook Workflow](http://www.guigarage.com/wordpress/wp-content/uploads/2016/01/webhook.png)

The controllers will add the received todo item to the model that is synchronized with the client [(more information about the Dolphin Platform features)](https://github.com/canoo/dolphin-platform). By doing so new todo items that are posted by using the webhook will automatically be shown in the client.

If you deploy this sample in the cloud (by for example using [Heroku](https://www.heroku.com)) you can use a service like [Zapier](https://zapier.com) to call the webhook on specific events like a new Twitter message. 

## How to use it
The project is created as a Maven project. You can directly import it in any IDE that supports Maven (like Eclipse, Netbeans or IntelliJ).
If you want to start the application you need to start the ServerApplication class in the server module and than the ClientApplication class in the client module. The client can be started several times.

If you want to start the application from commandline without IDE support you need to call a Maven install first in the main project folder:

```
mvn install
```

Once this is done you can simply install the server from the server folder
```
cd server
mvn spring-boot:run
```

A client instance can be started by Maven, too:
```
cd client
mvn exec:java
```