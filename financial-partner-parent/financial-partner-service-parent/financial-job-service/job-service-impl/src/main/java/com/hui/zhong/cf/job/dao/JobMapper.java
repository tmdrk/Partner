package com.hui.zhong.cf.job.dao;

import java.util.List;

import com.hui.zhong.cf.job.model.Job;

public interface JobMapper {
	
	public List<Job> find(Job job);

}
