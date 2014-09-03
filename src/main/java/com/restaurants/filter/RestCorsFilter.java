package com.restaurants.filter;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class RestCorsFilter implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext requestCtx,
			ContainerResponseContext responseCtx) throws IOException {

		MultivaluedMap<String, String> reqHeaders = requestCtx.getHeaders();
		List<String> oh = reqHeaders.get("Origin");
		String origin = "*";
		if (oh != null && oh.size() > 0) {
			origin = oh.get(0);
		}

		responseCtx.getHeaders().add("Access-Control-Allow-Credentials", true);
		responseCtx.getHeaders().add("Access-Control-Allow-Origin", origin);
		responseCtx
				.getHeaders()
				.add("Access-Control-Allow-Headers",
						"Origin, X-Requested-With, Content-Type, Accept, Authorization");
		responseCtx.getHeaders().add("Access-Control-Allow-Methods",
				"GET, POST, PUT, DELETE, OPTIONS");
		responseCtx.getHeaders().add("Access-Control-Max-Age", 5);
	}

	private String getAsString(List<String> items) {
		StringBuilder sb = new StringBuilder();
		String separator = ", ";
		for (String item : items) {
			sb.append(item + separator);
		}
		int lastIndex = sb.lastIndexOf(separator);
		if (lastIndex > 0) {
			sb.substring(0, lastIndex);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}
