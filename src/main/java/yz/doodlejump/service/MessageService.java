package yz.doodlejump.service;

import yz.doodlejump.core.MessageManager;
import yz.doodlejump.core.SessionManager;
import yz.doodlejump.entity.bean.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 消息类
 */
@Path("message")
public class MessageService {

    @Path("send-{session}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public int send(@PathParam("session") final String sessionId, final Message message){
        if (SessionManager.isSessionValid(sessionId)) {
            MessageManager.send(message);
            return 0;
        } else {
            return 1;
        }
    }

    @Path("get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Message[] get(@QueryParam("session") final String sessionId) throws Exception{
        if (SessionManager.isSessionValid(sessionId)) {
            int target = SessionManager.getPlayerIdBySessionId(sessionId);
            return MessageManager.get(target);
        } else {
            return null;
        }
    }
}
