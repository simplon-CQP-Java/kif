package co.simplon.kif.core.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.kif.core.model.Booking;

@Resource
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	@Query("select id from Booking where computerId=?1")
	public List<Integer> findBookingComputer(Integer computerId);

	@Query("select id from Booking where roomId=?1")
	public List<Integer> findBookingRoom(Integer roomId);

	@Query("select r from Booking r where r.user.id =?1")
	public List<Booking> userBookings(Integer userId);
}
