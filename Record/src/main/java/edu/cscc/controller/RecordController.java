package edu.cscc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import edu.cscc.model.Record;
import edu.cscc.repository.RecordRepository;


@Controller
public class RecordController {
	@Autowired
	private RecordRepository repository;

	@GetMapping("/add-record")
	public String getRecordForm(Model model) {
		model.addAttribute("request", new Record());

		return "add-record";
	}

	@PostMapping("/records")
	public String postRecord(Record record) {
		var createdRecord = repository.save(record);
		return "redirect:/records/" + createdRecord.id;
	}


	@GetMapping("/records")
	public String getRecords(Model model) {
		var records = repository.findAll();

		model.addAttribute("records", records);

		return "records";
	}

	@GetMapping("/records/{id}")
	public String getRecord(@PathVariable int id, Model model) {
		var record = repository.findById(id).get();

		model.addAttribute("title", record.title);
		model.addAttribute("artist", record.artist);
		model.addAttribute("label", record.label);
		model.addAttribute("released", record.released);
		model.addAttribute("tracks", record.tracks);
		model.addAttribute("size", record.size);
		model.addAttribute("damaged", record.damaged);


		return "record";

	}
}

