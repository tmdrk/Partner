package com.hui.zhong.cf.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.hui.zhong.cf.job.dao.JobMapper;
import com.hui.zhong.cf.job.model.Job;
import com.hui.zhong.cf.job.service.JobService;

@Service
public class JobServiceImpl implements JobService{
	
	@Autowired
	private JobMapper jobmapper;
	
	public List<Job> find(Job job){
		return jobmapper.find(job);
	}

}
