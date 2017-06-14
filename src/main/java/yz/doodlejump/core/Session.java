package yz.doodlejump.core;
/**
 * 会话对象
 */
public class Session {

    private String id;

    private Integer userId;

    private String userName;

    private long createTime;

    private long lastActiveTime;

    public Session() {
    }

    public Session(String id, Integer userId, String userName, long createTime, long lastActiveTime) {
        this.id = id;
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                ", lastActiveTime=" + lastActiveTime +
                '}';
    }
}