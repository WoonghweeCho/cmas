package com.dwenc.cmas.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * ---------------------------------------------------------------
 * 업무구분 : 공통
 * 프로그램 : StringUtil
 * 설      명 : String 처리를 위한 Utility Class
 * 작 성 자 : 홍두희
 * 작성일자 : 2012-12-05
 * 수정이력
 * ---------------------------------------------------------------
 * 수정일          이  름    사유
 * ---------------------------------------------------------------
 * 2011-12-07             최초 작성
 * ---------------------------------------------------------------
 * </pre>
 * @version 1.0
 */
public class StringUtil {

	public static final int ALIGN_NONE   = 0;
	public static final int ALIGN_LEFT   = 1;
	public static final int ALIGN_RIGHT  = 2;
	public static final int ALIGN_CENTER = 3;

	static final Pattern tagPattern=Pattern.compile("<[^>]+>");

	/**
	 * String to XML 변환
	 * @param str
	 * @return
	 */
	public static String toXml(String str) {
		if (str == null)
			return null;
		if (str.indexOf('&') > 0)
			str = str.replaceAll("&", "&#38;");
		if (str.indexOf('<') > 0)
			str = str.replaceAll("<", "&lt;");
		if (str.indexOf('>') > 0)
			str = str.replaceAll(">", "&gt;");

		return str;
	}

	/**
	 * Bytes to String 변환
	 * @param b
	 * @return
	 */
	public static String getStringFromBytes(byte[] b) {
		String r = null;
		try {
			r = new String(b, "euc-kr");
		} catch (UnsupportedEncodingException e) {
		}
		return r;
	}

	/**
	 * String to Byte 변환
	 * @param str
	 * @return
	 */
	public static byte[] getBytesFromString(String str) {
		byte[] b = null;
		try {
			b = str.getBytes("euc-kr");
		} catch (UnsupportedEncodingException ex) {
		}
		return b;
	}

	/**
	 * 사업자 번호 처리(ex 1018116731 -> 101-81-16731 )
	 * @param no
	 * @return
	 */
	public static String toCompanyNo(String no) {
		if (no == null)
			return "";
		no = no.trim();
		if (no.length() != 10)
			return no;

		return no.substring(0, 3) + "-" + no.substring(3, 5) + "-"
				+ no.substring(5);
	}

	/**
	 * 주민번호 처리(ex 123456-1234567 ->  123456-1234567)
	 * @param no
	 * @return
	 */
	public static String toBizNo(String no) {
		if (no == null)
			return "";
		no = no.trim();
		if (no.length() != 13)
			return no;

		return no.substring(0, 6) + "-" + no.substring(6);
	}

	/**
	 * str1을 str2의 pos에 삽입
	 * @param str1
	 * @param pos
	 * @param str2
	 * @return
	 */
	public static String insert(String str1, int pos, String str2) {
		if(str1 == null || str1 == "") {
			return "";
		}
		else {
			return str1.substring(0, pos) + str2 + str1.substring(pos);
		}
	}

	/**
	 * str to replacement로 변환
	 * @param str
	 * @param ch
	 * @param replacement
	 * @return
	 */
	public static String replace(String str, int ch, String replacement) {
		StringBuffer ret = new StringBuffer("");
		int p, oldp = 0;
		while ((p = str.indexOf(ch, oldp)) >= 0) {
			ret.append(str.substring(oldp, p));
			ret.append(replacement);
			oldp = p + 1;
		}
		ret.append(str.substring(oldp));
		return ret.toString();
	}

	/**
	 * old String to str로 대체
	 * @param str
	 * @param old
	 * @param replacement
	 * @return
	 */
	public static String replace(String str, String old, String replacement) {
		StringBuffer ret = new StringBuffer();
		if (str == null)
			return "";
		int p, oldp = 0;
		int oldlen = old.length();
		while ((p = str.indexOf(old, oldp)) >= 0) {
			ret.append(str.substring(oldp, p));
			ret.append(replacement);
			oldp = p + oldlen;
		}
		ret.append(str.substring(oldp));
		return ret.toString();
	}

	/**
	 * old String 대체
	 * @param strsrc
	 * @param old
	 * @param replacement
	 * @return
	 */
	public static String replaceIgnoreCase(String strsrc, String old, String replacement) {
		StringBuffer ret = new StringBuffer();
		if (strsrc == null)
			return "";
		String str = strsrc.toLowerCase();
		old = old.toLowerCase();

		int p, oldp = 0;
		int oldlen = old.length();
		while ((p = str.indexOf(old, oldp)) >= 0) {
			ret.append(strsrc.substring(oldp, p));
			ret.append(replacement);
			oldp = p + oldlen;
		}
		ret.append(strsrc.substring(oldp));
		return ret.toString();
	}

