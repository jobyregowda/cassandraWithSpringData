package com.target.retail.demo.utils;

import java.io.IOException;
import java.io.NotActiveException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.target.retail.demo.exception.RestTemplateException;



@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
	
	@Override
    public boolean hasError(ClientHttpResponse httpResponse) 
      throws IOException {
 
		boolean hasError = false;
        int rawStatusCode = httpResponse.getRawStatusCode();
        if (rawStatusCode != 200){
            hasError = true;
        }
        return hasError;    }
 
  

	@Override
    public void handleError(ClientHttpResponse httpResponse) 
      throws IOException {
 
		if (httpResponse.getStatusCode() == HttpStatus.MOVED_PERMANENTLY) {
	           
			throw new RestTemplateException(httpResponse.getStatusCode() , "Url trying to access moved permanently");
	        }
    }

}
