package co.simplon.kif.core.repository;

import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import co.simplon.kif.core.model.Booking;

@Resource
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
