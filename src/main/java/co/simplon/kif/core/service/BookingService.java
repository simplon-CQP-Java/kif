package co.simplon.kif.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Booking;
import co.simplon.kif.core.model.Computer;
import co.simplon.kif.core.model.Room;
import co.simplon.kif.core.model.User;
import co.simplon.kif.core.repository.BookingRepository;

@Service
public class BookingService {
	
    @Autowired
    public BookingRepository bookingRepository;
    
    @Autowired
    public UserService userService;

    public List<Booking> getAll() {
      return bookingRepository.findAll();
    }

    public Booking findById(Integer id) {
      return bookingRepository.findOne(id);
    }

    public Booking addOrUpdate(Booking booking) throws UsernameNotFoundException {
    	if (booking != null) {
    		User user = new User();
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		if (booking.getUser() == null && auth != null) {
    			user = userService.findOneByUsername(auth.getName());
    			booking.setUser(user);
    		} else {
    			user = userService.findById(booking.getUser().getId());
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

    public void delete(Integer id) {
      bookingRepository.delete(id);
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
