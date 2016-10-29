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
  private String roomId;
  private String computerId;
  private Date start;
  private Date End;
  private Date createdAt;

  public Booking() {
    super();
  }

  public Booking(String roomId, String computerId, Date start, Date end, Date createdAt) {
    super();
    this.roomId = roomId;
    this.computerId = computerId;
    this.start = start;
    End = end;
    this.createdAt = createdAt;
  }

  public Integer getId() {
    return id;
  }

  public String getroomId() {
    return roomId;
  }

  public void setroomId(String roomId) {
    this.roomId = roomId;
  }

  public String getComputerId() {
    return computerId;
  }

  public void setComputerId(String computerId) {
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
}
