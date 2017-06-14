package yz.doodlejump.entity.bean;

import yz.doodlejump.core.Util;

import java.util.UUID;

/**
 * 会话对象
 */
public class Session {

    private String id;

    private Integer playerId;

    private String playerName;

    private Long createTime;

    private Long lastActiveTime;

    public Session() {
    }

    public Session(Integer playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.createTime = Util.getTimeLong();
        this.lastActiveTime = Util.getTimeLong();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Long lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", createTime=" + createTime +
                ", lastActiveTime=" + lastActiveTime +
                '}';
    }
}