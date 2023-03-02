package org.acme;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/fruits")
public class FruitResource {

    private final FruitService fruitService;

    @Inject
    public FruitResource(final FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Fruit createFruit(final Fruit fruit) {
        return this.fruitService.saveFruit(fruit);
    }

    @GET
    @Path("/{fruitId}")
    @Produces(APPLICATION_JSON)
    public Fruit getFruitById(@PathParam("fruitId") final Integer fruitId) {
        return this.fruitService.findFruitById(fruitId);
    }

}
