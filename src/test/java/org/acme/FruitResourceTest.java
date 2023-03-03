package org.acme;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
@TestInstance(PER_CLASS)
class FruitResourceTest {
    
    private static EntityManager em;

    @Inject
    public FruitResourceTest(final EntityManager em) {
        FruitResourceTest.em = em;
    }

    @Nested
    @DisplayName("This is a special Test in an Inner class")
    class AFruitInnerClass {

        private static final Integer lemonId = 4;

        @BeforeAll
        // Commenting out @Transactional annotations makes tests to pass.
        @Transactional
        static void doSomeImportantStuffBeforeAllTests() {
            final var lemon = new Fruit(lemonId, "Lemon");

            em.merge(lemon);
        }

        @Test
        void testGetFruitByIdEndpoint() {
            given()
                    .when().get("/fruits/4")
                    .then()
                    .statusCode(200)
                    .body(containsString("Lemon"));
        }

        @AfterAll
        // Commenting out @Transactional annotations makes tests to pass.
        @Transactional
        static void doSomeImportantStuffAfterAllTests() {
            em.createNativeQuery("DELETE FROM fruit WHERE id = :lemonId")
                    .setParameter("lemonId", lemonId).executeUpdate();
        }
    }

    @Nested
    @DisplayName("This is a special Test in an Inner class")
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
