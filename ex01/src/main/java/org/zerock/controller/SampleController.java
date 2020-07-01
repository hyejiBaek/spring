package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/sample/*", method = {RequestMethod.GET, RequestMethod.POST})
@Log4j
public class SampleController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTOList dtolist) {
		log.info(dtolist);

		return "ex01";
	}
	
	@GetMapping("/todoo")
	public String todo(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info(dto);
		log.info(page);
		
		return "todo";
	}
	
	@GetMapping("/todo")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06....................");
		SampleDTO dto = new SampleDTO();
		dto.setAge(24);
		dto.setName("น้ม๖วý");
		
		return dto;
	}
	
	@GetMapping("/exUpload")
	public String exUpload() {
		log.info("/exUpload..........");
		
		return "exUpload";
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("--------------------");
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
		});
	}
}
