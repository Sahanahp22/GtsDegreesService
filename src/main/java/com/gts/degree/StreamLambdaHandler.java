package com.gts.degree;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class StreamLambdaHandler implements RequestStreamHandler {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try {
            LambdaContainerHandler.getContainerConfig().setInitializationTimeout(200_000);
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(GTSDegreesService.class);
        } catch (ContainerInitializationException e) {
        	System.out.println("***Exception to create SpringBootLambdaContainerHandler***");
        }
    }
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {	
    	handler.proxyStream(inputStream, outputStream, context);
    }
}
