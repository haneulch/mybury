package com.mybury.bucketlist.auth.config;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;

public class RereadableRequestWrapper extends HttpServletRequestWrapper {
	private final Charset encoding;
	private byte[] rawData;

	private Map<String, Object> params = new HashMap<>();

	public RereadableRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		params.putAll(request.getParameterMap());

		String characterEncoding = request.getCharacterEncoding();
		encoding = characterEncoding == null || characterEncoding.isEmpty() ? StandardCharsets.UTF_8 :
			Charset.forName(characterEncoding);

		try {
			InputStream is = request.getInputStream();
			rawData = IOUtils.toByteArray(is);

			String collect = getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			if (collect.isEmpty()) {
				return;
			}

			if (request.getContentType() != null && request.getContentType().contains(MediaType.MULTIPART_FORM_DATA.getType())) {
				return;
			}

			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(collect, Map.class);
			params.put("requestBody", map);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ServletInputStream getInputStream() {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.rawData);
		return new ServletInputStream() {
			@Override
			public int read() throws IOException { return byteArrayInputStream.read(); }

			@Override
			public void setReadListener(ReadListener listener) {}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public boolean isFinished() {
				return false;
			}
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream(), this.encoding));
	}

	@Override
	public ServletRequest getRequest() {
		return super.getRequest();
	}
}
