/*
 */
package com.dwenc.cmas.common.file.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import docfbaro.common.StringUtil;
import docfbaro.common.WebContext;
import docfbaro.sua.persistence.file.policy.WildcardAnalyzer;

/**
 * <pre>
 * file download 관련 Utility Class
 * </pre>
 *
 * @DWE
 */
public class FileDownload {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(FileDownload.class);

    private static final int BUFFER_SIZE = 4096;

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    private String contentType = "application/octect-stream";

    private String encoding = "UTF-8";

    private boolean directOpen = false;

    private String allow = "*";

    private String deny = "";

    private static final String PROP_BASE = "/dwe/project<dwe>/file/download<";

    private static final String PROP_HEADER_ENCODING = ">/header-encoding";

    private static final String PROP_DIRECT_OPEN = ">/direct-open";

    private static final String PROP_ALLOW = ">/allow";

    private static final String PROP_DENY = ">/deny";


    /**
     * FileDownload 생성자
     *
     * @throws Exception
     */
    public FileDownload() throws Exception {
        this(WebContext.getRequest(), WebContext.getResponse());
    }

    /**
     * FileDownload 생성자
     *
     * @param spec com.dwenc.cmas-framework.xml에 설정되어 있는 file download spec
     * @throws Exception
     */
    public FileDownload(String spec) throws Exception {
        this(WebContext.getRequest(), WebContext.getResponse(), spec);
    }

    /**
     * FileDownload 생성자
     *
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws Exception
     */
    public FileDownload(HttpServletRequest req, HttpServletResponse res) throws Exception {
        this(req, res, "default");
    }

    /**
     * FileDownload 생성자
     *
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @param spec com.dwenc.cmas-framework.xml에 설정되어 있는 file download spec
     * @throws Exception
     */
    public FileDownload(HttpServletRequest req, HttpServletResponse res, String spec) throws Exception {
        this.initialize(spec);
        this.request = req;
        this.response = res;
    }

    /**
     * configuration initialization
     *
     * @param spec
     * @throws Exception
     */
    private void initialize(String spec) throws Exception {

//        Configuration conf = Configuration.getInstance();
//
//        if (spec == null) spec = "default";
//
//        encoding = conf.get(PROP_BASE + spec + PROP_HEADER_ENCODING);
//        directOpen = conf.getBoolean(PROP_BASE + spec + PROP_DIRECT_OPEN, false);
//        allow = conf.get(PROP_BASE + spec + PROP_ALLOW);
//        deny = conf.get(PROP_BASE + spec + PROP_DENY);
    }

