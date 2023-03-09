package com.dwenc.cmas.common.bridge.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통모듈
 * 프로그램 : CSVConverter
 * 설    명 : XLS CSV (comma separated value)
 *           데이타베이스나 표 계산 소프트웨어 데이타를 보존하는 형식의 하나이다
 * 작 성 자 : 
 * 작성일자 : 2012-02-06
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일                    이  름          사유
 * ---------------------------------------------------------------
 * 2012-02-06     
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public class CSVConverter {

    /**
     * log 처리를 위한 변수 선언
     */
    private static final Logger logger = LoggerFactory.getLogger(CSVConverter.class);

	public static void main(String[] agrs) {

	}

	private File csvFile = null;

	public CSVConverter(File csvFile) throws Exception {
		this.csvFile = csvFile;
	}

    /**
     * 헤더 정보에 맞춰 csvFile 파일을 읽어 List<Map<String, Object>> 형태로 리턴
     */	
	public List<Map<String, Object>> toMultiData(Map headers) throws Exception {
		List<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
		FileReader reader = null;

		try {
			reader = new FileReader(this.csvFile);
			BufferedReader bufReader = new BufferedReader(reader);
			String str = null;

			List arrayHeader = new ArrayList();
			Set dataKeySet = headers.keySet();
			Iterator dataIterator = dataKeySet.iterator();
			while (dataIterator.hasNext()) {
				String dataKey = dataIterator.next().toString();
				arrayHeader.add(dataKey);
			}

			boolean bHeader = false;
			if (arrayHeader.size() > 0) { // 헤더를 넘겨왔을 경우
				bHeader = true;
			}
			int row = 0;
			while ((str = bufReader.readLine()) != null) {
				Map input = new HashMap();
				String[] result = CSV.split(str);
				final int size = result.length;
				if (bHeader) {
					if (row > 0) {
						for (int inx = 0; inx < size; inx++) {
							if (inx < arrayHeader.size()) {
								input.put(arrayHeader.get(inx), result[inx]);
							}
						}
						mData.add(mData.size(), input);
					}
				} else {
					if (row == 0) { // header
						for (int inx = 0; inx < size; inx++) {
							arrayHeader.add(result[inx]);
						}
					} else {
						for (int inx = 0; inx < size; inx++) {
							if (inx < arrayHeader.size()) {
								input.put(arrayHeader.get(inx), result[inx]);
							}
						}
						mData.add(mData.size(), input);
					}
				}
				row++;
			}
		} catch (FileNotFoundException e) {
		    logger.debug(e.getMessage());
		} catch (IOException e) {
		    logger.debug(e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					// nothing
				}
			}
		}
		return mData;
	}

	public static class CSV {

		private static String csvRe = ",(?=([^\\\"]*\"[^\"]*\")*(?![^\"]*\"))";

		private static String rmTripleDoubleQuoteRe = "\"\"\"";

		public static String[] split(String inputString) {

			List list = new ArrayList();

			Pattern pattern = Pattern.compile(csvRe);
			int start = 0;

			Matcher matcher = pattern.matcher(inputString);
			while (matcher.find()) {
				String matched = inputString.substring(start, matcher.start());
				start = matcher.end();
				matched = matched.replaceAll(rmTripleDoubleQuoteRe, "");
				list.add(matched);
			}
			list.add(inputString.substring(start));
			return (String[]) list.toArray(new String[0]);
		}
	}
}
