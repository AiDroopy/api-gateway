package org.qvision.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "doctor-service")
public interface DoctorClient {
    
    @GET
    @Path("/public")
    @Produces(MediaType.TEXT_PLAIN)
    String getPublicDoctorData();

    @GET
    @Path("/authenticated")
    @Produces(MediaType.TEXT_PLAIN)
    String getAuthenticatedDoctorData();

    @GET
    @Path("/authorized")
    String getAuthorizedDoctorData();
}
