package co.simplon.kif.web;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.kif.core.model.Booking;
import co.simplon.kif.core.model.Computer;
import co.simplon.kif.core.model.Room;
import co.simplon.kif.core.service.BookingService;
import co.simplon.kif.core.service.ComputerService;
import co.simplon.kif.core.service.RoomService;

@Controller
@RequestMapping("/bookings")
public class BookingController {
  @Autowired
  public BookingService bookingService;
  @Autowired
  public RoomService roomService;
  @Autowired
  public ComputerService computerService;

  @RequestMapping
  public ModelAndView getBookingList(ModelMap model) {
    List<Booking> bookingList = bookingService.getAll();
    List<Room> roomList = roomService.getAll();
    List<Computer> computerList = computerService.getAll();
    model.addAttribute("bookings", bookingList);
    model.addAttribute("rooms", roomList);
    model.addAttribute("computers", computerList);
    return new ModelAndView("bookings/bookings", model);
  }

  @RequestMapping("/book")
  public ModelAndView addBooking(
		  @RequestParam("roomId") Integer roomId,
		  @RequestParam("computerId") Integer computerId,
		  @RequestParam("start") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date start,
		  @RequestParam("end") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date end) {
    Date createdAt = new Date();
    if (roomId != null && computerId != null && start != null && end != null) {
	    Booking booking = new Booking(roomId, computerId, start, end, createdAt);
	    bookingService.addOrUpdate(booking);
    }
    return new ModelAndView("redirect:/bookings");
  }

  @RequestMapping("/delete")
	public ModelAndView deleteBooking(@RequestParam("id") Integer id, ModelMap model) {
		if (id == null) {
			return new ModelAndView("redirect:/bookings");
		}
		bookingService.delete(id);
		return new ModelAndView("redirect:/bookings");
	}
}
