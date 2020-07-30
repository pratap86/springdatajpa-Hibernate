package com.pratap.spring.batch.product.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MyJobLIstener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {

		System.out.println("job started "+jobExecution.getStatus().toString());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {

		System.out.println("Job Ended : "+jobExecution.getStatus().toString());
	}

}
