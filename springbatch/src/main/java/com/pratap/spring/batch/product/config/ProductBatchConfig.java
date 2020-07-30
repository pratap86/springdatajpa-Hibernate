package com.pratap.spring.batch.product.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.pratap.spring.batch.product.listener.MyJobLIstener;
import com.pratap.spring.batch.product.model.Product;

@Configuration
public class ProductBatchConfig {


	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.data-username}")
	private String username;
	
	@Value("${spring.datasource.data-password}")
	private String password;
	
	
	@Autowired
	private StepBuilderFactory sbf;
	
	@Autowired
	private JobBuilderFactory jbf;
	
	@Bean
	public Job productJob() {
		return jbf.get("job-1")
					.incrementer(new RunIdIncrementer())
					.listener(productListener())
					.start(productStep())
					.build();
	}
	
	@Bean
	public Step productStep() {
		
		return sbf.get("step-1")
					.<Product, Product>chunk(1)
					.reader(productReader())
					.processor(productProcessor())
					.writer(productWriter())
					.build();
	}

	@Bean
	public ItemReader<Product> productReader(){
		
		FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("products.csv"));
		
		DefaultLineMapper<Product> lineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("id", "name", "description", "price");
		
		BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Product.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		
		reader.setLineMapper(lineMapper);
		
		return reader;
	}
	
	@Bean
	public ItemProcessor<Product, Product> productProcessor(){
		
		return p -> {
			p.setPrice(p.getPrice() - p.getPrice()*10/100);
			return p;
		};
	}
	
	@Bean
	public ItemWriter<Product> productWriter(){
		
		JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
		
		writer.setDataSource(productDataSource());
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
		writer.setSql("INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE) VALUES (:id, :name, :description, :price)");
		
		return writer;
	}
	
	@Bean
	public DataSource productDataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean
	public MyJobLIstener productListener() {
		return new MyJobLIstener();
	}
}