	/**
	 * ������Ʈ ���� ���ڿ� ���ڵ� ���� ( DB ��� �ѱ� ���ڵ� ���� �ذ��� ���� �޼��� )
	 *
	 * @param str
	 *            ��
	 * @return ���ڵ� �ٲ� ���ڿ�
	 */
	public static String to8859(String str) {
		String r = null;
		try {
			r = new String(str.getBytes("euc-kr"), "8859_1");
		} catch (Exception ex) {
		}
		return r;
	}

	/**
	 * URL�� ��밡���� ���ڿ��� ��ȯ
	 *
	 * @param str
	 *            ��
	 * @return ��ȯ�� ���ڿ�
	 */
	public static String urlEncode(String str) {
		String r = null;
		// try {
		try {
			r = java.net.URLEncoder.encode(str, "euc-kr");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// }
		// catch(Exception ex){}
		return r == null ? "" : r;
	}

	/**
	 * url encode�� ���ڿ��� decode
	 *
	 * @param str
	 *            ���ڿ�
	 * @return decode�ȹ��ڿ�
	 */
	public static String urlDecode(String str) {
		String r = null;
		try {
			r = java.net.URLDecoder.decode(str, "euc-kr");
		} catch (Exception ex) {
		}
		return r == null ? "" : r;
	}

	/**
	 * html ������ Ư�� ����( & < > �� )�� ȭ�鿡 ǥ�õ� �� �ֵ��� ��ȯ
	 *
	 * @param str
	 *            ��
	 * @param initV
	 *            null�ϰ�� ��ȯ�Ǵ� ���ڿ�
	 * @return ��ȯ�� ���ڿ�
	 */
	public static String htmlEncode(Object str, String initV) {

		return xmlEncode(str, initV);
	}

	public static String xmlEncode(Object str ) {
		return xmlEncode(str,"");
	}

	public static String xmlEncode(Object str, String initV) {
		if (str == null)
			return initV;
		String d = str.toString();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<d.length();i++) {
			char ch = d.charAt(i);
			switch(ch) {
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '\"':
					sb.append("&quot;");
					break;
				case '\'':
					sb.append("&#39;");
					break;
				default:
					sb.append(ch);
					break;
			}
		}
		return sb.toString();
	}

	/**
	 * html ������ Ư�� ����( & < > �� )�� ȭ�鿡 ǥ�õ� �� �ֵ��� ��ȯ
	 *
	 * @param str
	 *            ��
	 * @return ��ȯ�� ���ڿ�
	 */
	public static String htmlEncode(Object str) {
		return htmlEncode(str, "");
	}

	/**
	 * ��¥ �� yyyy-M-d ���·� ��ȯ
	 *
	 * @param d
	 *            ��¥
	 * @return ���˵� ���ڿ�
	 */
	public static String formatDateYMD(Object dt) {
		String dstr = "";

		try {
			Calendar c = Calendar.getInstance();
			Date d = (Date) dt;
			c.setTime(d);
			StringBuffer sb = new StringBuffer();
			sb.append(c.get(Calendar.YEAR));
			sb.append('-');
			sb.append(c.get(Calendar.MONTH) + 1);
			sb.append('-');
			sb.append(c.get(Calendar.DAY_OF_MONTH));
			dstr = sb.toString();
		} catch (Exception ex) {
		}
		return dstr;
	}

	/**
	 * ��¥���� ���ڿ��� Timestamp�� ��ȯ
	 *
	 * @param dstr
	 *            ��¥
	 * @return Timestamp
	 */
	public static java.util.Date parseDate( String dstr )
	  {
		    if(dstr==null)
		        return null;
		    dstr = dstr.trim();
		    int p = dstr.indexOf(' ');

		    String datePart = null;
		    String timePart = null;

		    if(p>0) {
		    	datePart = dstr.substring(0,p);
		    	timePart = dstr.substring(p+1);
		    }
		    else {
		    	datePart = dstr;
		    }

		    String[] ds = null;

		    if(datePart.indexOf('-')>0)
		    	ds = StringUtil.split(datePart,'-');
		    else if(datePart.indexOf('.')>0)
		    	ds = StringUtil.split(datePart,'.');
		    else if(datePart.indexOf('.')>0)
		    	ds = StringUtil.split(datePart,'/');
		    else
		    	return toDateFromYYYYMMDD(datePart);

		    int y = 0, M = 0, d = 0, H=0,m=0,s=0;

		    Calendar c = Calendar.getInstance();
		    try {
		    	y = Integer.parseInt(ds[0]);
		    	M = Integer.parseInt(ds[1]);
		    	d = Integer.parseInt(ds[2]);
		    }
		    catch(Exception ex){
		    	return null;
		    } // ��¥�� �߸� �Է½� null

		    if(timePart!=null) {
		    	int dp = timePart.indexOf('.');
		    	if(dp>0)
		    		timePart=timePart.substring(0,dp);// �и��ʴ� ����

		    	String[] ts = StringUtil.split(timePart,':');
		    	try {
		    		H = Integer.parseInt(ts[0]);
		    		m = Integer.parseInt(ts[1]);
		    		if(ts.length>2) // �ʴ� ���
		    			s = Integer.parseInt(ts[2]);

		    	}
		    	catch(Exception ex) {
		    		H=m=s=0;
		    	} // �ð��� �߸��Է½� 0�� 0�� 0��
		    }

		    try {
		      c.set( y, M-1, d, H,m,s );
		    }
		    catch( Exception ex) {return null; }
		    return new java.util.Date(c.getTimeInMillis());
	  }

