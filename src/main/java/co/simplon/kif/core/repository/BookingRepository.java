package co.simplon.kif.core.repository;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.kif.core.model.Booking;

@Resource
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	@Query("select id from Booking where computerId=?1 and not(?2>=ends or starts>=?3)")
	public List<Booking> findBookingComputer(Integer computerId, Date start, Date end);

	@Query("select id from Booking where roomId=?1 and not(?2>=ends or starts>=?3)")
	public List<Booking> findBookingRoom(Integer roomId, Date start, Date end);
	
	 @Query("select id from Booking where user.id=?1")
	 public List<Booking> userBooking(Integer userId);
}
