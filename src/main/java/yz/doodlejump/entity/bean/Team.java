package yz.doodlejump.entity.bean;

import yz.doodlejump.constant.TeamConstant;
import yz.doodlejump.core.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 队伍类
 */
public class Team {
    private Integer id;
    private List<Integer> players;
    private Integer createPlayerId;
    private Boolean isOpen;
    private Long createTime;
    private Long closeTime;

    public Team() {
    }

    public Team(Integer id, Integer createPlayerId) {
        this.id = id;
        this.createPlayerId = createPlayerId;
        /*
        初始化玩家列表
         */
        this.players = new ArrayList<>(3);
        /*
        添加创建玩家到玩家列表
         */
        players.add(createPlayerId);
        this.isOpen = true;
        this.createTime = Util.getTimeLong();
    }

    /**
     * 加入队伍
     * @param userId 需要加入队伍的玩家id
     * @return 若加入成功，返回0；否则返回1
     */
    public int join(Integer userId) {
        /*
        判断队伍是否开放
         */
        if (isOpen) {
            players.add(userId);
            /*
            玩家人数达到最大值时关闭队伍
             */
            if (players.size() == TeamConstant.MAX_NUMBER_OF_PLAYER_IN_A_TEAM) {
                this.isOpen = false;
            }
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 退出队伍
     * @param userId 希望退出队伍的玩家id
     * @return 若加入成功，返回0；否则返回1
     */
    public int exit(Integer userId) {
        if (players.remove(userId)) {
            this.isOpen = true;
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

    public Integer getCreatePlayerId() {
        return createPlayerId;
    }

    public void setCreatePlayerId(Integer createPlayerId) {
        this.createPlayerId = createPlayerId;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", players=" + players +
                ", createPlayerId=" + createPlayerId +
                ", isOpen=" + isOpen +
                ", createTime=" + createTime +
                ", closeTime=" + closeTime +
                '}';
    }
}
