package org.qvision.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "patient-service")
public interface PatientClient {

    @GET
    @Path("/public")
    @Produces(MediaType.TEXT_PLAIN)
    String getPublicPatientData();

    @GET
    @Path("/authenticated")
    @Produces(MediaType.TEXT_PLAIN)
    String getAuthenticatedPatientData();

    @GET
    @Path("/authorized")
    @Produces(MediaType.TEXT_PLAIN)
    String getAuthorizedPatientData();
}

