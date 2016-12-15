package co.simplon.kif.core.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Booking;
import co.simplon.kif.core.model.User;
import co.simplon.kif.core.model.User.Role;
import co.simplon.kif.core.repository.BookingRepository;

@Service
public class BookingService extends GenericService<Booking, BookingRepository> {
    @Autowired
    public BookingRepository bookingRepository;

    @Autowired
    public UserService userService;

    public Booking addOrUpdate(Booking booking, int userId) throws Exception {
    	if (booking != null) {
    		User user = new User();
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		if (auth != null) {
    			Resource resource = new ClassPathResource("/META-INF/env.properties");
    			Properties props = PropertiesLoaderUtils.loadProperties(resource);
    			if (props == null) {
    				throw new IOException("Properties not found");
    			}
    			if (props.getProperty("admin.name").equals(auth.getName()) == false && user.getRole() != Role.ADMIN) {
    				user = userService.findOneByUsername(auth.getName());
    			}
    			if (user != null && userId != -1 && (user.getRole() == Role.ADMIN || props.getProperty("admin.name").equals(auth.getName()))) {
    				user = userService.findById(userId);
    			}
    			booking.setUser(user);
    		}
    		if (user == null) {
    			throw new Exception("User name not found");
    		}
    		if ((booking.getComputer() != null && this.computerIsAvailable(booking.getComputer().getId(), booking.getStart(), booking.getEnd())) ||
    			(booking.getRoom() != null && this.roomIsAvailable(booking.getRoom().getId(), booking.getStart(), booking.getEnd()))) {
    			return bookingRepository.save(booking);
    		} else {
    			throw new Exception("Ressource is not available");
    		}
    	}
    	return booking;
    }

    public List<Booking> userBookings(User user) {
    	return bookingRepository.userBookings(user.getId());
    }

    public List<Booking> getAllBookings() {
    	return bookingRepository.getAllBookings();
    }

    // Check if computer is available for start Date and end Date
    public boolean computerIsAvailable(Integer id, Date start, Date end) {
    	List<Booking> list = bookingRepository.getBookingsComputer(id, start, end);
		if (list.isEmpty()) {
			return true;
    	} else {
			return false;
		}
	}
    // Check if room is available for start Date and end Date
	public boolean roomIsAvailable(Integer id, Date start, Date end) {
		List<Booking> list = bookingRepository.getBookingsRoom(id, start, end);
		if (list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}
}
