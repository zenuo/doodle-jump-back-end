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
    private  Integer life;

    @JsonProperty
    private Integer coin;

    @JsonProperty
    private Float x;

    @JsonProperty
    private Float y;

    public PlayerStatus() {
    }

    public PlayerStatus(Integer id, Integer life, Integer coin, Float x, Float y) {
        this.id = id;
        this.life = life;
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

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
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
                ", life=" + life +
                ", coin=" + coin +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
