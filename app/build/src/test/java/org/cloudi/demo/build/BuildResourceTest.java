package org.cloudi.demo.build;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import javax.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BuildResourceTest {

    @Test
    public void testList() {
        given()
                .when().get("/builds")
                .then()
                .statusCode(200)
                .body("$.size()", is(2),
                        "name", containsInAnyOrder("RHEL8.2-build-01", "OCP4.5.1-build-01"),
                        "description", containsInAnyOrder("Red Hat Enterprise Linux 8.2 Build-01", "OpenShift Container Platform 4.5.1 Build-01"));
    }

    @Test
    public void testAdd() {
        given()
                .body("{\"name\": \"EAP7.2-Build-10\", \"description\": \"JBoss EAP7.2 Build-10\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/builds")
                .then()
                .statusCode(200)
                .body("$.size()", is(3),
                        "name", containsInAnyOrder("RHEL8.2-build-01", "OCP4.5.1-build-01","EAP7.2-Build-10"),
                        "description", containsInAnyOrder("Red Hat Enterprise Linux 8.2 Build-01", "OpenShift Container Platform 4.5.1 Build-01","JBoss EAP7.2 Build-10"));

        given()
                .body("{\"name\": \"EAP7.2-Build-10\", \"description\": \"JBoss EAP7.2 Build-10\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .delete("/builds")
                .then()
                .statusCode(200)
                .body("$.size()", is(2),
                        "name", containsInAnyOrder("RHEL8.2-build-01", "OCP4.5.1-build-01"),
                        "description", containsInAnyOrder("Red Hat Enterprise Linux 8.2 Build-01", "OpenShift Container Platform 4.5.1 Build-01"));
    }
}
