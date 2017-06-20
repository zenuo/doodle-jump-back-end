package yz.doodlejump.entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 玩家状态类
 */
public final class PlayerStatus implements Serializable{
    @JsonProperty
    private Integer id;

    @JsonProperty
    private Integer coin;

    @JsonProperty
    private Float x;

    @JsonProperty
    private Float y;

    public PlayerStatus() {
    }

    public PlayerStatus(Integer id, Integer coin, Float x, Float y) {
        this.id = id;
        this.coin = coin;
        this.x = x;
        this.y = y;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "PlayerStatus{" +
                "id=" + id +
                ", coin=" + coin +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
