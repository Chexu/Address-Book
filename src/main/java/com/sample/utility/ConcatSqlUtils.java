package com.sample.utility;

import org.apache.commons.lang3.StringUtils;

public class ConcatSqlUtils {
	
	public static String concatSqlQuery(String query1, String query2){
		if(StringUtils.isEmpty(query1)){
			query1 = " WHERE " + query2;
		}else{
			query1 = " AND " + query2;
		}
		return query1;
	}
}
