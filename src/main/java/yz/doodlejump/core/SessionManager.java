package yz.doodlejump.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yz.doodlejump.constant.SessionConstant;
import yz.doodlejump.entity.bean.Player;
import yz.doodlejump.entity.bean.Session;

import java.util.*;

/**
 * 会话管理器
 */
public class SessionManager {

    private static final SessionManager INSTANCE = new SessionManager();

    private static Logger LOGGER = LoggerFactory.getLogger(SessionManager.class);

    private static final Map<String, Session> sessionMap =
            Collections.synchronizedMap(new LinkedHashMap<>());

    public static void init() {
        LOGGER.info("SessionManager initialized");
    }

    private SessionManager() {
        Thread expireWatcherThread = new Thread(new ExpireWatcher());
        expireWatcherThread.start();
    }

    public static Map<String, Session> getSessionMap() {
        return sessionMap;
    }

    public static boolean isUserOnline(final String userName) {
        for (Map.Entry<String, Session> entry : sessionMap.entrySet()) {
            if (entry.getValue().getPlayerName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public static void invalidate(final String id) {
        sessionMap.remove(id);
    }

    public static boolean isSessionValid(final String id) {
        return sessionMap.get(id) == null;
    }

    public static Session create(final Player player) {
        Session session = new Session(
                player.getId(),
                player.getName()
        );
        sessionMap.put(session.getId(), session);
        LOGGER.info("create session: " + session);
        return session;
    }

    /**
     * 根据会话id获取对应的用户id
     *
     * @param sessionId 会话id
     * @return 用户id
     */
    public static Integer getPlayerIdBySessionId(final String sessionId) {
        return sessionMap.get(sessionId).getPlayerId();
    }
}

/**
 * 会话过期监视线程
 */
class ExpireWatcher implements Runnable {

    /**
     * 日志记录器实例
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpireWatcher.class);

    /**
     * 无限循环，每隔EXPIRE_WATCHER_SLEEP_TIME_IN_MILLIS时间遍历session列表，将过期的会话删除
     */
    @Override
    public void run() {
        LOGGER.info("ExpireWatcher running");
        while (true) {
            long nowTime = Util.getTimeLong();
            //获取迭代器
            Iterator<Map.Entry<String, Session>> iterator =
                    SessionManager.getSessionMap().entrySet().iterator();

            while (iterator.hasNext()) {
                Session session = iterator.next().getValue();
                //判定是否过期
                if (nowTime - session.getLastActiveTime() > SessionConstant.SESSION_EXPIRE_TIME_IN_SECOND) {
                    //去除会话
                    LOGGER.info("expire: " + session.toString());
                    iterator.remove();
                }
            }
            //睡眠
            try {
                Thread.sleep(SessionConstant.EXPIRE_WATCHER_SLEEP_TIME_IN_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}