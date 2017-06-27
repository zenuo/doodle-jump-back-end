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
    private  Integer l;

    @JsonProperty
    private Integer c;

    @JsonProperty
    private Integer d;

    @JsonProperty
    private Float x;

    @JsonProperty
    private Float y;

    public PlayerStatus() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
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
}
