package org.qvision.gateway;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.qvision.client.DoctorClient;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/doctor-service")
public class DoctorGateway {

    @RestClient
    DoctorClient doctorClient;

    @Tag(name = "Doctor-Gateway", description = "`Routes all traffic to DoctorService` ")

    @Operation(summary = "Everybody can access this test endpoint.", description = "**No security:**")
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String doctorTest() {
        // System.out.println("Test endpoint in patient-gateway was called!");
        return "API Gateway is alive";
    }

    @GET
    @Path("/public")
    @Produces(MediaType.TEXT_PLAIN)
    public String getData() {
        // System.out.println("Everybody can call this public endpoint");
        return doctorClient.getPublicDoctorData();
    }


    @GET
    @Path("/authenticated")
    @Produces(MediaType.TEXT_PLAIN)
    public String authenticatedEndpoint() {
        // System.out.println("Only authenticated users can call this endpoint!");
        return doctorClient.getAuthenticatedDoctorData();
    }

    @GET
    @Path("/authorized")
    @RolesAllowed("DOCTOR")
    @Produces(MediaType.TEXT_PLAIN)
    public String doctorOnlyEndpoint() {
        // System.out.println("Only doctors are authorized to call this secured endpoint!");
        return doctorClient.getAuthorizedDoctorData();
    }
}

