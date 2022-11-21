# Simple TCP/IP Server

I am working on a more convenient way to build and run this project, but for the moment you need to follow the steps mentioned below.

## Build

1. Install shared library jar into your local maven repository. <br> Run the following command in the server or client directory. 

<code>mvn initialize</code>

2. Package server project into a jar.
   
<code>mvn package -DskipTests</code> 

3. Package client project into a jar.

<code>mvn package -DskipTests</code>

## Run

1. Start docker containers with PostgreSQL and pgAdmin. Run this command in root directory of the project.

<code>docker-compose up</code> 

2. Start the server by running the following command in the server directory:

<code>java -cp target/server-1.0-SNAPSHOT-jar-with-dependencies.jar com.jgrajber.server.Server
</code>

3. Start the client app by running the following command in the client directory:

<code>java -cp target/client-1.0-SNAPSHOT-jar-with-dependencies.jar com.jgrajber.client.Client
</code>