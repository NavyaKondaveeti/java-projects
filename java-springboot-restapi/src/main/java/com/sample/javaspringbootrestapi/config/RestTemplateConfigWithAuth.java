/*package com.sample.javaspringbootrestapi.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

/**
 * Configures a customized rest template bean which intercepts all request that
 * are triggered through it and adds the basic authentication
 */
/*@Configuration
public class RestTemplateConfigWithAuth {
		@Value("${api.username}")
		private String apiUserName;
		@Value("${api.password}")
		private String apiPassword;
		/**
		 * Default rest template no configuration
		 */
		/*@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
		@Bean(name = "api")
		public RestTemplate apiRestTemplate() {
			RestTemplate restTemplate = new RestTemplate();
			List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
			interceptors.add(new PcfHeaderInterceptor(apiUserName, apiPassword));
			restTemplate.setInterceptors(interceptors);
			return restTemplate;
		}	
}
class PcfHeaderInterceptor implements ClientHttpRequestInterceptor {

	private String username;
	private String password;
	public PcfHeaderInterceptor(String username, String password) {
		this.username = username;
		this.password = password;
	}
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		HttpHeaders httpHeaders = request.getHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN));
		httpHeaders.add(
				"Authorization",
				"Basic "
						+ new String(Base64Util.encodeValue(EncodeNDecode.decode(username) + ":"
								+ EncodeNDecode.decode(password))));
		return execution.execute(request, body);
	}
}
*/