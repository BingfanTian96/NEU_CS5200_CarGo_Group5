package car.model;

import java.util.Date;

/**
 * Message model for Cargo.
 * 
 * @author Jianqing Ma, Sen Yan, Bo Chen, Bingfan Tian, Qihui Liu, Kailun He
 *
 */

public class Messages {
    protected int messageId;
    protected Date sendTime;
    protected String content;
    protected int fromId;
    protected int toId;

    public Messages(Date sendTime, String content, int fromId, int toId) {
        this.sendTime = sendTime;
        this.content = content;
        this.fromId = fromId;
        this.toId = toId;
    }
    
    public Messages(int messageId, Date sendTime, String content, int fromId, int toId) {
        this.messageId = messageId;
        this.sendTime = sendTime;
        this.content = content;
        this.fromId = fromId;
        this.toId = toId;
    }

    public Messages(int messageId) {
        this.messageId = messageId;
    }

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

    
}
