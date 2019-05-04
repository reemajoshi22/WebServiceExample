package com.example.webservice;


import in.gov.uidai.commons.metrics.ApplicationMetrics;

import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jersey.InstrumentedResourceMethodDispatchAdapter;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.WebApplication;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import com.sun.jersey.spi.container.servlet.WebConfig;

/**
 * 
 * Use this class instead of the com.sun.jersey.spi.container.servlet.ServletContainer for the metrics to be 
 * printed for the rest endpoints.
 * 
 * Also annotate the restendpoints with the @Timed and @Metered to get the running time and hit count for the endpoints.
 * 
 * @author Nitin
 *
 */
public class InstrumentedServletContainer extends ServletContainer {
	
	private static final long serialVersionUID = 1L;
	private MetricRegistry registry = ApplicationMetrics.getMetricsRegistry();
	
	
	@Override
	protected void configure(FilterConfig fc, ResourceConfig rc,
			WebApplication wa) {
		super.configure(fc, rc, wa);
		instrumentResourceConfig(rc);
	}
	
	@Override
	protected void configure(ServletConfig sc, ResourceConfig rc,
			WebApplication wa) {
		super.configure(sc, rc, wa);
		instrumentResourceConfig(rc);
	}
	
	@Override
	protected void configure(WebConfig wc, ResourceConfig rc, WebApplication wa) {
		super.configure(wc, rc, wa);
		instrumentResourceConfig(rc);
	}
	
	
	private void instrumentResourceConfig(ResourceConfig defaultResourceConfig){
		defaultResourceConfig.getSingletons().add(new InstrumentedResourceMethodDispatchAdapter(registry));
	} 
}
