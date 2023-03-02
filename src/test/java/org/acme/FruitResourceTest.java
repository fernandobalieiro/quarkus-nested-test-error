package org.acme;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
@TestInstance(PER_CLASS)
class FruitResourceTest {

    @BeforeAll
    void doSomeImportantStuffBeforeAllTests() {
        //
    }

    @Nested
    @DisplayName("This is a special Test in an Inner class")
    @TestInstance(PER_CLASS)
    class AFruitInnerClass {

        @BeforeAll
        // Commenting out @Transactional annotations makes tests to pass.
        @Transactional
        static void doSomeImportantStuffBeforeAllTests() {
            //
        }

        @Test
        void testGetFruitByIdEndpoint() {
            given()
                    .when().get("/fruits/1")
                    .then()
                    .statusCode(200)
                    .body(containsString("Banana"));
        }
    }

    @Nested
    @DisplayName("This is a special Test in an Inner class")
    @TestInstance(PER_CLASS)
    class AnotherFruitInnerClass {

        @BeforeAll
        // Commenting out @Transactional annotations makes tests to pass.
        @Transactional
        static void doSomeImportantStuffBeforeAllTests() {
            //
        }

        @Test
        void testGetFruitByIdEndpointAgain() {
            given()
                    .when().get("/fruits/1")
                    .then()
                    .statusCode(200)
                    .body(containsString("Banana"));
        }
    }
}
