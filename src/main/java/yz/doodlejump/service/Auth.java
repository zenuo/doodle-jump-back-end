package yz.doodlejump.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 认证服务
 */
@Path("/auth")
public class Auth {

    @Path("/h")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello(){
        return "Hello World!";
    }
}
