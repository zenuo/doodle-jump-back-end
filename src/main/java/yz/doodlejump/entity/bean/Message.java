package yz.doodlejump.entity.bean;

/**
 * 消息类
 */
public final class Message {
    private Long id;
    private Byte scope;
    private Integer source;
    private Integer target;
    private Long createTime;
    private Long sendTime;
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
        return "Message{" +
                "id=" + id +
                ", scope=" + scope +
                ", source=" + source +
                ", target=" + target +
                ", createTime=" + createTime +
                ", sendTime=" + sendTime +
                ", content='" + content + '\'' +
                '}';
    }
}
