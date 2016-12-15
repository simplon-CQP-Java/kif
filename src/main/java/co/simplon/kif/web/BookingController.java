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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.GsonBuilder;

import co.simplon.kif.core.model.Booking;
import co.simplon.kif.core.model.Computer;
import co.simplon.kif.core.model.Room;
import co.simplon.kif.core.model.User;
import co.simplon.kif.core.model.User.Role;
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
		// Get Bookings, Rooms, Computers, Users, currentUser
		User user = userService.findOneByUsername(auth.getName());
		List<Booking> bookingList;
		if (user == null || user.getRole() == Role.ADMIN) {
			bookingList = bookingService.getAll();
		} else {
			bookingList = bookingService.userBookings(user);
		}
		List<User> userList = userService.getAll();
		List<Room> roomList = roomService.getAll();
		List<Computer> computerList = computerService.getAll();
		// Add attribute to model
		model.addAttribute("users", userList);
		model.addAttribute("bookings", bookingList);
		model.addAttribute("rooms", roomList);
		model.addAttribute("computers", computerList);
		return new ModelAndView("bookings/bookings", model);
	}

	@RequestMapping("/book")
	public ModelAndView addBooking(@RequestParam(name = "roomId", defaultValue = "-1") Integer roomId,
			@RequestParam(name = "computerId", defaultValue = "-1") Integer computerId,
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date start,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date end,
			@RequestParam(name = "userId", defaultValue = "-1") Integer userId,
			RedirectAttributes redirectAttr) throws Exception {
		Date createdAt = new Date();
		if (roomId == null || computerId == null) {
			redirectAttr.addFlashAttribute("error", "Tous les champs sont requis.");
			return new ModelAndView("redirect:/bookings");
		}
		if (start != null && end != null) {
			Room room = roomService.findById(roomId);
			Computer computer = computerService.findById(computerId);
			Booking booking = new Booking(room, computer, start, end, createdAt);
			try {
				booking = bookingService.addOrUpdate(booking, userId);
				redirectAttr.addFlashAttribute("success", "Votre réservation à bien été enregistrée.");
			} catch (Exception e) {
				redirectAttr.addFlashAttribute("error", "Vérifier que la salle et/ou l'ordinateur sont bien disponibles.");
				return new ModelAndView("redirect:/bookings");
			}
		}
		return new ModelAndView("redirect:/bookings");
	}

	@RequestMapping("/delete")
	public ModelAndView deleteBooking(@RequestParam("id") Integer id, ModelMap model,
			RedirectAttributes redirectAttr) {
		if (id == null) {
			return new ModelAndView("redirect:/bookings");
		}
		try {
			bookingService.delete(id);
			redirectAttr.addFlashAttribute("success", "La réservation à bien été supprimée.");
		} catch(Exception e) {
			redirectAttr.addFlashAttribute("error", "Un problème est survenu lors de la suppression de la réservation.");
		}
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

	@RequestMapping("/bookingById")
	public ModelAndView getById(@RequestParam("id") Integer id, ModelMap model) {
		if (id == null) {
			return new ModelAndView("redirect:/bookings");
		}
		// Find booking by id and set to attribute on model
		Booking booking = bookingService.findById(id);
		List<User> userList = userService.getAll();
		List<Room> roomList = roomService.getAll();
		List<Computer> computerList = computerService.getAll();
		// Add attribute to model
		model.addAttribute("users", userList);
		model.addAttribute("rooms", roomList);
		model.addAttribute("computers", computerList);
		model.addAttribute("booking", booking);
		return new ModelAndView("bookings/booking", model);
	}

	@RequestMapping("/edit")
	public ModelAndView editBooking(@RequestParam("id") Integer id, ModelMap model,
			@RequestParam(name = "roomId", defaultValue = "-1") Integer roomId,
			@RequestParam(name = "computerId", defaultValue = "-1") Integer computerId,
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date start,
			@RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date end,
			@RequestParam(name = "userId", defaultValue = "-1") Integer userId,
			RedirectAttributes redirectAttr) throws Exception {
		// Check if values are not null
		if (id == null || roomId == null || computerId == null || start == null || end == null) {
			return new ModelAndView("redirect:/bookings");
		}
		// Get Room, Computer, Booking by Id
		Room room = roomService.findById(roomId);
		Computer computer = computerService.findById(computerId);
		Booking booking = bookingService.findById(id);
		// Set new title with room name and computer brand / model
		String title = "";
		if (room != null) {
			title += room.getName();
		}
		if (computer != null) {
			title += " - " + computer.getBrand() + " " + computer.getModel();
		}
		// Set all new properties to booking
		booking.setComputer(computer);
		booking.setRoom(room);
		booking.setEnd(end);
		booking.setStart(start);
		booking.setTitle(title);
		try {
			// Save booking
			booking = bookingService.addOrUpdate(booking, userId);
		} catch (UsernameNotFoundException e) {
			redirectAttr.addFlashAttribute("error", "Vérifier que la salle et/ou l'ordinateur sont bien disponibles.");
			return new ModelAndView("redirect:/bookings");
		}
		model.addAttribute("booking", booking);
		return new ModelAndView("redirect:/bookings/bookingById?id=" + id, model);
	}
	
	@RequestMapping("/history")
	public ModelAndView findAll(ModelMap model) {
		List<Booking> list = bookingService.findAll();
		model.addAttribute("history", list);
		return new ModelAndView("/history", model);
	}
}
