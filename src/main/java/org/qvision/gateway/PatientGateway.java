package org.qvision.gateway;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.qvision.client.PatientClient;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;

@Path("/patient-service")
public class PatientGateway {

    @RestClient
    PatientClient patientClient;

    @Tag(name = "Patient-Gateway", description = "`Routes all traffic to PatientService` ")

    @Operation(summary = "Everybody can access this test endpoint.", description = "**No security:**")
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        // System.out.println("Test endpoint in patient-gateway was called!");
        return "API Gateway is alive";
    }

    @Operation(summary = "Everybody can access this public endpoint.", description = "**No security:**")
    @GET
    @Path("/public")
    @Produces(MediaType.TEXT_PLAIN)
    public String publicEndpoint() {
        System.out.println("Public endpoint called!");
        return patientClient.getPublicPatientData();
    }

    @Operation(summary = "Authenticated users can access this endpoint.", description = "**Authenticated:**")
    @GET
    @Path("/authenticated")
    @Produces(MediaType.TEXT_PLAIN)
    @Authenticated
    public String authenticatedEndpoint() {
        // System.out.println("Authenticated endpoint called!!");
        return patientClient.getAuthenticatedPatientData();
    }

    @Operation(summary = "Authorized users with role PATIENT can access this endpoint.", description = "**Role:** `PATIENT`")
    @GET
    @Path("/authorized")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("PATIENT")
    public String securedEndpoint() {
        System.out.println("Only Patients authorized endpoint called!!");
        return patientClient.getAuthorizedPatientData();
    }    
}