    /**
     * 지정한 url로 redirect시킨다.
     *
     * @param url redirect하고자 하는 url
     * @exception Exception
     */
    public void redirectTo(String url) throws Exception {
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 설정한 파일을 stream을 통해 다운로드한다.
     *
     * @param file 다운로드하려는 File 객체
     * @exception Exception
     */
    public void streamTo(File file) throws Exception {
        streamTo(file, file.getName());
    }

    /**
     * alias를 파일명으로 설정한 파일을 stream을 통해 다운로드한다.
     *
     * @param file 다운로드하려는 File 객체
     * @param alias 파일명
     * @exception Exception
     */
    public void streamTo(File file, String alias) throws Exception {
        FileInputStream fin = null;
        FileChannel fc = null;
        BufferedOutputStream fout = null;

        try {
            // 절대 경로와 상대 경로가 일치하는지 여부 check
//            if (!file.getCanonicalPath().equals(file.getAbsolutePath())) {
//                ExceptionPitcher.throwException("FRM_FDL_008", this.getClass(), "streamTo(File file, String alias)");
//            }

            // configuration에 설정된, 확장자 allow/deny에 match 되는지 여부 check
            this.accept(file);

            // file.canRead check
            if (!file.canRead()) {
            	logger.debug("streamTo(File file, String alias)");
            }

            response.reset();

            setHeaders(alias, file.length());

            fin = new FileInputStream(file);
            fc = fin.getChannel();
            fout = new java.io.BufferedOutputStream(response.getOutputStream());

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            int length = -1;
            while ((length = fc.read(buffer)) != -1) {
                buffer.flip();
                fout.write(buffer.array(), 0, length);
                buffer.clear();
            }
        } catch (UnsupportedEncodingException uee) {
        	logger.debug(uee.getMessage());
        } catch (FileNotFoundException e) {
        	logger.debug(e.getMessage());
        } catch (IOException e) {
        	logger.debug(e.getMessage());
        } finally {
            if (fin != null){
                try {
                    fin.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (fc != null){
                try {
                    fc.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            if (fout != null){
                try {
                    fout.flush();
                    fout.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 지정된 file이 allow 및 deny에 적합한 이름형식인지를 검사한다.
     *
     * @param file 저장 정책을 적용할 file 객체
     * @throws Exception
     */
    private void accept(File file) throws Exception {

        if (("*".equals(allow) && "*".equals(deny)) || (!"*".equals(allow) && !"*".equals(deny))) {
        	logger.debug("accept(File file)");
        }

        if ("*".equals(allow)) {
            Pattern p = WildcardAnalyzer.compileWildcardPattern(deny);
            Matcher m = p.matcher(file.getName());
            if (m.matches()) {
            	logger.debug("accept(File file)");
            }
            return;

        } else if ("*".equals(deny)) {
            Pattern p = WildcardAnalyzer.compileWildcardPattern(allow);
            Matcher m = p.matcher(file.getName());
            if (!m.matches()) {
            	logger.debug("accept(File file)");
            }
            return;
        }
        logger.debug("accept(File file)");
    }

    private void setHeaders(String filename, long filelength) throws UnsupportedEncodingException {
        int fileNameLength = filename.getBytes("utf-8").length;
        // client file name이 너무 길면 에러를 발생하기 때문에 임의 처리함
        if(fileNameLength > 90) {
            String ext = filename.substring(filename.lastIndexOf("."), filename.length());
            filename = filename.substring(0, filename.lastIndexOf("."));
            fileNameLength = filename.length() / 2;
            filename = filename.substring(0, fileNameLength) + ".." + ext;
        }

        response.setContentType(getContentType(filename));
        String sUserAgent = request.getHeader("User-Agent");

        if (sUserAgent.indexOf("MSIE 5.5") != -1) {
            response.setHeader("Content-Disposition", "filename=\"" + this.encoding + "\""
                    + encodeURLEncoding(filename) + "\";");
        } else {

            boolean isFirefox = (sUserAgent.toLowerCase().indexOf("firefox") != -1) ? true : false;
            if (this.isDirectOpen()) {
                if (isFirefox) {
                    response.setHeader("Content-Disposition", "inline; filename=" + "\"=?" + this.encoding + "?Q?"
                            + encodeQuotedPrintable(filename) + "?=\";");
                } else {
                    response.setHeader("Content-Disposition", "inline; filename="
                            + encodeURLEncoding(filename).replaceAll("\\+", "_") + ";");
                }
            } else {
                if (isFirefox) {
                    response.setHeader("Content-Disposition", "attachment; filename=" + "\"=?" + this.encoding + "?Q?"
                            + encodeQuotedPrintable(filename) + "?=\";");
                } else {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + encodeURLEncoding(filename).replaceAll("\\+", " ") + "\"");
                }
            }
        }

        response.setHeader("Content-Length", StringUtil.cString(filelength));
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
    }

    private String getContentType(String filename) throws UnsupportedEncodingException {
        this.contentType += "; charset=" + this.encoding;
        if (filename.toLowerCase().lastIndexOf("gif") != -1 || filename.toLowerCase().lastIndexOf("jpg") != -1
                || filename.toLowerCase().lastIndexOf("bmp") != -1 || filename.toLowerCase().lastIndexOf("png") != -1) {
            this.contentType = "image/gif";
            this.contentType += "; charset=" + this.encoding;
        } else if (filename.toLowerCase().lastIndexOf("html") != -1) {
            this.contentType = "text/html; charset=EUC-KR";
        }
        return this.contentType;
    }

    private String encodeQuotedPrintable(String p_sStr) throws UnsupportedEncodingException {
        String sUrlEncodingStr = URLEncoder.encode(p_sStr, this.encoding);
        sUrlEncodingStr = sUrlEncodingStr.replaceAll("\\+", "_");
        sUrlEncodingStr = sUrlEncodingStr.replaceAll("%", "=");

        return sUrlEncodingStr;
    }

    private String encodeURLEncoding(String p_sStr) throws UnsupportedEncodingException {
        String filename = p_sStr;
        try {
            filename = java.net.URLEncoder.encode(filename, "utf-8");//this.encoding
            filename = filename.replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return filename;
    }

    /**
     * encoding type을 설정한다.
     *
     * @param encoding String
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * direct open 여부를 반환한다.
     *
     * @return boolean direct open 여부
     */
    public boolean isDirectOpen() {
        return directOpen;
    }

    /**
     * direct open 여부를 설정한다.
     *
     * @param directOpen boolean
     */
    public void setDirectOpen(boolean directOpen) {
        this.directOpen = directOpen;
    }



}

