package com.github.simplesteph.grpc.greeting.server;

import com.proto.greet.Greet;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;

import io.grpc.stub.StreamObserver;

public class GreetingServerImpl extends GreetServiceGrpc.GreetServiceImplBase {
    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        // extract the filed we need
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();
        String lastName = greeting.getLastName();
        //create the response
        String result = "Hello " + firstName +" "+ lastName;
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();
       //send the response
        responseObserver.onNext(response);
        // complete the RPC call
        responseObserver.onCompleted();
    }
}
