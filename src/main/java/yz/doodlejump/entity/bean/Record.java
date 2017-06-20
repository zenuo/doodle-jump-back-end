package yz.doodlejump.entity.bean;

import java.io.Serializable;

/**
 * 游戏结果
 */
public final class Record implements Serializable {
    private Integer id;
    private Integer player;
    private Integer mark;
    private Long time;

    public Record() {
    }

    public Record(Integer id, Integer player, Integer mark, Long time) {
        this.id = id;
        this.player = player;
        this.mark = mark;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", player=" + player +
                ", mark=" + mark +
                ", time=" + time +
                '}';
    }
}
