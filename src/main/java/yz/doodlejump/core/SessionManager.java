package yz.doodlejump.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 会话管理器
 */
public class SessionManager {

    private static final SessionManager INSTANCE = new SessionManager();

    private static Logger LOGGER;

    private static final List<Session> sessionList = new ArrayList<>();

    private SessionManager() {
        LOGGER = LoggerFactory.getLogger(SessionManager.class);
        if (INSTANCE != null) {
            return;
        }
        //Thread expireWatcherThread = new Thread(new ExpireWatcher());
        //expireWatcherThread.start();
        LOGGER.info("SessionManager initialized");
    }
}
