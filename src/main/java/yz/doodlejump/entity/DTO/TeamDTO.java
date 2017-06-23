package yz.doodlejump.entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 团队状态类
 */
public final class TeamDTO implements Serializable{
    @JsonProperty
    private Integer id;

    @JsonProperty
    private Integer playerCount;

    @JsonProperty
    private Boolean open;

    @JsonProperty
    private Long createTime;

    public TeamDTO(Integer id, Integer playerCount, Boolean open, Long createTime) {
        this.id = id;
        this.playerCount = playerCount;
        this.open = open;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(Integer playerCount) {
        this.playerCount = playerCount;
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

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", playerCount=" + playerCount +
                ", open=" + open +
                ", createTime=" + createTime +
                '}';
    }
}
