package com.dwenc.cmas.baronet.sgn.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dwenc.cmas.baronet.constant.BaronetConstants;

@Service
public class BaronetSignService {
	/**
	 * log 처리를 위한 변수 선언
	 */
	private static final Logger logger = LoggerFactory.getLogger(BaronetSignService.class);

	@Autowired
    @Qualifier("appProperties")
    private Properties appProperties;

	/**
	 * <pre>
	 * Gateway를 통해 NOTES 데이터를 가져온다.
	 * </pre>
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String getNotesData(HttpServletRequest request, Map<String, String> params) throws Exception
	{
		String strURL = "";
		String tempParam = "";
		String orgParam = params.get("goURL");
		StringBuilder contents = new StringBuilder();

		URL url = null;
        HttpURLConnection connection = null;

        HttpSession session = request.getSession();
        String cookie = (String) session.getAttribute(BaronetConstants.COOKIE_INFO_SESSION_KEY);

        int status = 0;

		try {
			strURL = appProperties.getProperty("dwe.baronetgw.domain").toString() + "/names.nsf?login&";

			if(cookie == null || "".equals(cookie)) {
				tempParam = "username=" + params.get("username") + "&password=" + params.get("password") + "&redirectto=" + URLEncoder.encode(orgParam);
				System.out.println("##### Call URL == " + strURL + tempParam);

				url = new URL(strURL + tempParam);
				byte[] postData = tempParam.getBytes(Charset.forName("UTF-8"));
				int dataLen = postData.length;

				connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput (true);
				connection.setInstanceFollowRedirects( false );
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				connection.setRequestProperty("charset", "utf-8");
				connection.setRequestProperty("Content-Length", Integer.toString( dataLen ));
				connection.setUseCaches(false);

				OutputStream outStream = connection.getOutputStream();
				outStream.write(postData);
				outStream.flush();
				outStream.close();

				status = connection.getResponseCode();

				cookie = connection.getHeaderField("Set-Cookie");

				session.setAttribute(BaronetConstants.COOKIE_INFO_SESSION_KEY, cookie);

				strURL = connection.getHeaderField("Location");
			}
			else {
				tempParam = params.get("goURL");
				strURL = appProperties.getProperty("dwe.baronetgw.domain").toString() + orgParam;
			}

			if(status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_MOVED_TEMP  || status == 0) {
				logger.debug("##### strURL == " + strURL);
				url = new URL(strURL);
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput (true);
				connection.setInstanceFollowRedirects( false );
				connection.setRequestMethod("GET");
				connection.setRequestProperty( "Content-Type", "application/json+sua");
				connection.setRequestProperty("charset", "utf-8");
				connection.setRequestProperty( "Cookie", cookie);
				connection.setUseCaches(false);
				connection.connect();

				status = connection.getResponseCode();

   	        	BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

   	        	String line = "";
   	        	while((line = br.readLine()) != null) {
   	        		contents.append(line);
   	        	}
			}
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			if(connection != null) connection.disconnect();
		}

		return contents.toString();
	}
}
