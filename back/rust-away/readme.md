# RUST-AWAY

RustAway it's the backend application of the homonymous project. This part has the duty to serve and manage data to/from the frontend application and to store it in a MySQL database.

---
To install locally the Database engine you have the following options:

Visiting the officil page from Docker Hub:
https://hub.docker.com/_/mysql

Or pulling the lastest version from command line:
> docker pull mysql

Once you downloaded the image, you have to set the correct values for the project to start:
* user: MYSQL_USER
* password: MYSQL_PASSWORD
* schema: MYSQL_DATABASE

You can set this parameters by just one command like the following: (Note: We also add MYSQL_ROOT_PASSWORD just in case we need to do some maintenance tasks)
> docker run --name rustaway-mysql -e MYSQL_DATABASE=rustaway MYSQL_USER=sa MYSQL_PASSWORD=password MYSQL_ROOT_PASSWORD=password -d mysql:tag

Once the application it's up and running, you can check the documentation in the following url:
>http://localhost:8080/swagger-ui/index.html#/


---

