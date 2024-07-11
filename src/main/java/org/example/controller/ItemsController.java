package org.example.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.constants.ItemsMessages;
import org.example.dto.Items;
import org.example.service.ItemsService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Path("/items")
public class ItemsController {
    private ObjectMapper mapper = new ObjectMapper();
    private ItemsService itemsService = new ItemsService();

    @POST
    @Path("/save")
    @Consumes("application/json")
    @Produces("application/json")
    public Response saveGoods(String json){

        try {
            System.out.println("Received JSON: " + json + " the end");
            Items items = mapper.readValue(json, Items.class);
            Items savedItem = itemsService.saveItem(items);
            return Response.ok(mapper.writeValueAsString(savedItem)).build();
        }

        catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ItemsMessages.NOT_VALID_PARAMS).build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces("application/json")
    public Response getItems() {
        List<Items> itemsList = itemsService.getItems();
        try {
            String json = mapper.writeValueAsString(itemsList);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ItemsMessages.CONVERTING_ERROR).build();
        }
    }

    @GET
    @Path("/get/{uuid}")
    @Produces("application/json")
    public Response getItem(@PathParam("uuid") String uuid) {
        try {
            Items item = itemsService.getItem(uuid);
            if (item == null) {
                return Response.status(Response.Status.NOT_FOUND).entity(ItemsMessages.NOT_FOUND).build();
            }
            String json = mapper.writeValueAsString(item);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ItemsMessages.CONVERTING_ERROR).build();
        }
    }


    @PUT
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateItem(String json){
        try {
            Items item = mapper.readValue(json, Items.class);
            Items updatedItem = itemsService.updateItem(item);
            return Response.ok(mapper.writeValueAsString(updatedItem)).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity(ItemsMessages.NOT_VALID_PARAMS).build();
        }
    }

    @DELETE
    @Path("/delete/{uuid}")
    @Produces("application/json")
    public Response deleteItem(@PathParam("uuid") String uuid) {
        try {
            itemsService.deleteItem(uuid);
            return Response.ok().build();
        }

        catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ItemsMessages.NOT_FOUND).build();
        }
    }
}
