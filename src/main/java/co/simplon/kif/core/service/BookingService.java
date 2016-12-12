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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Booking;
import co.simplon.kif.core.model.Computer;
import co.simplon.kif.core.model.Room;
import co.simplon.kif.core.model.User;
import co.simplon.kif.core.model.User.Role;
import co.simplon.kif.core.repository.BookingRepository;

@Service
public class BookingService extends GenericService<Booking, BookingRepository> {
    @Autowired
    public BookingRepository bookingRepository;

    @Autowired
    public UserService userService;

    public Booking addOrUpdate(Booking booking, int userId) throws UsernameNotFoundException, IOException {
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
    			throw new UsernameNotFoundException("User name not found");
    		}
    		if ((booking.getComputer() != null && this.computerIsAvailable(booking.getComputer(), booking.getStart(), booking.getEnd())) ||
    			(booking.getRoom() != null && this.roomIsAvailable(booking.getRoom(), booking.getStart(), booking.getEnd()))) {
    			return bookingRepository.save(booking);
    		}
    	}
    	return booking;
    }

    public List<Booking> userBookings(User user) {
    	return bookingRepository.userBookings(user.getId());
    }

    public boolean computerIsAvailable(Computer computer, Date start, Date end) {
		List<Integer> list = bookingRepository.findBookingComputer(computer.getId());
		boolean isAvailable = false;
		if (list == null || list.size() < 1) {
			return true;
		}
		for (int i = 0; i < list.size(); i++) {
			Booking book = bookingRepository.findOne(list.get(i));
			if ((start.before(book.getStart()) && end.before(book.getStart()))
				|| (book.getEnd().before(start) && book.getEnd().before(end))) {
				isAvailable = true;
			} else {
				return false;
			}
		}
		return isAvailable;
	}

	public boolean roomIsAvailable(Room room, Date start, Date end) {
		List<Integer> list = bookingRepository.findBookingRoom(room.getId());
		boolean isAvailable = false;

		if (list == null || list.size() < 1) {
			return true;
		}
		for (int i = 0; i < list.size(); i++) {
			Booking book = bookingRepository.findOne(list.get(i));
			if ((start.before(book.getStart()) && end.before(book.getStart()))
				|| (book.getEnd().before(start) && book.getEnd().before(end))) {
				isAvailable = true;
			} else {
				return false;
			}
		}
		return isAvailable;
	}
}
