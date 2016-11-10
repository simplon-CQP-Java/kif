package co.simplon.kif.core.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer roomId;
	private Integer computerId;
	private Integer createdBy;
	private Date start;
	private Date End;
	private Date createdAt;

	public Booking() {
		super();
	}

	public Booking(Integer roomId, Integer computerId, Date start, Date end, Date createdAt) {
		super();
		this.roomId = roomId;
		this.computerId = computerId;
    	this.start = start;
    	End = end;
    	this.createdAt = createdAt;
	}
	
	public Booking(Integer roomId, Integer computerId, Integer createdBy, Date start, Date end, Date createdAt) {
		super();
		this.roomId = roomId;
		this.computerId = computerId;
		this.createdBy = createdBy;
    	this.start = start;
    	End = end;
    	this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getComputerId() {
		return computerId;
	}

	public void setComputerId(Integer computerId) {
		this.computerId = computerId;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return End;
	}

	public void setEnd(Date end) {
		End = end;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	
}
