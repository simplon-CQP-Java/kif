package co.simplon.kif.core.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import co.simplon.kif.core.model.Computer;
import co.simplon.kif.core.model.Room;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date start;
	private Date end;
	private Date createdAt;
	
	@ManyToOne(cascade = { CascadeType.MERGE }, targetEntity = Computer.class)
    @JoinColumn(name = "computerId")
    private Computer computer;

    @ManyToOne(cascade = { CascadeType.MERGE }, targetEntity = Room.class)
    @JoinColumn(name = "roomId")
    private Room room;
    
    @ManyToOne(cascade = { CascadeType.MERGE }, targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;

    public Booking() {
    	
    }
    
	public Booking(Date start, Date end, Date createdAt, Computer computer, Room room, User user) {
		super();
		this.start = start;
		this.end = end;
		this.createdAt = createdAt;
		this.computer = computer;
		this.room = room;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}