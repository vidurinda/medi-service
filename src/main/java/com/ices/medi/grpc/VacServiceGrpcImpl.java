package com.ices.medi.grpc;

import com.ices.vac.GreetingRequest;
import com.ices.vac.GreetingResponse;
import com.ices.vac.GreetingServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

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

    @Override
    public void greetMany(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {

        try{
            for(int i= 0 ; i < 10 ;i++){
                String result = "Hello " + request.getGreeting().getFirstName() + "This is " + i + "Greeting !";

                GreetingResponse response = GreetingResponse.newBuilder()
                        .setResult(result)
                        .build();
                responseObserver.onNext(response);
                Thread.sleep(2000);
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            responseObserver.onCompleted();
        }

    }
}
