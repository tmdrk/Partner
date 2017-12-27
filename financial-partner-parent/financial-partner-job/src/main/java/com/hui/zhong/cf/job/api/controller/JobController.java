package com.hui.zhong.cf.job.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hui.zhong.cf.job.model.Job;
import com.hui.zhong.cf.job.service.JobService;

@Controller
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	private JobService jobservice;
	
	@RequestMapping("/find")
	@ResponseBody
	public List<Job> find(){
		Job job = new Job();
		return jobservice.find(job);
	}

}
