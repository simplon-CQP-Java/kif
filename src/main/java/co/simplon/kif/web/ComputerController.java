package co.simplon.kif.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView deleteComputer(@RequestParam("id") Integer id, ModelMap model) {
		if (id == null) {
			return new ModelAndView("redirect:/computers");
		}
		computerService.delete(id);
		return new ModelAndView("redirect:/computers");
	}

	@RequestMapping("/edit")
	public ModelAndView editComputer(@RequestParam("id") Integer id, @RequestParam("brand") String brand, @RequestParam("model") String model, ModelMap modelMap) {
		if (id == null || brand == null || model == null) {
			return new ModelAndView("redirect:/computers");
		}
		// Get computer by id and set brand & model
		Computer computer = computerService.findById(id);
		computer.setBrand(brand);
		computer.setModel(model);
		// Update computer and redirect
		computerService.addOrUpdate(computer);
		modelMap.addAttribute("computer", computer);
		return new ModelAndView("redirect:/computers/computerById?id=" + id, modelMap);
	}
}
