package co.simplon.kif.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.kif.core.model.Room;
import co.simplon.kif.core.service.RoomService;

@Controller
@RequestMapping
public class RoomController {
	@Autowired
	private RoomService roomService;

	@RequestMapping("/room")
	public ModelAndView getList(ModelMap model) {
		List<Room> roomList = roomService.getAll();
		model.addAttribute("roomList", roomList);
		return new ModelAndView("room", model);
	}

	@RequestMapping("/roomById")
	public ModelAndView getById(@RequestParam("id") Integer id, ModelMap model) {
		Room room = roomService.findById(id);
		model.addAttribute("room", room);
		return new ModelAndView("search-room", model);
	}

	@RequestMapping("/addRoom")
	public ModelAndView addRoom(@RequestParam("name") String name, @RequestParam("places") Integer places,
			String description) {
		Room room = new Room(name, places, description);
		roomService.addOrUpdate(room);
		return new ModelAndView("redirect:/room");
	}

	@RequestMapping("/deleteRoom")
	public ModelAndView deleteRoom(@RequestParam("id") Integer id, ModelMap model) {
		roomService.delete(id);
		return new ModelAndView("redirect:/room");
	}
}
