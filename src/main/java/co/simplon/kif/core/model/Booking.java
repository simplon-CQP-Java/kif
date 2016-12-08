package co.simplon.kif.core.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;
	@ManyToOne(cascade = { CascadeType.MERGE }, targetEntity = Room.class)
    @JoinColumn(name = "roomId")
	@Expose
	private Room room;
	@ManyToOne(cascade = { CascadeType.MERGE }, targetEntity = Computer.class)
    @JoinColumn(name = "computerId")
	@Expose
	private Computer computer;
	@ManyToOne(cascade = { CascadeType.MERGE }, targetEntity = User.class)
    @JoinColumn(name = "userId")
	@Expose
	private User user;
	@Expose
	private Date start;
	@Expose
	private Date end;
	@Expose
	private Date createdAt;

	public Booking() {
		super();
	}

	public Booking(Room room, Computer computer, Date start, Date end, Date createdAt) {
		super();
		this.room = room;
		this.computer = computer;
    	this.start = start;
    	this.end = end;
    	this.createdAt = createdAt;
	}
	
	public Booking(Room room, Computer computer, User user, Date start, Date end, Date createdAt) {
		super();
		this.room = room;
		this.computer = computer;
		this.user = user;
    	this.start = start;
    	this.end = end;
    	this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
