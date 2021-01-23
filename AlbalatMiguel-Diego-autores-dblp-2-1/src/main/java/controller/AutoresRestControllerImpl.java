package controller;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;

@Path("autores")
@Produces(MediaType.APPLICATION_JSON)
@Api(produces = "application/json", value = "Operations pertaining to manager blood donors in the application")
public class AutoresRestControllerImpl implements IAutoresRestController {

}
