package com.pratap.spring.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class Writer implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {

		System.out.println("inside write");
		System.out.println("writing data - :"+items);
	}

}
