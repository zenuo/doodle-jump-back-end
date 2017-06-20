package yz.doodlejump.entity.bean;

import yz.doodlejump.core.Util;

import java.util.UUID;

/**
 * 会话对象
 */
public final class Session {

    private String id;

    private int playerId;

    private String playerName;

    private int playerRecord;

    private long createTime;

    private long lastActiveTime;

    private int teamId;

    public Session() {
    }

    public Session(int playerId, String playerName, int playerRecord) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerRecord = playerRecord;
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.createTime = System.currentTimeMillis();
        this.lastActiveTime = System.currentTimeMillis();
        this.teamId = -1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerRecord() {
        return playerRecord;
    }

    public void setPlayerRecord(int playerRecord) {
        this.playerRecord = playerRecord;
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

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", playerRecord=" + playerRecord +
                ", createTime=" + createTime +
                ", lastActiveTime=" + lastActiveTime +
                ", teamId=" + teamId +
                '}';
    }
}