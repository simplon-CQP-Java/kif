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
@RequestMapping
public class ComputerController {
	@Autowired
	private ComputerService computerService;

	@RequestMapping("/computer")
	public ModelAndView getComputerList(ModelMap model) {
		List<Computer> computerList = computerService.getAll();
		model.addAttribute("computerList", computerList);
		return new ModelAndView("computer", model);
	}

	@RequestMapping("/computerById")
	public ModelAndView getById(@RequestParam("id") Integer id, ModelMap model) {
		Computer computer = computerService.findById(id);
		model.addAttribute("computer", computer);
		return new ModelAndView("search-pc", model);
	}

	@RequestMapping("/addComputer")
	public ModelAndView addComputer(@RequestParam("brand") String brand, @RequestParam("model") String model,
			Integer serial) {
		Computer computer = new Computer(brand, model, serial);
		computerService.addOrUpdate(computer);
		return new ModelAndView("redirect:/computer");
	}

	@RequestMapping("/deleteComputer")
	public ModelAndView deleteComputer(@RequestParam("id") Integer id, ModelMap model) {
		computerService.delete(id);
		return new ModelAndView("redirect:/computer");
	}
}
