# Intergrate gPRC with Spring Boot.
 Spring Boot & gRPC integration
 
 Getting started with gRPC
 
This sample for intergrate gRPC with Spring Boot and write a service using the artifact create by https://github.com/vidurinda/vac-service

# Dependancies

following dependencies are require to intergrate gRPC with Spring boot

<pre>
 implementation 'org.springframework.boot:spring-boot-starter-web'
 implementation 'io.github.lognet:grpc-spring-boot-starter:3.4.3'
 compileOnly 'org.projectlombok:lombok'
 developmentOnly 'org.springframework.boot:spring-boot-devtools'
 annotationProcessor 'org.projectlombok:lombok'
 
 // locally published artifact from the above sample project
 compile group: 'com.ices.vac', name: 'vac-service', version: '0.0.100'
 
 implementation 'io.grpc:grpc-netty-shaded:1.40.1'
 implementation 'io.grpc:grpc-protobuf:1.40.1'
</pre> 

<pre>
repositories {
	mavenCentral()
	mavenLocal()

}
</pre>

Add mavenLocal() to use the locally published artifact.

# Using as gRPC server.
Need to implement services annotated with '@GRpcService' also need to allocate a grpc port run as a gRPC server

in the following sample service we created by extending the sample service we have introduced in the protobuf project 'GreetingServiceGrpc'<br>
<pre>
@GRpcService
public class VacServiceGrpcImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greet(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {
        GreetingResponse response = GreetingResponse.newBuilder()
                .setResult("Hello Janaka "+ request.getGreeting().getFirstName())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
</pre>

Annotating the service with @GRpcService tells to serve as a gRPC server using the port given in the application.properties.
grpc.port=6565

OOK, we have successfuly integrate gRPC with Spring boot and expose a service '/greet', now the time to test the service.

# Testing the service

* Download the BloomRPC, the gRPC client(like postman for REST) to test our gRPC service endpoints using following url.

https://github.com/uw-labs/bloomrpc

* Open the BloomRPC and open the proto file 'Greeting.proto' from the 'vac-service/src/main/proto/' (our previous protobuf project)

![Screen Shot 2021-08-29 at 2 05 16 PM](https://user-images.githubusercontent.com/14219550/131244216-f3ba7251-1817-41b1-9265-f03a13a47112.png)

* Enter the url localhost:6565 (or your IP and grpc.port)
* Select the grpc service you want from the left panel (Greet in the example) and fill the request parameters.
* Click on the green play button in the middle and wait for the response,

That's it, you have the greeting response, we are success in the intergration of gRPC with Spring boot.
