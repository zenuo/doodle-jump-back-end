package yz.doodlejump.core;
/**
 * 会话对象
 */
public class Session {
    /**
     * 会话ID
     */
    private String id;
    /**
     * 持有该会话的用户ID
     */
    private long userID;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 过期时间
     */
    private long lastActiveTime;

    public Session() {
    }

    public Session(String id, long userID, String userName, long createTime, long lastActiveTime) {
        this.id = id;
        this.userID = userID;
        this.userName = userName;
        this.createTime = createTime;
        this.lastActiveTime = lastActiveTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(long lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", userID=" + userID +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                ", lastActiveTime=" + lastActiveTime +
                '}';
    }
}