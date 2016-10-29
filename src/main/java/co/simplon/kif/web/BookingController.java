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
import co.simplon.kif.core.service.BookingService;

@Controller
@RequestMapping("/bookings")
public class BookingController {
  @Autowired
  public BookingService bookingService;

  @RequestMapping
  public ModelAndView getBookingList(ModelMap model) {
    List<Booking> bookingList = bookingService.getAll();
    model.addAttribute("bookings", bookingList);
    return new ModelAndView("bookings/bookings", model);
  }

  @RequestMapping("/book")
  public ModelAndView addBooking(@RequestParam("roomId") String roomId, @RequestParam("computerId") String computerId, @RequestParam("start") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date start, @RequestParam("end") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date end) {
    Date createdAt = new Date();
    Booking booking = new Booking(roomId, computerId, start, end, createdAt);
    bookingService.addOrUpdate(booking);
    return new ModelAndView("redirect:/bookings");
  }
}
