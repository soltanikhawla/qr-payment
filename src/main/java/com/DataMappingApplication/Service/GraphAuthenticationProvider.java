package com.DataMappingApplication.Service;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.http.IHttpRequest;

public class GraphAuthenticationProvider implements IAuthenticationProvider {

    private String accessToken;

    public GraphAuthenticationProvider(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void authenticateRequest(IHttpRequest request) {
        request.addHeader("Authorization", "Bearer " + accessToken);
    }
}