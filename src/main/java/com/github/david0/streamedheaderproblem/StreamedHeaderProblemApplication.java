package com.github.david0.streamedheaderproblem;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class StreamedHeaderProblemApplication {

	private static final Log logger = LogFactory.getLog(StreamedHeaderProblemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StreamedHeaderProblemApplication.class, args);
	}

	@Bean
	Filter headerWriter() {
		return new HeaderWriterFilter();
	}

	private static class HeaderWriterFilter implements Filter {

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			try {
				chain.doFilter(req, res);
			} finally {
				logger.error("writing headers as part of returning filter");
				((HttpServletResponse) response).setHeader("X-One", "one");
				((HttpServletResponse) response).setHeader("X-Two", "two");
				((HttpServletResponse) response).setHeader("X-Three", "three");
				((HttpServletResponse) response).setHeader("X-Four", "four");
				((HttpServletResponse) response).setHeader("X-Five", "five");
			}
		}


	}
}
