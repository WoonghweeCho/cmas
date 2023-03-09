package com.dwenc.cmas.common.sysMng.service.support;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import docfbaro.common.Constants;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 
 * 프로그램 : CrudLog
 * 설    명 : Crudlog를 남기는 유틸
 * 작 성 자 : DWE
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
public class CrudLog extends PrintWriter {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(CrudLog.class);

    private static PrintWriter appender;

    private static CrudLog xfb;

    private static String log = Constants.workingDir;

    /**
     * <pre>
     *  CrudLog getInstance()
     * </pre>
     * @return
     */
    public static synchronized CrudLog getInstance(){
        try{
            String logFileName = log + "/logs/crud.log";
            logger.debug("crud logFileName : " + logFileName);
            if(xfb == null){
                xfb = new CrudLog(logFileName);
            }
            return xfb;
        }catch(Exception e){
            logger.error("Error on loading CrudLog Instance!!!" + e);
            try{
                throw new Exception("Error on loading CrudLog Instance", e);
            }catch(Exception e1){
            }
            return xfb;
        }
    }

    /**
     * <pre>
     * CrudLog 기본 생성자.
     * </pre>
     * @param logFileName
     */
    public CrudLog(String logFileName) {
        super(System.out, true);

        try{
            File logFile = new File(logFileName.substring(0, logFileName.lastIndexOf("/")));
            if(!logFile.exists()){
                logFile.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(logFileName, true);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            PrintStream out = new PrintStream(bos, true);
            appender = new PrintWriter(out, true);
        }catch(FileNotFoundException e){
            logger.error("Error on loading CrudLog!!!" + e);
        }
    }

    /**
     * <pre>
     * Crudlog 를 File에 Write하는 메소드
     * </pre>
     * @param strInput
     */
    public void write(String strInput){
        synchronized(lock){
            appender.print(strInput + "\n");
            appender.flush();
        }
    }

    /**
     * <pre>
     * Write a portion of an array of characters.
     * </pre>
     * @param buf - Array of characters
     * @param off - Offset from which to start writing characters
     * @param len - Number of characters to write
     */
    public void write(char buf[], int off, int len){
        if(buf == null){
            throw new NullPointerException();
        }else if((off < 0) || (off > buf.length) || (len < 0) || ((off + len) > buf.length) || ((off + len) < 0)){
            throw new IndexOutOfBoundsException();
        }else if(len == 0){
            return;
        }
        for(int i = 0; i < len; i++){
            write(buf[off + i]);
        }
    }

    /**
     * <pre>
     * Write an array of characters. This method cannot be inherited from the
     * Writer class because it must suppress I/O exceptions.
     * </pre>
     * @param buf - Array of characters to be written
     */
    public void write(char buf[]){
        write(buf, 0, buf.length);
    }

    /**
     * <pre>
     * Terminate the current line by writing the line separator string. The line
     * separator string is defined by the system property
     * <code>line.separator</code>, and is not necessarily a single newline character (<code>'\n'</code>).
     * </pre>
     */
    public void println(){}

    /**
     * <pre>
     * Print a boolean value and then terminate the line. This method behaves as
     * though it invokes <code>{@link #print(boolean)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the <code>boolean</code> value to be printed
     */
    public void println(boolean x){
        write(x ? "true" : "false");
    }

    /**
     * <pre>
     * Print a character and then terminate the line. This method behaves as
     * though it invokes <code>{@link #print(char)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     *
     * @param x - the <code>char</code> value to be printed
     */
    public void println(char x){
        write(x);
    }

    /**
     * <pre>
     * Print an integer and then terminate the line. This method behaves as
     * though it invokes <code>{@link #print(int)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     *
     * @param x - the <code>int</code> value to be printed
     */
    public void println(int x){
        write(x);
    }

    /**
     * <pre>
     * Print a long integer and then terminate the line. This method behaves as
     * though it invokes <code>{@link #print(long)}</code> and then <code>{@link #println()}</code>.
     *
     *
     * @param x - the <code>long</code> value to be printed
     */
    public void println(long x){
        write(String.valueOf(x));
    }

    /**
     * <pre>
     * Print a floating-point number and then terminate the line. This method
     * behaves as though it invokes <code>{@link #print(float)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the <code>float</code> value to be printed
     */
    public void println(float x){
        write(String.valueOf(x));
    }

    /**
     * <pre>
     * Print a double-precision floating-point number and then terminate the
     * line. This method behaves as though it invokes <code>{@link #print(double)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the <code>double</code> value to be printed
     */
    public void println(double x){
        write(String.valueOf(x));
    }

    /**
     * <pre>
     * Print an array of characters and then terminate the line. This method
     * behaves as though it invokes <code>{@link #print(char[])}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the array of <code>char</code> values to be printed
     */
    public void println(char x[]){
        write(x);
    }

    /**
     * <pre>
     * Print a String and then terminate the line. This method behaves as though
     * it invokes <code>{@link #print(String)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the <code>String</code> value to be printed
     */
    public void println(String x){
        write(x);
    }

    /**
     * <pre>
     * Print an Object and then terminate the line. This method behaves as
     * though it invokes <code>{@link #print(Object)}</code> and then <code>{@link #println()}</code>.
     * </pre>
     * @param x - the <code>Object</code> value to be printed
     */
    public void println(Object x){
        write(String.valueOf(x));
    }
}
