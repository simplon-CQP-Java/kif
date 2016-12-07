/**
 * @author iris
 *
 */

package co.simplon.kif.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.kif.core.model.User;
import co.simplon.kif.core.model.Room;
import co.simplon.kif.core.model.Computer;
import co.simplon.kif.core.model.User.Role;
import co.simplon.kif.core.service.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	public UserService userService;

	@Autowired
	public RoomService roomService;

	@Autowired
	public ComputerService computerService;

	@RequestMapping
	public ModelAndView getList(ModelMap model){
		List<User> userList = userService.getAll();
		List<Room> roomsList = roomService.getAll();
		List<Computer> computersList = computerService.getAll();
		model.addAttribute("users",userList);
		model.addAttribute("rooms",roomsList);
		model.addAttribute("computers",computersList);
		return new ModelAndView("admin/admin",model);
	}
}