	/**
	 * YYYYMMDD���� ���ڿ��� Date ������ ��ȯ
	 *
	 * @param yyyyMMdd
	 *            ��¥���ڿ�
	 * @return Timestamp
	 */
	public static java.util.Date toDateFromYYYYMMDD(String yyyyMMdd) {
		java.util.Date d = null;

		if (yyyyMMdd == null)
			return null;
		int l = yyyyMMdd.length();

		if (l >= 8) {
			Calendar c = Calendar.getInstance();
			try {
				int nYmd = Integer.parseInt(yyyyMMdd.substring(0, 8));
				d = new java.util.Date(0);
				c.set(nYmd / 10000, (nYmd / 100) % 100 - 1, nYmd % 100);
			} catch (Exception ex) {
				c = null;
			}
			if (c != null) {
				try {
					String hhmmdd = yyyyMMdd.substring(8);
					c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hhmmdd
							.substring(0, 2)));
					c.set(Calendar.MINUTE, Integer.parseInt(hhmmdd.substring(2,
							4)));
					c.set(Calendar.SECOND, Integer.parseInt(hhmmdd.substring(4,
							6)));
				} catch (Exception ex) {
				}
				c.set(Calendar.MILLISECOND, 0);
				d = new java.util.Date(c.getTimeInMillis());
			}
		}
		return d;
	}

	/**
	 * ����ð�(Timestamp)
	 *
	 * @return ����ð�
	 */
	public static java.util.Date getCurrentDate() {
		return new java.util.Date();
	}

	/**
	 * ���糯¥(Timestamp). 0�� 0�� 0��
	 *
	 * @return ���糯¥
	 */
	public static java.util.Date getCurrentDateOnly() {
		Calendar c = Calendar.getInstance();

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return new java.util.Date(c.getTimeInMillis());
	}

	/**
	 * ���ڿ��� ���� �ڵ� �� &lt;br&gt; �ڵ�� �ٲ㼭 ������󿡼� ��ٲ��� �ǵ��� ��ȯ
	 *
	 * @param str
	 *            ��
	 * @return ��ȯ�� ���ڿ�
	 */
	public static String nl2br(String str) {
		return replace(str, '\n', "<br>\n");
	}


	/**
	 * ���������� �Ǿ��ִ� ip ���� ���� �� ip ���ڿ��� ��ȯ
	 *
	 * @param ip
	 *            IP(����)
	 * @return IP(���ڿ�)
	 */
	public static String int2ip(int ip) {
		return ((ip >> 24) & 0xff) + "." + ((ip >> 16) & 0xff) + "."
				+ ((ip >> 8) & 0xff) + "." + ((ip) & 0xff);
	}

	/**
	 * ���ڿ��� Ư�� ���� �������� split
	 *
	 * @param str
	 *            ��
	 * @param ch
	 *            ������
	 * @return split�� ���ڿ� �迭
	 */
	public static String[] split(String str, int ch) {
		int p;
		int lp = 0;
		// String s;
		ArrayList al = new ArrayList();
		while (true) {
			p = str.indexOf(ch, lp);
			if (p < 0) {
				al.add(str.substring(lp));
				break;
			}
			al.add(str.substring(lp, p));
			lp = p + 1;
		}

		return toStringArray(al);
	}

	public static String[] split(String str, String gb) {
		int p;
		int lp = 0;
		// String s;
		ArrayList al = new ArrayList();
		int gbl = gb.length();
		while (true) {
			p = str.indexOf(gb, lp);
			if (p < 0) {
				al.add(str.substring(lp));
				break;
			}
			al.add(str.substring(lp, p));
			lp = p + gbl;
		}

		return toStringArray(al);
	}

	/**
	 * ���ڿ� ip �� ������ ��ȯ
	 *
	 * @param ip
	 *            IP(���ڿ�)
	 * @return IP(����)
	 */
	public static int ip2int(String ip) {
		int ret;
		try {
			String[] s = split(ip, '.');
			ret = (Integer.parseInt(s[0]) << 24)
					| (Integer.parseInt(s[1]) << 16)
					| (Integer.parseInt(s[2]) << 8) | (Integer.parseInt(s[3]));
		} catch (Exception ex) {
			ret = 0;
		}
		return ret;
	}

	/**
	 * ���ڿ��� ������ ��ȯ
	 *
	 * @param str
	 *            ���ڿ�
	 * @param no
	 *            ���н� ��ȯ��
	 * @return ��
	 */
	public static int toInt(Object str, int no) {
		if(str==null)
			return no;
		try {
			no = Integer.parseInt((String) str, 10);
		} catch (Exception ex) {
		}
		return no;
	}

	/**
	 * ���ڿ��� ������ ��ȯ. ���н� -1 ��ȯ
	 *
	 * @param str
	 *            ���ڿ�
	 * @return ��
	 */
	public static int toInt(Object str) {
		return toInt(str, -1);
	}

	/**
	 * ���ڿ��� long ������ ��ȯ
	 *
	 * @param str
	 *            ���ڿ�
	 * @param no
	 *            ���н� ��ȯ��
	 * @return ��
	 */
	public static long toLong(String str, long no) {
		try {
			no = Long.parseLong(str);
		} catch (Exception ex) {
		}
		return no;
	}

	/**
	 * ���ڿ��� long ������ ��ȯ, ���н� -1 ��ȯ
	 *
	 * @param str
	 *            ���ڿ�
	 * @return ��
	 */
	public static long toLong(String str) {
		return toLong(str, -1);
	}

	/**
	 * ��null �� �ƴϸ� ��, ���� ��� �ٸ� ���ڿ� ��ȯ
	 *
	 * @param str
	 *            ��
	 * @param nl
	 *            null�ΰ�� ��ȯ�� ���ڿ�
	 * @return ��ȯ��
	 */
	public static String nvl(Object str, String nl) {
		return str == null ? nl : str.toString().trim();
	}

	/**
	 * ��null �� �ƴϸ� ��, ���� ��� �ٸ� ���ڿ� ��ȯ
	 *
	 * @param str
	 *            ��
	 * @param nl
	 *            null�ΰ�� ��ȯ�� ���ڿ�
	 * @param n2
	 *            null�� �ƴ� ��� �� ���ڿ��� ���ļ� ��Ÿ�� ���ڿ�
	 * @return ��ȯ��
	 */
	public static String nvl(Object str, String nl, String n2) {
		return str == null ? nl : str.toString().trim() + n2;
	}

	/**
	 * ��null �� �ƴϸ� ��, ���� ��� ��鹮�ڿ�("") ��ȯ
	 *
	 * @param str
	 *            ��
	 * @return ��ȯ��
	 */
	public static String nvl(Object str) {
		return nvl(str, "");
	}



	/**
	 * SQL�� ������ ���ڿ��� ��ȯ. ����̸� null
	 *
	 * @param str
	 *            ���ڿ�
	 * @return SQL�� ������ ���ڿ�
	 */
	public static String dbEncodeNull(Object ostr) {
		String str = dbEncode(ostr);
		return (str == null || str.length() == 0) ? null : str;
	}

	/**
	 * trim �� ����̸� null�� �ٲ�
	 *
	 * @param str
	 *            ���ڿ�
	 * @return trim�� ���ڿ�
	 */
	public static String trimToNull(String str) {
		if (str == null)
			return null;
		str = str.trim();
		return (str.length() == 0) ? null : str;
	}

	/**
	 * SQL�� ������ ���ڿ��� ��ȯ
	 *
	 * @param str
	 *            ���ڿ�
	 * @return SQL�� ������ ���ڿ�
	 */
	public static String dbEncode(Object str) {
		if (str == null)
			return null;
		return replace(str.toString().trim(), '\'', "\'\'");
	}

	/**
	 * �������� �� ������ �ΰ��� �ٲ�
	 *
	 * @param s
	 *            ��
	 * @return ��ȯ�� ���ڿ�
	 */
	public static String escape(String s) {
		return replace(s, "\\", "\\\\");
	}

	protected static String readNumber1000(String ns) {
		String[] jari = { "", "��", "��", "õ" };
		String[] su = { "��", "��", "��", "��", "��", "��", "ĥ", "��", "��" };
		String n = ns.toString();
		String ss = "";
		int nl = n.length();
		for (int i = 0; i < nl; i++) {
			int c = n.charAt(nl - i - 1) - '0';
			if (c > 0)
				ss = su[c - 1] + jari[i] + ss;
		}
		return ss;
	}

	/**
	 * ���ڸ� �ѱ� ���ڷ� �ٲ�( ex 1002 -> õ�� )
	 *
	 * @param n
	 *            ����
	 * @return �ѱۼ���
	 */
	public static String getHanNumber(long n) {
		return getHanNumber(new Long(n).toString());
	}

	public static String getHanNumber(BigDecimal n) {
		if(n==null) return "";
		return getHanNumber(n.toString());
	}


	/**
	 * ���ڸ� �ѱ� ���ڷ� �ٲ�( ex 1002 -> õ�� )
	 *
	 * @param n
	 *            ����(String��)
	 * @return �ѱۼ���
	 */
	public static String getHanNumber(String ns) {
		if(ns==null) return "";
		String n = StringUtil.replace(ns, ',', "");
		int dp = n.indexOf('.');

		if (dp >= 0)
			n = n.substring(0, dp);

		int l = n.length();
		String s = "";
		String[] jari = { "", "��", "��", "��", "��", "��" };
		int i = 0;
		String ss;
		while (l > 4) {
			ss = readNumber1000(n.substring(l - 4));
			if (ss != "")
				s = ss + jari[i] + s;
			l -= 4;
			n = n.substring(0, l);
			i++;
		}
		ss = readNumber1000(n);
		if (ss != "")
			s = ss + jari[i] + s;
		if (s == "")
			s = "��";
		return s;
	}

	/**
	 * �ֹε�Ϲ�ȣ�� - ��ȣ �ֱ�
	 *
	 * @param no
	 *            String
	 * @return String
	 */
	public static String toRRN(String no) {
		if (no == null)
			return "";
		no = no.trim();
		return (no.length() == 13) ? no.substring(0, 6) + "-" + no.substring(6)
				: (no.equals("0") ? "" : no);
	}

	static char getHexB(int no) {
		return (char) (no >= 10 ? no - 10 + 'a' : '0' + no);
	}

	static void appendHex(StringBuffer b, byte n) {
		b.append(getHexB((n & 0xf0) >> 4));
		b.append(getHexB((n & 0x0f)));
	}

	/**
	 * ����Ʈ �迭�� hex ���ڿ��� �ٲ�
	 *
	 * @param b
	 *            ����Ʈ�迭
	 * @return hex ���ڿ�
	 */
	public static String toHexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			appendHex(sb, b[i]);
		}
		return sb.toString();
	}

	public static String toHexString( long v ) {
		return toHexString( new byte[] {
				(byte)((v>>24) & 0xff),
				(byte)((v>>16) & 0xff),
				(byte)((v>>8 ) & 0xff),
				(byte)((v    ) & 0xff)
		});
	}

	/**
	 * ����Ʈ �迭�� base64 ���ڿ��� �ٲ�
	 *
	 * @param b
	 *            ����Ʈ�迭
	 * @return hex ���ڿ�
	 */
	public static String toBase64String(byte[] b) {
		return Base64Encoder.encode(b);
	}


	/**
	 * ���ھտ� 0 ���̱� ex) StringUtil.padZero( 12345, 10 ) = 0000012345
	 *
	 * @param no
	 *            ����
	 * @param co
	 *            ��ü ����
	 * @return �տ� 0 �� ���� ���ڿ�
	 */
	public static String padZero(int no, int co) {
		String nos = "000000000000000000000000000000000000" + no;
		return nos.substring(nos.length() - co);
	}

	/**
	 * �־��� ȸ����ŭ ���ڿ��� �տ� �߰��� ���ڿ��� �����δ�.
	 * @param str
	 * 				���ڿ�
	 * @param inc
	 * 				�߰��� ���ڿ�
	 * @param count
	 * 				�߰��� ���ڿ��� �ݺ��� ī��Ʈ
	 * @return
	 */
	public static String padString(String str, String inc, int count){
		int i = 0;
		while(i < count){
			str = inc + str;
			i++;
		}

		return str;
	}

	  public static String padZero( String no, int co )
	  {
		    String nos = "000000000000000000000000000000000000"+no;
		    return nos.substring(nos.length()-co);
	  }

	/**
	 * ���ڿ� �迭�� ���ļ� �� ���ڿ��� ����. �����ڴ� ch
	 *
	 * @param srcs
	 *            ���ڿ��迭
	 * @param ch
	 *            ������
	 * @return �������ڿ�
	 */
	public static String join(String[] srcs, char ch) {
		if (srcs == null)
			return "";
		StringBuffer sb = new StringBuffer(srcs[0]);
		for (int i = 1; i < srcs.length; i++) {
			sb.append(ch);
			sb.append(srcs[i]);
		}
		return sb.toString();
	}

	/**
	 * Object �迭�� ���ļ� �ϳ��� ���ڿ��� ����. �����ڴ� ch
	 *
	 * @param srcs
	 *            ���ڿ��迭
	 * @param ch
	 *            ������
	 * @return �������ڿ�
	 */
	public static String join(Object[] srcs, char ch) {
		if (srcs == null)
			return "";
		StringBuffer sb = new StringBuffer(srcs[0].toString());
		for (int i = 1; i < srcs.length; i++) {
			sb.append(ch);
			sb.append(srcs[i].toString());
		}
		return sb.toString();
	}

	/**
	 * ���ڿ� �迭�� ���ļ� �� ���ڿ��� ����
	 *
	 * @param srcs
	 *            ���ڿ��迭
	 * @param s
	 *            ������
	 * @return �������ڿ�
	 */
	public static String join(String[] srcs, String s) {
		if (srcs == null)
			return "";
		StringBuffer sb = new StringBuffer(srcs[0]);
		for (int i = 1; i < srcs.length; i++) {
			sb.append(s);
			sb.append(srcs[i]);
		}
		return sb.toString();
	}

	/**
	 * Object �迭�� ���ļ� �� ���ڿ��� ����
	 *
	 * @param srcs
	 *            Object�迭
	 * @param s
	 *            ������
	 * @return �������ڿ�
	 */
	public static String join(Object[] srcs, String s) {
		if (srcs == null)
			return "";
		StringBuffer sb = new StringBuffer(srcs[0].toString());
		for (int i = 1; i < srcs.length; i++) {
			sb.append(s);
			sb.append(srcs[i].toString());
		}
		return sb.toString();
	}

	/**
	 * yyyy-MM-dd ����� ���ڿ��� YYYYMMDD ���·� ��ȯ
	 *
	 * @param d
	 *            yyyy-MM-dd ����� ���ڿ�
	 * @return yyyyMMdd ������ ���ڿ�
	 */
	public static String toYYYYMMDDFromYMD(String d) {
		String r = "";
		if (d == null)
			d = "";
		String[] s = d.split("-");
		if (s.length == 3) {
			try {
				r = String
						.valueOf(Integer.parseInt(s[0], 10) * 10000
								+ Integer.parseInt(s[1]) * 100
								+ Integer.parseInt(s[2]));
			} catch (Exception ex) {
			}
		}
		return r;
	}

	/**
	 * yyyyMMdd ������ ���ڿ��� yyyy-MM-dd ���·� ��ȯ
	 *
	 * @param d
	 *            yyyyMMdd ���ڿ�
	 * @return yyyy-MM-dd ���ڿ�
	 */
	public static String toDateYMDFromYYYYMMDD(String d) {
		if (d == null)
			return "";
		d = d.trim();
		if (d.equals(""))
			return "";
		String ds = d + "        ";
		return (ds.substring(0, 4) + "-" + ds.substring(4, 6) + "-" + ds
				.substring(6)).trim();
	}

	/**
	 * ���ڷε� ���ڿ��� BigDecimal ������� �ٲ�. �޸�(,) �� ������
	 *
	 * @param n
	 *            ���ڷε� ���ڿ�
	 * @return BigDecimal��
	 */
	public static BigDecimal parseBigDecimal(String n, int scale ) {
		BigDecimal r = null;
		if(n==null)return null;
		n = StringUtil.replace(n, ',', "");

		try {
			r = new BigDecimal(n);
			int p = n.indexOf('.');

			if(p>=0) {
				int pscale = n.length()-p;
				r.setScale(pscale, BigDecimal.ROUND_HALF_UP);
			}
			else
				r.setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Exception ex) {
			r = null;
		}

		if(scale>=0)
			r = r.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return r;
	}

	public static BigDecimal parseBigDecimal(String n ) {
		return parseBigDecimal(n,-1);
	}

	/**
	 * ���ڷε� ���ڿ��� long ���� �ٲ�. �޸�(,)�� ������
	 *
	 * @param n
	 *            ���ڷε� ���ڿ�
	 * @param initv
	 *            ��ȯ���н� ��
	 * @return long��
	 */
	public static long parseLong(String n, long initv) {
		long no;
		try {
			no = Long.parseLong(StringUtil.replace(n, ',', ""));
		} catch (Exception ex) {
			no = initv;
		}
		return no;
	}

	/**
	 * ���ڷε� ���ڿ��� long ���� �ٲ�. �޸�(,)�� ������
	 *
	 * @param n
	 *            ���ڷε� ���ڿ�
	 * @return long��
	 */
	public static long parseLong(String n) {
		return parseLong(n, 0);
	}

	/**
	 * Collection �� ��� ���� ��Ʈ���迭�� ��ȯ
	 *
	 * @param cl
	 *            Collection
	 * @return String �迭
	 */
	public static String[] toStringArray(java.util.Collection cl) {
		Object[] al = cl.toArray();
		int sz = al.length;
		String[] r = new String[sz];
		for (int i = 0; i < sz; i++)
			r[i] = (String) al[i];
		return r;
	}

	private static SimpleDateFormat defaultDateFormat = null;

	private static DecimalFormat defaultDecimalFormat = null;

	static {
		defaultDateFormat = new SimpleDateFormat("yyyy.MM.dd");
		defaultDecimalFormat = new DecimalFormat("#,##0.#####");
	}

	/**
	 * ��¥�� �⺻ ����(yyyy-MM-dd) ���� ���ڿ��� ��ȯ
	 *
	 * @param d
	 *            ��¥
	 * @return ���ڿ�
	 */
	public static String format(Date d) {
		if (d == null)
			return "";
		return defaultDateFormat.format(d);
	}

	/**
	 * ���ڸ� �⺻ ����(#,##0.#####) ���� ���ڿ��� ��ȯ
	 *
	 * @param d
	 *            ����
	 * @return ���ڿ�
	 */
	public static String format(BigDecimal d) {
		if (d == null)
			return "";
		return defaultDecimalFormat.format(d);
	}

	/**
	 * ���ڸ� �⺻ ����(#,##0.#####) ���� ���ڿ��� ��ȯ
	 *
	 * @param d
	 *            ����
	 * @return ���ڿ�
	 */
	public static String format(double d) {
		return defaultDecimalFormat.format(d);
	}

	/**
	 * ���ڸ� �⺻ ����(#,##0.#####) ���� ���ڿ��� ��ȯ
	 *
	 * @param d
	 *            ����
	 * @return ���ڿ�
	 */
	public static String format(long d) {
		return defaultDecimalFormat.format(d);
	}

	/**
	 * ��¥�� format �� �ش��ϴ� ������� ��ȯ
	 *
	 * @param format
	 *            ����(ex yyyy-MM-dd HH:mm:ss )
	 * @param d
	 *            ��¥
	 * @return ���ڿ�
	 */
	public static String format(String format, Date d) {
		if (d == null)
			return "";
		return FormatterCache.getDateFormat(format).format(d);
	}

	/**
	 * ���ڸ� format �� �ش��ϴ� ������� ��ȯ
	 *
	 * @param format
	 *            ����(ex #,##0.000 )
	 * @param d
	 *            ����
	 * @return ���ڿ�
	 */
	public static String format(String format, java.math.BigDecimal d) {
		if (d == null)
			return "";
		return FormatterCache.getDecimalFormat(format).format(d);
	}

	/**
	 * ���ڸ� format �� �ش��ϴ� ������� ��ȯ
	 *
	 * @param format
	 *            ����(ex #,##0.000 )
	 * @param d
	 *            ����
	 * @return ���ڿ�
	 */
	public static String format(String format, double d) {
		return FormatterCache.getDecimalFormat(format).format(d);
	}

	/**
	 * ���ڸ� format �� �ش��ϴ� ������� ��ȯ
	 *
	 * @param format
	 *            ����(ex #,##0.000 )
	 * @param d
	 *            ����
	 * @return ���ڿ�
	 */
	public static String format(String format, long d) {
		return FormatterCache.getDecimalFormat(format).format(d);
	}

	/**
	 * �ش� format �� ��¥ Formatter ( java.text.Format )�� ��ȯ
	 *
	 * @param format
	 *            ����
	 * @return java.text.Format
	 */
	public static java.text.Format getDateFormatter(String format) {
		return FormatterCache.getDateFormat(format);
	}

	/**
	 * �ش� format �� ���� Formatter ( java.text.Format )�� ��ȯ
	 *
	 * @param format
	 *            ����
	 * @return java.text.Format
	 */
	public static java.text.Format getDecimalFormatter(String format) {
		return FormatterCache.getDecimalFormat(format);
	}

	/**
	 * ���ڿ��� byte����( ��, ����1byte �ѱ��� 2byte )�� ���ʿ��� �߶�
	 *
	 * @param str
	 *            ���ڿ�
	 * @param len
	 *            byte����
	 * @return �߷��� ���ڿ�
	 */
	public static String leftByte(String str, int len) {
		StringBuffer sb = new StringBuffer();

		int l = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch < 0 || ch > 127)
				l++;
			l++;
			if (l > len)
				break;
			sb.append(ch);
		}
		return sb.toString();
	}

	public static String rtrim(String str) {
		int l = str.length();
		while (l > 0 && str.charAt(l - 1) == ' ')
			l--;
		if (l == 0)
			return "";
		return str.substring(0, l);
	}

	 public static String ellipsisText( int width, String text )
	  {
		  return ellipsisText( width, ALIGN_NONE, text );
	  }
	  public static String ellipsisText( int width, int align, String text )
	  {
		  return ellipsisText( width, align, text, null  );
	  }

	  public static String ellipsisText( int width, String text, String title )
	  {
		  return ellipsisText( width, ALIGN_NONE, text, title );
	  }

	  public static String ellipsisText( int width, int align, String text, String title )
	  {
		  StringBuffer sb = new StringBuffer();
		  sb.append("<span style='width:"+width+"px;");
		  switch(align) {
		  case ALIGN_LEFT: sb.append("text-align:left;");break;
		  case ALIGN_RIGHT: sb.append("text-align:right;");break;
		  case ALIGN_CENTER: sb.append("text-align:center;");break;
		  }
		  sb.append("overflow-x:hidden;text-overflow:ellipsis;'");
		  if(title!=null)
			  sb.append(" title=\""+(title)+"\"");
		  sb.append("><nobr>"+(text)+"</nobr></span>");

		  return sb.toString();
	  }


	/**
	 * InputStream ���� ���� data�� buffer�� �־�ٰ� string���� ��ȯ�Ͽ� return �Ѵ�.
	 *
	 * @param in
	 *            InputStream
	 * @return String
	 * @throws Exception
	 */
	public static String getStringFromInputStream(InputStream in) {
		StringBuffer buff = null;
		BufferedReader read = null;
		String line = "";

		try {

			// InputStream�� �ڷḦ ��� ���� StringBuffer��ü ����
			buff = new StringBuffer();
			read = new BufferedReader(new InputStreamReader(in,"EUC-KR"));

			while ((line = read.readLine()) != null) {
				buff.append(line + "\n");
			}

			// BufferedReader �� null �� �ƴϸ� BufferedReader�� close ��Ű��.
			if (read != null) {
				read.close();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

		return buff.toString();
	}



	/**
	 * �ѱ� �ʼ� ��
	 * ��, ��, ��, ��, ��, ��, ��, ��, ��, ��,
	 * ��, ��, ��, ��, ��, ��, ��, ��, ��
	 * @param ch ����
	 * @return ��
	 */
	public static int getHanFirstLetterIndex(char ch)
	{
		return ( ch - 44032 ) / ( 21 * 28 );
	}

	/**
	 * �ѱ� �߼� ��
	 * ��, ��, ��, ��, ��, ��, ��, ��, ��, ��,
	 * ��, ��, ��, ��, ��, ��, ��, ��, ��, ��,
	 * ��
	 * @param ch ����
	 * @return ��
	 */
	public static int getHanSecondLetterIndex(char ch)
	{
		return ( ch - 44032 ) % ( 21 * 28 ) / 28;
	}

	/**
	 * �ѱ� ���� ��
     * (����), ��, ��, ��, ��, ��, ��, ��, ��, ��,
     * ��, ��, ��, ��, ��, ��, ��, ��, ��, ��,
     * ��, ��, ��, ��, ��, ��, ��, ��
	 * @param ch ����
	 * @return ��
	 */
	public static int getHanLastLetterIndex(char ch)
	{
		return ( ( ch - 44032 ) % ( 21 * 28 ) ) % 28;
	}

	public static String stripTags( String html )
	{
		if(html==null)
			return null;
		StringBuffer sb = new StringBuffer();
		Matcher m = tagPattern.matcher(html);
		while(m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();


	}

	/**
     * �Է¹��� from��κ��� �Է�to����� �������ȯ�ϱ�
     *
     * @param fMonth
     * @param tMonth
     * @return ���ڿ�
     */
	public static String getCalcMonth(String fMonth, String tMonth)
	{
        int iYear = 0;
        int iMonth = 0;
        int iDay = 0;
        String rMonth = "";

        if(Integer.parseInt(fMonth) <= Integer.parseInt(tMonth)) {
            iYear = Integer.parseInt(tMonth.substring(0,4))- Integer.parseInt(fMonth.substring(0,4));
            iMonth = Integer.parseInt(tMonth.substring(4,6))- Integer.parseInt(fMonth.substring(4,6));
            iDay = Integer.parseInt(tMonth.substring(6,8))- Integer.parseInt(fMonth.substring(6,8));

            rMonth = String.valueOf((12 * iYear) + iMonth + 1);
        }

        return rMonth;

	}
}