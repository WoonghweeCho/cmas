package com.dwenc.cmas.common.mail;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 :
 * 프로그램 : InputStreamDataSource
 * 설    명 : 이 클래스는 Email의 ecm 형태의 첨부를 위해 Datasource 를 implement 했다
 * 작 성 자 :
 * 작성일자 :
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 *
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

public class InputStreamDataSource implements DataSource {
	private String name;
 	private String contentType;
 	private ByteArrayOutputStream baos;

 	/**
 	 * Email의 ecm 형태의 첨부를 위해 Datasource 를 implement하는 생성자
 	 *
 	 * @param name
 	 * @param contentType
 	 * @param baStream
 	 * @throws IOException
 	 */
 	InputStreamDataSource(String name, String contentType, ByteArrayOutputStream baStream) throws IOException
 	{
 		this.name = name;
 		this.contentType = contentType;
 		baos = baStream;
 	}

 	public String getContentType() {
 		return contentType;
 	}

 	public InputStream getInputStream() throws IOException {
 		return new ByteArrayInputStream(baos.toByteArray());
 	}

 	public String getName() {
 		return name;
 	}

 	public OutputStream getOutputStream() throws IOException 	{
 		throw new IOException("Cannot write to this read-only resource");
 	}

}
