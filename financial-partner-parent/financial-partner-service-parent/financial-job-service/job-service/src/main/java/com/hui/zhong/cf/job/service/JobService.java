package com.hui.zhong.cf.job.service;

import java.util.List;

import com.hui.zhong.cf.job.model.Job;

public interface JobService {
	
	public List<Job> find(Job job);

}
