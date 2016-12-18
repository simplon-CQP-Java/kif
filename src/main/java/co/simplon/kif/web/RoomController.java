package co.simplon.kif.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.simplon.kif.core.model.Room;
import co.simplon.kif.core.service.RoomService;

@Controller
@RequestMapping("/rooms")
public class RoomController {
	@Autowired
	private RoomService roomService;

	@RequestMapping
	public ModelAndView getList(ModelMap model) {
		List<Room> roomList = roomService.getAll();
		model.addAttribute("rooms", roomList);
		return new ModelAndView("rooms/rooms", model);
	}

	@RequestMapping("/roomById")
	public ModelAndView getById(@RequestParam("id") Integer id, ModelMap model) {
		if (id == null) {
			return new ModelAndView("redirect:/rooms");
		}
		Room room = roomService.findById(id);
		if (room == null) {
			model.addAttribute("id", id);
		}
		model.addAttribute("room", room);
		return new ModelAndView("rooms/room", model);
	}

	@RequestMapping("/add")
	public ModelAndView addRoom(@RequestParam("name") String name, @RequestParam("places") Integer places,
			String description) {
		if (name == null || places == null || description == null) {
			return new ModelAndView("redirect:/rooms");
		}
		Room room = new Room(name, places, description);
		roomService.addOrUpdate(room);
		return new ModelAndView("redirect:/rooms");
	}

	@RequestMapping("/delete")
	public ModelAndView deleteRoom(@RequestParam("id") Integer id, ModelMap model, RedirectAttributes redirectAttr) {
		if (id == null) {
			return new ModelAndView("redirect:/rooms");
		}
		try {
			roomService.delete(id);
			redirectAttr.addFlashAttribute("success", "La salle à bien été supprimé.");
		} catch(Exception e) {
			redirectAttr.addFlashAttribute("error", "La salle est réservée, elle ne peut pas être supprimer pour le moment.");
		}
		return new ModelAndView("redirect:/rooms");
	}

	@RequestMapping("/edit")
	public ModelAndView editComputer(@RequestParam("id") Integer id, @RequestParam("name") String name,
		@RequestParam("places") Integer places, @RequestParam("description") String description, ModelMap modelMap,
		RedirectAttributes redirectAttr) {
		if (id == null || name == null || places == null || description == null) {
			return new ModelAndView("redirect:/rooms");
		}
		// Get computer by id and set brand & model
		Room room = roomService.findById(id);
		room.setName(name);
		room.setPlaces(places);
		room.setDescription(description);
		// Update computer and redirect
		try {
			room = roomService.addOrUpdate(room);
			redirectAttr.addFlashAttribute("success", "La salle à bien été modifiée.");
		} catch(Exception e) {
			redirectAttr.addFlashAttribute("error", "Une erreur est survenue lors de la modification de la salle.");
		}
		redirectAttr.addFlashAttribute("room", room);
		modelMap.addAttribute("id", id);
		return new ModelAndView("redirect:/rooms/roomById?id=", modelMap);
	}
}
