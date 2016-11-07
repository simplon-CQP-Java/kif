package co.simplon.kif.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Booking;
import co.simplon.kif.core.repository.BookingRepository;

@Service
public class BookingService {
    @Autowired
    public BookingRepository bookingRepository;

    public List<Booking> getAll() {
      return bookingRepository.findAll();
    }

    public Booking findById(Integer id) {
      return bookingRepository.findOne(id);
    }

    public Booking addOrUpdate(Booking booking) {
    	if (booking != null) {
    		if (this.computerIsAvailable(booking.getComputerId(), booking.getStart(), booking.getEnd())
    			&& this.roomIsAvailable(booking.getRoomId(), booking.getStart(), booking.getEnd())) {
        		return bookingRepository.save(booking);
    		}
    	}
    	return booking;
    }

    public void delete(Integer id) {
      bookingRepository.delete(id);
    }
    
    public boolean computerIsAvailable(int id, Date start, Date end) {
		List<Integer> list = bookingRepository.findBookingComputer(id);
		boolean isAvailable = false;
		if (list == null) {
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

	public boolean roomIsAvailable(int id, Date start, Date end) {
		List<Integer> list = bookingRepository.findBookingRoom(id);
		boolean isAvailable = false;
			
		if (list == null) {
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
