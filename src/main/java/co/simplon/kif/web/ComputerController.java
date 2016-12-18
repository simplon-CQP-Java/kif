package co.simplon.kif.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.simplon.kif.core.model.Computer;
import co.simplon.kif.core.service.ComputerService;

@Controller
@RequestMapping("/computers")
public class ComputerController {
	@Autowired
	private ComputerService computerService;

	@RequestMapping
	public ModelAndView getComputerList(ModelMap model) {
		List<Computer> computerList = computerService.getAll();
		model.addAttribute("computers", computerList);
		return new ModelAndView("computers/computers", model);
	}

	@RequestMapping("/computerById")
	public ModelAndView getById(@RequestParam("id") Integer id, ModelMap model) {
		if (id == null) {
			return new ModelAndView("redirect:/computers");
		}
		Computer computer = computerService.findById(id);
		if (computer == null) {
			model.addAttribute("id", id);
		}
		model.addAttribute("computer", computer);
		return new ModelAndView("computers/computer", model);
	}

	@RequestMapping("/add")
	public ModelAndView addComputer(@RequestParam("brand") String brand, @RequestParam("model") String model) {
		if (brand == null || model == null) {
			return new ModelAndView("redirect:/computers");
		}
		Computer computer = new Computer(brand, model);
		computerService.addOrUpdate(computer);
		return new ModelAndView("redirect:/computers");
	}

	@RequestMapping("/delete")
	public ModelAndView deleteComputer(@RequestParam("id") Integer id, ModelMap model, RedirectAttributes redirectAttr) {
		if (id == null) {
			return new ModelAndView("redirect:/computers");
		}
		try {
			computerService.delete(id);
			redirectAttr.addFlashAttribute("success", "L'ordinateur à bien été supprimé.");
		} catch(Exception e) {
			redirectAttr.addFlashAttribute("error", "L'ordinateur est réservé, il ne peut pas être supprimer pour le moment.");
		}
		return new ModelAndView("redirect:/computers");
	}

	@RequestMapping("/edit")
	public ModelAndView editComputer(@RequestParam("id") Integer id, @RequestParam("brand") String brand, @RequestParam("model") String model,
			ModelMap modelMap, RedirectAttributes redirectAttr) {
		if (id == null || brand == null || model == null) {
			return new ModelAndView("redirect:/computers");
		}
		// Get computer by id and set brand & model
		Computer computer = computerService.findById(id);
		computer.setBrand(brand);
		computer.setModel(model);
		// Update computer and redirect
		try {
			computer = computerService.addOrUpdate(computer);
			redirectAttr.addFlashAttribute("success", "L'ordinateur à bien été modifié.");
		} catch(Exception e) {
			redirectAttr.addFlashAttribute("error", "Une erreur est survenue lors de la modification de l'ordinateur.");
		}
		redirectAttr.addFlashAttribute("computer", computer);
		modelMap.addAttribute("id", id);
		return new ModelAndView("redirect:/computers/computerById", modelMap);
	}
}
