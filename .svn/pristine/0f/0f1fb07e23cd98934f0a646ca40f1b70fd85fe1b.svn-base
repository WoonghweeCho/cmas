package com.dwenc.cmas.common.sysMng.service.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.sun.corba.se.spi.orbutil.fsm.Input;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : FtpUtil
 * 설    명 : Ftp 서비스 지원 Util
 * 작 성 자 :
 * 작성일자 : 2011-12-01
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-01             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public class FtpUtil {

	private FTPClient ftpClient;

	private String host;

	private String user;

	private String password;

	private String encoding;

    /**
     * 생성자
     */
	public FtpUtil(String host, String user, String password, String encoding) {
		this.host = host;
		this.user = user;
		this.password = password;
		this.encoding = encoding;
		this.ftpClient = new FTPClient();
	}

    /**
     * FTP 서버 연결
     */
	public void connect() throws Exception{
		ftpClient.setControlEncoding(encoding);
		ftpClient.connect(host);
		ftpClient.login(user, password);
		ftpClient.enterLocalPassiveMode();
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	}

    /**
     * FTP 서버 종료
     */
	public void disconnect() throws Exception{
        if(ftpClient.isConnected())
            ftpClient.disconnect();
    }

    /**
     * 서버에서 다운로드된 파일을 String으로 리턴
     */
	public String downloadFileToString(String serverFullFileName, String clientFullFileName) throws Exception{
		StringBuffer stringBuffer = new StringBuffer();
		File file = new File(clientFullFileName);
		OutputStream stream = new FileOutputStream(file);

		connect();
		ftpClient.retrieveFile(serverFullFileName, stream);

		FileReader in = new FileReader(file);

		int ch;
		while((ch=in.read())!=-1) {
			stringBuffer.append((char)ch);
		}

		stream.close();
		in.close();
		System.out.println("File Delete Result====>"+file.delete());
		file = null;

		disconnect();

		return stringBuffer.toString();
	}
	
    public boolean mkdir(String directoryName) throws Exception
    {
		if( ftpClient.makeDirectory(directoryName) != true )
			return false;
		
		return true;
    }

	public void cd(String path) throws Exception {
		ftpClient.changeWorkingDirectory(path);
	}

    /**
     * 서버에서 다운로드된 파일을 String으로 리턴
     */
	public void uploadFileToString(String serverFullFileName, String clientFullFileName) throws Exception{
		StringBuffer stringBuffer = new StringBuffer();
		File file = new File(clientFullFileName);
		InputStream local = new FileInputStream(file);

		ftpClient.storeFile(serverFullFileName, local);

		local.close();
		file = null;
	}

    /**
     * 파일명을 패턴별로 하여 filter하여 파일 목록을 조회
     */
	public Map<String, Object> getFileInfosByPattern(String rootPath, String pattern) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();

		connect();
		ftpClient.changeWorkingDirectory(rootPath);

		Pattern p = Pattern.compile(pattern);

		FTPFile[] files = ftpClient.listFiles();

		for(FTPFile file : files) {
			String fileName = file.getName();
			Matcher m = p.matcher(fileName);

			// pattern과 일치하는 경우, 정보를 별도의 Map에 저장한다.
			if(m.find()) {

				/*
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("IS_FILE", file.isFile());
				map.put("IS_DIRECTORY", file.isDirectory());
				map.put("SIZE", file.getSize());
				map.put("LINK", file.getLink());
				map.put("GROUP", file.getGroup());
				map.put("USER", file.getUser());
				map.put("IS_SYMBOLIC_LINK", file.isSymbolicLink());
				*/

				//resultMap.put(fileName, map);
				resultMap.put(fileName, file.getSize());
			}
		}

		disconnect();

		return resultMap;
	}
}
