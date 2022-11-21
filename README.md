# Simple TCP/IP Server

## Build

1. Install shared library jar into your local maven repository. <br> Run the following command in the server or client directory:


<code>mvn initialize</code>

1. Package server project into a jar. In server directory: 
   
<code>mvn package -DskipTests</code> 

2. Package client project into a jar. In client directory:

<code>mvn package</code>

## Run

1. This command will build, create and start containers including PostgreSQL database and tcp server:

<code>docker-compose up</code> 

2. Now you can start multiple client apps by running the following command in the client directory:

<code>java -cp target/client-1.0-SNAPSHOT-jar-with-dependencies.jar com.jgrajber.client.Client
</code>

## Testing

You can test the server's response by providing following credentials

<table>
    <tr>
        <th>login</th>
        <th>password</th>
    </tr>
    <tr>
        <td>user1</td>
        <td>test</td>
    </tr>
    <tr>
        <td>user2</td>
        <td>test</td>
    </tr>
    <tr>
        <td>user3</td>
        <td>test</td>
    </tr>
    <tr>
        <td>user4</td>
        <td>test</td>
    </tr>
</table>