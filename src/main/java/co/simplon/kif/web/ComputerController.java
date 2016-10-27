package co.simplon.kif.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import co.simplon.kif.core.model.Computer;
import co.simplon.kif.core.service.ComputerService;
import org.springframework.web.servlet.ModelAndView;

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
		model.addAttribute("computer", computer);
		return new ModelAndView("computers/search", model);
	}

	@RequestMapping("/add")
	public ModelAndView addComputer(@RequestParam("brand") String brand, @RequestParam("model") String model, Integer serial) {
		if (brand == null || model == null || serial == null) {
			return new ModelAndView("redirect:/computers");
		}
		Computer computer = new Computer(brand, model, serial);
		computerService.addOrUpdate(computer);
		return new ModelAndView("redirect:/computers");
	}

	@RequestMapping("/delete")
	public ModelAndView deleteComputer(@RequestParam("id") Integer id, ModelMap model) {
		if (id == null) {
			return new ModelAndView("redirect:/computers");
		}
		computerService.delete(id);
		return new ModelAndView("redirect:/computers");
	}
}
