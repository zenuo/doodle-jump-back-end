package yz.doodlejump.entity.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import yz.doodlejump.constant.TeamConstant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 队伍类
 */
public final class Team implements Serializable {
    @JsonProperty
    private Integer id;

    @JsonProperty
    private List<Integer> players;

    @JsonProperty
    private List<Integer> avators;

    @JsonProperty
    private List<Integer> coins;

    @JsonProperty
    private Integer createPlayerId;

    @JsonProperty
    private Boolean open;

    @JsonProperty
    private Long createTime;

    @JsonProperty
    private Long lastActiveTime;

    public Team() {
    }

    public Team(int id, int createPlayerId, int avator, int coin) {
        this.id = id;
        this.createPlayerId = createPlayerId;
        /*
        初始化列表
         */
        this.players = new ArrayList<>(3);
        this.avators = new ArrayList<>(3);
        this.coins = new ArrayList<>(3);
        /*
        添加创建玩家到玩家列表
         */
        players.add(createPlayerId);
        avators.add(avator);
        coins.add(coin);
        this.open = true;
        this.createTime = System.currentTimeMillis();
        this.lastActiveTime = System.currentTimeMillis();
    }

    /**
     * 加入队伍
     *
     * @param userId 需要加入队伍的玩家id
     * @return 若加入成功，返回0；否则返回1
     */
    public int join(Integer userId) {
        /*
        判断队伍是否开放
         */
        if (open) {
            players.add(userId);
            /*
            玩家人数达到最大值时关闭队伍
             */
            if (players.size() == TeamConstant.MAX_NUMBER_OF_PLAYER_IN_A_TEAM) {
                this.open = false;
            }
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 退出队伍
     *
     * @param userId 希望退出队伍的玩家id
     * @return 若加入成功，返回0；否则返回1
     */
    public int exit(Integer userId) {
        if (players.remove(userId)) {
            this.open = true;
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 锁定队伍
     *
     * @return 若锁定成功，返回0；否则返回1
     */
    public int lock() {
        if (open) {
            open = false;
            return 0;
        } else {
            return 1;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getPlayers() {
        return players;
    }

    public void setPlayers(List<Integer> players) {
        this.players = players;
    }

    public List<Integer> getAvators() {
        return avators;
    }

    public void setAvators(List<Integer> avators) {
        this.avators = avators;
    }

    public List<Integer> getCoins() {
        return coins;
    }

    public void setCoins(List<Integer> coins) {
        this.coins = coins;
    }

    public Integer getCreatePlayerId() {
        return createPlayerId;
    }

    public void setCreatePlayerId(Integer createPlayerId) {
        this.createPlayerId = createPlayerId;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
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
        return "Team{" +
                "id=" + id +
                ", players=" + players +
                ", createPlayerId=" + createPlayerId +
                ", open=" + open +
                ", createTime=" + createTime +
                ", lastActiveTime=" + lastActiveTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != null ? !id.equals(team.id) : team.id != null) return false;
        if (players != null ? !players.equals(team.players) : team.players != null) return false;
        if (createPlayerId != null ? !createPlayerId.equals(team.createPlayerId) : team.createPlayerId != null)
            return false;
        if (open != null ? !open.equals(team.open) : team.open != null) return false;
        if (createTime != null ? !createTime.equals(team.createTime) : team.createTime != null) return false;
        return lastActiveTime != null ? lastActiveTime.equals(team.lastActiveTime) : team.lastActiveTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (players != null ? players.hashCode() : 0);
        result = 31 * result + (createPlayerId != null ? createPlayerId.hashCode() : 0);
        result = 31 * result + (open != null ? open.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastActiveTime != null ? lastActiveTime.hashCode() : 0);
        return result;
    }
}
