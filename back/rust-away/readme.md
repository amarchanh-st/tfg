

To install locally the Database engine you have the following options:

Visiting the officil page from Docker Hub:
`https://hub.docker.com/_/mysql`

Or pulling the lastest version from command line:
`docker pull mysql`

Once you downloaded the image, you have to set the correct values for the project to start:
* user: 
* password: 
* schema: 

You can set this parameters by just one command like the following:
`docker run --name rustaway-mysql -e MYSQL_DATABASE=rustaway MYSQL_USER=sa MYSQL_PASSWORD=password MYSQL_ROOT_PASSWORD=password -d mysql:tag`