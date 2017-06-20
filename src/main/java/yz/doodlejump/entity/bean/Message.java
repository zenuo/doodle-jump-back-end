package yz.doodlejump.entity.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 消息类
 */
public final class Message implements Serializable {
    @JsonProperty
    private Long id;
    @JsonProperty
    private Byte scope;
    @JsonProperty
    private Integer source;
    @JsonProperty
    private Integer target;
    @JsonProperty
    private Long createTime;
    @JsonProperty
    private Long sendTime;
    @JsonProperty
    private String content;

    public Message() {
    }

    public Message(Byte scope, Integer source, Integer target, Long createTime, String content) {
        this.scope = scope;
        this.source = source;
        this.target = target;
        this.createTime = createTime;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getScope() {
        return scope;
    }

    public void setScope(Byte scope) {
        this.scope = scope;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageService{" +
                "id=" + id +
                ", scope=" + scope +
                ", source=" + source +
                ", target=" + target +
                ", createTime=" + createTime +
                ", sendTime=" + sendTime +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (scope != null ? !scope.equals(message.scope) : message.scope != null) return false;
        if (source != null ? !source.equals(message.source) : message.source != null) return false;
        if (target != null ? !target.equals(message.target) : message.target != null) return false;
        if (createTime != null ? !createTime.equals(message.createTime) : message.createTime != null) return false;
        if (sendTime != null ? !sendTime.equals(message.sendTime) : message.sendTime != null) return false;
        return content != null ? content.equals(message.content) : message.content == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
