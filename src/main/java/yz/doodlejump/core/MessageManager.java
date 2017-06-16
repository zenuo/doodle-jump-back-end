package yz.doodlejump.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yz.doodlejump.constant.MessageConstant;
import yz.doodlejump.entity.bean.Message;

import java.util.*;

/**
 * 消息管理器
 */
public class MessageManager {

    private static final MessageManager INSTANCE = new MessageManager();

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageManager.class);

    private static final Map<Integer, Message> MESSAGE_MAP = Collections.synchronizedMap(new LinkedHashMap<>());

    private MessageManager() {

    }

    public static void init() {
        LOGGER.info("MessageManager initialized.");
    }

    static Map<Integer, Message> getMessageList() {
        return MESSAGE_MAP;
    }

    public static void send(Message message) {
        if (message.getScope().equals(MessageConstant.SCOPE_OF_PRIVATE_MESSAGE)) {
            //私信
            MESSAGE_MAP.put(message.getTarget(), message);
        } else if (message.getScope().equals(MessageConstant.SCOPE_OF_TEAM_MESSAGE)) {
            //群发
            Integer[] players = TeamManager.getPlayersByPlayerId(message.getSource());
            if (players != null) {
                for (Integer i : players) {
                    MESSAGE_MAP.put(i, message);
                }
            }
        }
    }

    /**
     * 接收信息
     * @param target 接收人
     * @return Message数组
     */
    public static Message[] get(Integer target) {
        Iterator<Map.Entry<Integer, Message>> iterator = MESSAGE_MAP.entrySet().iterator();
        List<Message> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Message message = iterator.next().getValue();
            if (message.getTarget().equals(target)) {
                message.setSendTime(System.currentTimeMillis());
                list.add(message);
                iterator.remove();
            }
        }
        return list.toArray(new Message[list.size()]);
    }

}
