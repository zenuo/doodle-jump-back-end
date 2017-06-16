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

    @Path("send")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public int send(final Message message){
        MessageManager.send(message);
        return 0;
    }

    @Path("get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Message[] get(@QueryParam("session") final String sessionId) throws Exception{
        int target = SessionManager.getPlayerIdBySessionId(sessionId);
        Message[] messages = MessageManager.get(target);
        return messages;
    }
}
