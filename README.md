rooms-availability
==================

Small service to display the availability (free/busy) of a person (or a room) from Exchange Web Services.

Building the service
--------------------

At the root of the repository:

    mvn clean package

Running the service
-------------------

At the root of the repository, and assuming that the JAR has been built correctly:

    java -jar target/rooms-availability-1.0-SNAPSHOT.jar server <path of the yaml file>

Resources available
-------------------

**/availability**

Query parameters:

* email: (mandatory) email address to be checked
* date: (optional, defaults to "today") date to be checked, in the format dd/MM/yyyy

Example:

    http://127.0.0.1:8080/availability?email=my@email.com&date=21/10/2013

Configuration example
---------------------

Example of configuration file (YAML)

    exchange:
      username: <username>
      password: <password>
      domain: <domain>
      url: https://<domain>/EWS/exchange.asmx

