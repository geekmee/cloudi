package org.cloudi.demo.build;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/builds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BuildResource {

    private Set<Build> builds = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public BuildResource() {
        builds.add(new Build("RHEL8.2-build-01", "Red Hat Enterprise Linux 8.2 Build-01"));
        builds.add(new Build("OCP4.5.1-build-01", "OpenShift Container Platform 4.5.1 Build-01"));
    }

    @GET
    public Set<Build> list() {
        return builds;
    }

    @POST
    public Set<Build> add(Build build) {
        builds.add(build);
        return builds;
    }

    @DELETE
    public Set<Build> delete(Build build) {
        builds.removeIf(existingBuild -> existingBuild.name.contentEquals(build.name));
        return builds;
    }
}
