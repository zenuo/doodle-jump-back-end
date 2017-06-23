package yz.doodlejump.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    //日志记录对象
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    /**
     * 发送信息
     *
     * @param sessionId 会话id
     * @param message   信息对象
     * @return 若发送成功，返回0；否则返回1
     */
    @Path("send-{session}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public int send(@PathParam("session") final String sessionId,
                    final Message message) {
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("send " + message.toString());
            MessageManager.send(message);
            return 0;
        } else {
            return 1;
        }
    }

    @Path("get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Message[] get(@QueryParam("session") final String sessionId) {
        if (SessionManager.isValid(sessionId)) {
            LOGGER.info("get" + sessionId);
            int target = SessionManager.getPlayerIdBySessionId(sessionId);
            return MessageManager.get(target);
        } else {
            return null;
        }
    }
}
