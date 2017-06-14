package yz.doodlejump.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yz.doodlejump.entity.bean.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 消息管理器
 */
public class MessageManager {

    private static final MessageManager INSTANCE = new MessageManager();

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageManager.class);

    private static final List<Message> MESSAGE_LIST = Collections.synchronizedList(new ArrayList<>());

    private MessageManager() {

    }

    public static void init() {
        LOGGER.info("MessageManager initialized.");
    }

    static List<Message> getMessageList() {
        return MESSAGE_LIST;
    }

    public static void send(Message message) {
        MESSAGE_LIST.add(message);
    }


}
