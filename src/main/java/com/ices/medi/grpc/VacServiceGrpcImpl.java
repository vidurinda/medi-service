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
}
