package co.simplon.kif.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;

import co.simplon.kif.core.model.Booking;
import co.simplon.kif.core.model.Computer;
import co.simplon.kif.core.model.Room;
import co.simplon.kif.core.model.User;
import co.simplon.kif.core.service.BookingService;
import co.simplon.kif.core.service.ComputerService;
import co.simplon.kif.core.service.RoomService;
import co.simplon.kif.core.service.UserService;

@Controller
@RequestMapping("/bookings")
public class BookingController {
	@Autowired
	public BookingService bookingService;
	@Autowired
	public RoomService roomService;
	@Autowired
	public ComputerService computerService;
	@Autowired
	public UserService userService;

	@RequestMapping
	public ModelAndView getBookingList(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return new ModelAndView("redirect:/login");
		}
		User user = userService.findOneByUsername(auth.getName());
		List<Booking> bookingList = bookingService.userBookings(user);
		List<Room> roomList = roomService.getAll();
		List<Computer> computerList = computerService.getAll();
		model.addAttribute("bookings", bookingList);
		model.addAttribute("rooms", roomList);
		model.addAttribute("computers", computerList);
		return new ModelAndView("bookings/bookings", model);
	}

	@RequestMapping("/book")
	public ModelAndView addBooking(@RequestParam(name = "roomId", defaultValue = "-1") Integer roomId,
			@RequestParam(name = "computerId", defaultValue = "-1") Integer computerId,
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date start,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date end) {
		Date createdAt = new Date();
		if (roomId == null && computerId == null)
			return new ModelAndView("redirect:/bookings");
		if (start != null && end != null) {
			Room room = roomService.findById(roomId);
			Computer computer = computerService.findById(computerId);
			Booking booking = new Booking(room, computer, start, end, createdAt);
			try {
				booking = bookingService.addOrUpdate(booking);
			} catch (UsernameNotFoundException e) {
				System.out.println("UsernameNotFoundException " + e);
				return new ModelAndView("accessDenied");
			}
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

	@RequestMapping("/getCalendarBookings")
	@ResponseBody
	public String getCalendarBookings(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Booking> bookings = bookingService.getAll();

        // Convert to JSON string (exclude fields without @Expose).
        String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(bookings);
        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return json;
	}
}
