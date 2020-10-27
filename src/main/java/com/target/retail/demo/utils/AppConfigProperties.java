package com.target.retail.demo.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("app")
public class AppConfigProperties {
	
	public String getProductExternalUrl() {
		return productExternalUrl;
	}

	public void setProductExternalUrl(String productExternalUrl) {
		this.productExternalUrl = productExternalUrl;
	}

	private String productExternalUrl;

}
