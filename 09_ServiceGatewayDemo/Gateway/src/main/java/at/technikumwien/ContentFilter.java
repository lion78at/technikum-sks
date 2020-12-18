package at.technikumwien;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.google.common.base.Objects;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.java.Log;

@Component
@Log
public class ContentFilter extends ZuulFilter {
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 0;
	}
	
	@Override
	public Object run() throws ZuulException {
		log.info("run()");
		
		RequestContext context = RequestContext.getCurrentContext();
		
		try (
			InputStream responseDataStream = context.getResponseDataStream();
		) {
			String body = StreamUtils.copyToString(responseDataStream, StandardCharsets.UTF_8);
			String bodyFiltered = body.replaceAll("(?i)sex", "***");   // case insensitive
			
			if (!Objects.equal(body, bodyFiltered)) {
				log.info("content filtered");
			}
			
			context.setResponseDataStream(new ByteArrayInputStream(bodyFiltered.getBytes(StandardCharsets.UTF_8)));
		}
		catch (Exception e) {
			throw new ZuulException(e, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
		
		return null;
	}	
}
