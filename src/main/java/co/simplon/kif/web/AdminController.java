/**
 * @author iris
 *
 */

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
import co.simplon.kif.core.model.User.Role;
import co.simplon.kif.core.service.BookingService;
import co.simplon.kif.core.service.ComputerService;
import co.simplon.kif.core.service.RoomService;
import co.simplon.kif.core.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	public UserService userService;
	@Autowired
	public RoomService roomService;
	@Autowired
	public ComputerService computerService;
	@Autowired
	public BookingService bookingService;

	@RequestMapping
	public ModelAndView getList(ModelMap model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return new ModelAndView("redirect:/login");
		}else{
			User user = userService.findOneByUsername(auth.getName());
			List<User> userList = userService.getAll();
			List<Room> roomsList = roomService.getAll();
			List<Computer> computersList = computerService.getAll();
			List<Booking> bookingList = bookingService.userBookings(user);
			if (user.getRole() == Role.ADMIN) {
				bookingList = bookingService.getAll();
			}
			model.addAttribute("users",userList);
			model.addAttribute("rooms",roomsList);
			model.addAttribute("computers",computersList);
			model.addAttribute("bookings", bookingList);
			return new ModelAndView("admin/admin",model);
		}
		
	}
}


