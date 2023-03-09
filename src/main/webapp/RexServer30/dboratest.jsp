<%@ page contentType="text/html; charset=euc-kr" %> 
<%@page import="java.util.Map.Entry"%><%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map.Entry"%>
<%
try {
	Class.forName("oracle.jdbc.driver.OracleDriver");

	String url      = "jdbc:oracle:thin:@172.20.2.145:1521:DBDICM"; 
    String user     = "icmd";
	String password = "icmd";
 	String querystr   = "";
 	querystr += "";

                                                                                                   
querystr += "SELECT ROWNUM AS SEQ                                                                  ";
querystr += ", AA.PJ_CD                                                                            ";
querystr += ", AA.ESTI_WK_CLS                                                                      ";
querystr += ", AA.EXEC_CLS                                                                         ";
querystr += ", AA.ESTI_CHG_SQNUM                                                                   ";
querystr += ", AA.ESTI_QTY                                                                         ";
querystr += ", AA.ESTI_UNITP                                                                       ";
querystr += ", AA.ESTI_AMT                                                                         ";
querystr += ", AA.ENTER_YM                                                                         ";
querystr += ", AA.ENTER_YM_SHORT                                                                   ";
querystr += ", (CASE WHEN MONTHS_BETWEEN(TO_DATE(AA.ENTER_YMD, 'YYYYMMDD'), TO_DATE(AA.CT_FYMD, 'YYYYMMDD')) = 0 THEN 'M'                    ";
querystr += "        ELSE 'M+' || MONTHS_BETWEEN(TO_DATE(AA.ENTER_YMD, 'YYYYMMDD'), TO_DATE(AA.CT_FYMD, 'YYYYMMDD'))                         ";
querystr += "   END                                                                                ";
querystr += "  ) AS ENTER_YM_NM                                                                    ";
querystr += ", AA.ENTER_AMT                                                                        ";
querystr += ", AA.ACCU_ESTI_AMT                                                                    ";
querystr += "FROM (                                                                                ";
querystr += "   SELECT A.PJ_CD                                                                     ";
querystr += "        , A.ESTI_WK_CLS                                                               ";
querystr += "        , A.EXEC_CLS                                                                  ";
querystr += "        , A.ESTI_CHG_SQNUM                                                            ";
querystr += "        , SUM(NVL(A.ESTI_QTY,0)) AS ESTI_QTY                                          ";
querystr += "        , SUM(NVL(A.ESTI_UNITP,0)) AS ESTI_UNITP                                      ";
querystr += "        , SUM(NVL(A.ESTI_AMT,0)) AS ESTI_AMT                                          ";
querystr += "        , A.ENTER_YM                                                                  ";
querystr += "        , (A.ENTER_YM || '01') AS ENTER_YMD                                           ";
querystr += "        , (SUBSTR(C.CT_FYMD, 1, 6) || '01') AS CT_FYMD                                ";
querystr += "        , SUBSTR(C.CT_FYMD, 1, 6) AS CT_FYM                                           ";
querystr += "        , SUBSTR(A.ENTER_YM, 3, 4) AS ENTER_YM_SHORT                                  ";
querystr += "        , SUM(NVL(A.ENTER_AMT,0)) AS ENTER_AMT                                        ";
querystr += "        , (SELECT SUM(NVL(B.ENTER_AMT,0))                                             ";
querystr += "             FROM EMD_ENTER_PLAN_AMT B                                                ";
querystr += "            WHERE A.PJ_CD = B.PJ_CD                                                   ";
querystr += "              AND A.ESTI_WK_CLS = B.ESTI_WK_CLS                                       ";
querystr += "              AND A.EXEC_CLS = B.EXEC_CLS                                             ";
querystr += "              AND A.ESTI_CHG_SQNUM = B.ESTI_CHG_SQNUM                                 ";
querystr += "              AND A.ENTER_YM >= B.ENTER_YM                                            ";
querystr += "          ) AS ACCU_ESTI_AMT                                                          ";
querystr += "     FROM EMD_ENTER_PLAN_AMT A                                                        ";
querystr += "INNER JOIN EMB_PJ_ESTI_CRIT C                                                         ";
querystr += "       ON A.PJ_CD = C.PJ_CD                                                           ";
querystr += "    WHERE A.PJ_CD = 'CO12008'                                                         ";
querystr += "      AND A.ESTI_WK_CLS = 'EM012010'                                                  ";
querystr += "      AND A.EXEC_CLS = 'EM008030'                                                     ";
querystr += "      AND A.ESTI_CHG_SQNUM = '1'                                                      ";
querystr += " GROUP BY A.PJ_CD                                                                     ";
querystr += "        , A.ESTI_WK_CLS                                                               ";
querystr += "        , A.EXEC_CLS                                                                  ";
querystr += "        , A.ESTI_CHG_SQNUM                                                            ";
querystr += "        , A.ENTER_YM                                                                  ";
querystr += "        , C.CT_FYMD                                                                   ";
querystr += " ORDER BY A.ENTER_YM                                                                  ";
querystr += " ) AA                                                                                 ";
 	

  	Connection con = DriverManager.getConnection(url, user, password);
  	out.println("conn :: " + con);
  	
  	Statement stmt = con.createStatement();
  	ResultSet rset = stmt.executeQuery(querystr);
  	ResultSetMetaData rsmd = rset.getMetaData();
  	 int nColumn = rsmd.getColumnCount();
  	 
	while (rset.next())
  	{
		Map<String, Object> rowData = new HashMap<String, Object>();
		for(int i = 1 ; i < nColumn; i++){
			String key = rsmd.getColumnName(i);
			//System.out.println(i +" === " + key +" === " +rsmd.getColumnType(i));
			rowData.put(key, rset.getObject(i)) ;
		}
   		out.println("<br>" + rset.getString(1));
  	}   	


}catch(Exception e){
	out.println("Exception : " + e.getMessage());
	
}finally {
	out.println("finally.......");
}
%>
