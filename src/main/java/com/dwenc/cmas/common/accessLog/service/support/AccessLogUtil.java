package com.dwenc.cmas.common.accessLog.service.support;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : AccessLogUtil
 * 설    명 : 사용량 통계 지원 유틸 
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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class AccessLogUtil {

	private static final int COMPRESSION_LEVEL = 8;

	private static final int BUFFER_SIZE = 1024 * 2;


    /**
     * 지정된 폴더를 Zip 파일로 압축한다.
     * @param sourcePath - 압축 대상 디렉토리
     * @param output - 저장 zip 파일 이름
     * @throws Exception
     */
    public static void zip(String sourcePath, String output) throws Exception {

        // 압축 대상(sourcePath)이 디렉토리나 파일이 아니면 리턴한다.
        File sourceFile = new File(sourcePath);
        if (!sourceFile.isFile() && !sourceFile.isDirectory()) {
            throw new Exception("압축 대상의 파일을 찾을 수가 없습니다.");
        }

        // output 의 확장자가 zip이 아니면 리턴한다.
        if (!(StringUtils.substringAfterLast(output, ".")).equalsIgnoreCase("zip")) {
            throw new Exception("압축 후 저장 파일명의 확장자를 확인하세요");
        }

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        ZipOutputStream zos = null;

        try {

            fos = new FileOutputStream(output); // FileOutputStream
            bos = new BufferedOutputStream(fos); // BufferedStream
            zos = new ZipOutputStream(bos); // ZipOutputStream
            zos.setLevel(COMPRESSION_LEVEL); // 압축 레벨 - 최대 압축률은 9, 디폴트 8
            zipEntry(sourceFile, sourcePath, zos); // Zip 파일 생성
            zos.finish(); // ZipOutputStream finish

        } finally {
            if (zos != null) {
                zos.close();
            }
            if (bos != null) {
                bos.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 압축
     * @param sourceFile
     * @param sourcePath
     * @param zos
     * @throws Exception
     */
    private static void zipEntry(File sourceFile, String sourcePath, ZipOutputStream zos) throws Exception {
        // sourceFile 이 디렉토리인 경우 하위 파일 리스트 가져와 재귀호출
        if (sourceFile.isDirectory()) {
            if (sourceFile.getName().equalsIgnoreCase(".metadata")) { // .metadata 디렉토리 return
                return;
            }
            File[] fileArray = sourceFile.listFiles(); // sourceFile 의 하위 파일 리스트
            for (int i = 0; i < fileArray.length; i++) {
                zipEntry(fileArray[i], sourcePath, zos); // 재귀 호출
            }
        } else { // sourcehFile 이 디렉토리가 아닌 경우
            BufferedInputStream bis = null;
            try {
                String sFilePath = sourceFile.getPath();
                //String zipEntryName = sFilePath.substring(sourcePath.length() + 1, sFilePath.length());
                String zipEntryName = sFilePath.substring(sourcePath.lastIndexOf("/") + 1, sFilePath.length());

                bis = new BufferedInputStream(new FileInputStream(sourceFile));
                ZipEntry zentry = new ZipEntry(zipEntryName);
                zentry.setTime(sourceFile.lastModified());
                zos.putNextEntry(zentry);

                byte[] buffer = new byte[BUFFER_SIZE];
                int cnt = 0;
                while ((cnt = bis.read(buffer, 0, BUFFER_SIZE)) != -1) {
                    zos.write(buffer, 0, cnt);
                }
                zos.closeEntry();
            } catch(Exception e) {
            	e.printStackTrace();

            } finally {
                if (bis != null) {
                    bis.close();
                }
            }
        }
    }

    /**
     * Zip 파일의 압축을 푼다.
     *
     * @param zipFile - 압축 풀 Zip 파일
     * @param targetDir - 압축 푼 파일이 들어간 디렉토리
     * @param fileNameToLowerCase - 파일명을 소문자로 바꿀지 여부
     * @throws Exception
     */
    public static void unzip(File zipFile, File targetDir, boolean fileNameToLowerCase) throws Exception {
        FileInputStream fis = null;
        ZipInputStream zis = null;
        ZipEntry zentry = null;

        try {
            fis = new FileInputStream(zipFile); // FileInputStream
            zis = new ZipInputStream(fis); // ZipInputStream

            while ((zentry = zis.getNextEntry()) != null) {

                String fileNameToUnzip = zentry.getName();

                if (fileNameToLowerCase) { // fileName toLowerCase
                    fileNameToUnzip = fileNameToUnzip.toLowerCase();
                }

                fileNameToUnzip = fileNameToUnzip.replace("\\", "/");

                File targetFile = new File(targetDir, fileNameToUnzip);
                unzipEntry(zis, targetFile);
            }
        } catch(Exception e) {
        	e.printStackTrace();
        	throw e;
        } finally {
            if (zis != null) {
                zis.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }

    /**
     * Zip 파일의 한 개 엔트리의 압축을 푼다.
     *
     * @param zis - Zip Input Stream
     * @param filePath - 압축 풀린 파일의 경로
     * @return
     * @throws Exception
     */
    protected static File unzipEntry(ZipInputStream zis, File targetFile) throws Exception {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(targetFile);

            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = zis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
        return targetFile;
    }

    /***
     * Shell명령을 수행하여, 로그 파일명의 infix를 가져온다.
     *
     * @param instcId
     * @return
     * @throws Exception
     */
    public static String getLogFileInfix(String command) throws Exception{

    	String inFix = "";

    	try {

    		String process = ManagementFactory.getRuntimeMXBean().getName();
    		String processId = "";

    		if(!process.equals("")) {

    			String [] processInfos = process.split("@");
    			processId = processInfos[0];

    			String osName = System.getProperty("os.name");

    			// 윈도우일 경우 명령어 : tasklist /FI "PID eq &&processId" /FI "IMAGENAME eq javaw.exe"
    			if(osName.indexOf("Windows") > -1) {
    				return inFix;
    			}

    			Map<String, String> paramMap = null;
    			List<Map<String, String>> list = new ArrayList<Map<String, String>>();

    			String [] commands = command.split(",");

    			for(String s : commands) {

    				String preCommand = "";
    				String afterCommand = "";
    				String [] div = s.split(":");

    				if(div.length == 2) {
    					preCommand = div[0];

    					if(div[1].equals("$$processId"))
    						afterCommand = processId;
    					else
    						afterCommand = div[1];
    				}

    				paramMap = new HashMap<String, String>();
    				paramMap.put(preCommand, afterCommand);
    				list.add(paramMap);
    			}

    			List<String> result = executeCommand(list);

    			if(result != null && result.size() > 0) {
    				String str = result.get(0);
    				String [] strInfos = str.split(" ");
    				inFix = strInfos[strInfos.length - 1];
    				inFix += "_";
    			}

    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

    	return inFix;
    }

    /***
     * Shell 명령을 수행한다.
     *
     * @param args : 여러 명령어가 |로 수행될 경우 명령어를 key, value 쌍으로 넘긴다.
     * 				예) ps -ef | grep wasadm | grep cmas
     * @return
     * @throws Exception
     */
    public static List<String> executeCommand(List<Map<String, String>> paramList) throws Exception{

		OutputStream output = null;
		InputStream input = null;
		Process process = null;
		int count = 1;
		int size = paramList.size();

    	for(Map<String, String> paramMap : paramList) {
    		Set<String> set = paramMap.keySet();
    		Iterator<String> iterator = set.iterator();
    		iterator.hasNext();

			String key = iterator.next();
			String value = paramMap.get(key);

			process = Runtime.getRuntime().exec(new String[] { key, value });

			if(size > 1 && count > 1) {
			    output = process.getOutputStream();
			    IOUtils.copy(input, output);
			}

		    input = process.getInputStream();

		    if(output != null)
		    	output.close();

		    count++;
    	}

	    List<String> result = IOUtils.readLines(input);
	    System.out.println(result);

    	return result;
    }

    /***
     *	HTTP로 file을 download 시킨다.
     *
     * @param svcUrl
     * @param downloadPath
     * @param retryCnt
     * @return
     */
    public static File downloadFileByHttp(String svcUrl, String downloadPath, int retryCnt) {

    	File file = null;

    	try {

			URL url = new URL(svcUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setInstanceFollowRedirects(false);
			connection.connect();
			int responseCode = connection.getResponseCode();

			// 404 또는 503일 경우 retry
			if(responseCode == HttpURLConnection.HTTP_NOT_FOUND || responseCode == HttpURLConnection.HTTP_UNAVAILABLE) {
				for(int i=0; i<retryCnt; i++) {
					connection.connect();
					responseCode = connection.getResponseCode();

					if(responseCode == HttpURLConnection.HTTP_OK)
						break;
				}
			}

			if(responseCode == HttpURLConnection.HTTP_OK) {
				file = new File(downloadPath);
				FileOutputStream output = new FileOutputStream(file);

				InputStream input = connection.getInputStream();
				byte[] buffer = new byte[1024];
				int len = 0;

				while((len = input.read(buffer)) != -1) {
					output.write(buffer, 0, len);
				}

				output.close();
			}

			connection.disconnect();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

    	return file;
    }

    /***
     * 특정 URL을 호출한다.
     *
     * @param svcUrl
     * @return
     */
    public static String callUrl(String svcUrl, int retryCnt) {

    	String ret = "";
    	HttpURLConnection connection = null;

    	try {

			URL url = new URL(svcUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setInstanceFollowRedirects(false);
			connection.connect();
			int responseCode = connection.getResponseCode();

			// 404 또는 503일 경우 retry
			if(responseCode == HttpURLConnection.HTTP_NOT_FOUND || responseCode == HttpURLConnection.HTTP_UNAVAILABLE) {
				for(int i=0; i<retryCnt; i++) {
					connection.connect();
					responseCode = connection.getResponseCode();

					if(responseCode == HttpURLConnection.HTTP_OK)
						break;
				}
			}

			if(responseCode == HttpURLConnection.HTTP_OK) {

				//BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "US-ASCII"));
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
				StringBuffer sb = new StringBuffer();

				String strData = "";
				while ((strData = br.readLine()) != null) {
					sb.append(strData);
				}

				ret = sb.toString();
			}

    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
    		if(connection != null)
    			connection.disconnect();
    	}

    	return ret;
    }

    /***
     * 디렉토리 및 하위 파일을 모두 삭제시킨다.
     *
     * @param file
     */
    public static void deleteDirectory(File file) throws Exception{

		File [] files = file.listFiles();
		for(File f : files) {
			if (f.isFile()) {
				f.delete();
			} else {
				deleteDirectory(f);
			}
		}

		file.delete();
    }

    /***
     * 파일의 encoding을 변경 시켜서 파일을 copy 시킨다.
     *
     * @param source
     * @param destination
     * @throws Exception
     */
    public static void copyFileByEncoding(File source, File destination, String fromEncoding, String toEncoding) throws Exception{

		BufferedReader in  =  new BufferedReader(new InputStreamReader(new FileInputStream(source), fromEncoding));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destination), toEncoding));

		String s = "";
        while((s = in.readLine()) != null){
            out.write(s);
            out.newLine();
        }

        in.close();
        out.close();

        in = null;
        out = null;
    }

}
