package co.simplon.kif.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
    		if (booking.getComputer() == null && booking.getRoom() == null) {
    			throw new Exception("room_computer_null");
    		}
    		boolean computerIsAvailable = true;
    		boolean roomIsAvailable = true;
    		if (booking.getComputer() != null) computerIsAvailable = this.computerIsAvailable(booking.getComputer().getId(), booking.getStart(), booking.getEnd());
    		if (booking.getRoom() != null) roomIsAvailable = this.roomIsAvailable(booking.getRoom().getId(), booking.getStart(), booking.getEnd());
    		User user = new User();
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		if (auth != null) {
    			Map<String, String> env = System.getenv();
    			String adminName = env.get("ADMIN_NAME");
    			if (auth.getName().equals(adminName) == false && user.getRole() != Role.ADMIN) {
    				user = userService.findOneByUsername(auth.getName());
    			}
    			if (user != null && userId != -1 && (user.getRole() == Role.ADMIN || auth.getName().equals(adminName))) {
    				user = userService.findById(userId);
    			}
    			booking.setUser(user);
    		}
    		if (user == null) {
    			throw new Exception("User name not found");
    		}
    		if (computerIsAvailable && roomIsAvailable) {
    			booking = setBookingTitle(booking);
    			booking = setBookingColor(booking);
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
	
	// Set booking color
	public Booking setBookingColor(Booking booking) {
		// Set color according to the reserved ressource
		if (booking.getRoom() != null && booking.getComputer() == null) {
			booking.setColor("#B4DF37");
		} else if (booking.getComputer() != null && booking.getRoom() == null) {
			booking.setColor("#CFBE27");
		} else {
			booking.setColor("#DB1414");
		}
		return booking;
	}

	// Set booking title
	public Booking setBookingTitle(Booking booking) {
		String title = "";
		if (booking.getRoom() != null) {
			title += booking.getRoom().getName();
		}
		if (booking.getComputer() != null) {
			title += " - " + booking.getComputer().getBrand() + " " + booking.getComputer().getModel();
		}
		title += " réservée par " + booking.getUser().getUsername();
		booking.setTitle(title);
		return booking;
	}
}
