package co.simplon.kif.core.repository;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.kif.core.model.Booking;

@Resource
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	// Get user bookings and order by id descendant
	@Query("select r from Booking r ORDER BY r.id DESC")
	public List<Booking> getAllBookings();
	// Get user bookings and order by id descendant
	@Query("select r from Booking r where r.user.id =?1 ORDER BY r.id DESC")
	public List<Booking> userBookings(Integer userId);
	
	// Return a list of Bookings for Computer which overlap with current booking
    @Query("select r from Booking r where r.computer.id = ?1 and not(r.end<=?2 or r.start>=?3)")
    public List<Booking> getBookingsComputer(Integer computerId, Date start, Date end);

    // Return a list of Bookings for Room which overlap with current booking
    @Query("select r from Booking r where r.room.id = ?1 and not(r.end<=?2 or r.start>=?3)")
    public List<Booking> getBookingsRoom(Integer roomId, Date start, Date end);
}
