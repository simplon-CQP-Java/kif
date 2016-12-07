package co.simplon.kif.core.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade = { CascadeType.MERGE }, targetEntity = Message.class)
    @JoinColumn(name = "messageId")
	private Message message;
	
	@ManyToOne(cascade = { CascadeType.MERGE }, targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;
	private String content;
	private Date createdAt;
	private boolean sended;

	public Reply() {
		super();
	}

	public Reply(Message message, String content, Date createdAt, User user) {
		super();
		this.message = message;
		this.content = content;
		this.createdAt = createdAt;
		this.user = user;
		this.sended = false;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public boolean isSended() {
		return sended;
	}
	public void setSended(boolean sended) {
		this.sended = sended;
	}
}
