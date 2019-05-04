package com.example.webservice;

import in.gov.uidai.commons.metrics.ApplicationMetrics;
import in.gov.uidai.commons.metrics.Slf4jMetricsReporterBeanFactory;

import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.context.ApplicationContext;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;

//URI path "/helloworld"
@Path("/helloworld")
public class HelloWorldResource {

	public static final Log logger = LogFactory.getLog(HelloWorldResource.class);
	
	static{
		
		ConsoleReporter.forRegistry(ApplicationMetrics.getMetricsRegistry())
		.convertRatesTo(TimeUnit.SECONDS)
		.convertDurationsTo(TimeUnit.MILLISECONDS)
		.build().start(5, TimeUnit.SECONDS);;
		
		
	}
	
//	private Slf4jMetricsReporterBeanFactory beanFactory;

//	public HelloWorldResource() {
//		beanFactory = new Slf4jMetricsReporterBeanFactory();
//		beanFactory.setMetricsReportTimeInSec(5);
//	}
	
	
	@POST
	@Timed
	@Path("/hello")
	@Produces("text/plain")
	public String getClichedMessage() throws Exception {
		System.out.println("hello");
		logger.info("LOGGER :in.gov.uidai.commons.metrics.http.InstrumentedServletContainer");
//		System.out.println(beanFactory.getObject());
		return "Hello World : Reema";
	}

}