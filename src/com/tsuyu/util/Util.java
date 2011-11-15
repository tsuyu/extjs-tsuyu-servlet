package com.tsuyu.util;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
public class Util {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * Generates modelMap to return in the 
	 * @param List<CatalogModel> catalogmodel
	 * @return
	 */
	public JSONObject getModelMap(List<?> list,String root){
		
		 
		Map<String,Object> modelMap = new HashMap<String,Object>(3);
		modelMap.put("total", list.size());
		modelMap.put(root, list);
		modelMap.put("success", true);
		
		JSONObject obj = JSONObject.fromObject(modelMap);
		return obj;
	}
	
	/**
	 * Generates modelMap to return in the in case
	 * of exception
	 * @param msg message
	 * @return
	 */
	public JSONObject getModelMapMsg(String msg ,boolean bool){

		Map<String,Object> modelMap = new HashMap<String,Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", bool);

		JSONObject obj = JSONObject.fromObject(modelMap);
		return obj;
	}
	
	public String replaceUpdate(String str){
		String a = str.replace("[","");
		String b = a.replace("]","");
		return b;
	}
	
	public String nullconv(String str) {
		if (str == null)
			str = "";
		else if (str.equals("null"))
			str = "";
		else if ((str.trim()).equals(""))
			str = "";
		else if (str.length()==0)
			str = "";
		else
			str = str.trim();
		return str;
	}
	
	/**
	 * Get formated date
	 * @param value
	 * @return
	 * @throws ParseException 
	 */
	private static Date getDateValue(String value) throws ParseException{
		
		return dateFormat.parse(value);
	}
	
	public static String filterBuilder(List<ExtJSFilter> filters) throws SecurityException, NoSuchFieldException, ParseException{
		StringBuilder query = new StringBuilder();
		for (ExtJSFilter filter : filters){
				if (filter.getType().equals("string")){
					query.append("AND "+filter.getTable()+"."+filter.getField()+" LIKE '%"+filter.getValue()+"%' ");
				} else if (filter.getType().equals("boolean")){ //boolean in database is 1 (true) or 0 (false)
					query.append("AND "+filter.getTable()+"."+filter.getField()+" = "+filter.getValue()+" ");
				} else if (filter.getType().equals("numeric")){
					if(filter.getComparison().equals("ne")){
						query.append("AND "+filter.getTable()+"."+filter.getField()+" != "+filter.getValue()+" ");
					} else if (filter.getComparison().equals("eq")){
						query.append("AND "+filter.getTable()+"."+filter.getField()+" = "+filter.getValue()+" ");
					} else if (filter.getComparison().equals("lt")){
						query.append("AND "+filter.getTable()+"."+filter.getField()+" < "+filter.getValue()+" ");
					} else if (filter.getComparison().equals("gt")){
						query.append("AND "+filter.getTable()+"."+filter.getField()+" > "+filter.getValue()+" ");
					}
				} else if (filter.getType().equals("date")){
					if(filter.getComparison().equals("ne")){
						query.append("AND "+filter.getTable()+"."+filter.getField()+" != "+getDateValue(filter.getValue())+" ");
					} else if (filter.getComparison().equals("eq")){
						query.append("AND "+filter.getTable()+"."+filter.getField()+" = "+filter.getValue()+" ");
					} else if (filter.getComparison().equals("lt")){
						query.append("AND "+filter.getTable()+"."+filter.getField()+" < "+getDateValue(filter.getValue())+" ");
					}else if (filter.getComparison().equals("gt")){
						query.append("AND "+filter.getTable()+"."+filter.getField()+" > "+getDateValue(filter.getValue())+" ");
					}
				} else if (filter.getType().equals("list")){ //can contain only one value (use equals) or multiple values (use IN - list of values)			
					String[] values = filter.getValue().split(",");
					if (values.length > 1){ //more than one value - use IN
						query.append("AND "+filter.getTable()+"."+filter.getField()+" IN("+values+") ");
					} else{ //just only one value - can use equals
						query.append("AND "+filter.getTable()+"."+filter.getField()+" = "+filter.getValue()+" ");
					}
				}
		}
		 return query.toString();
	}
	
	/**
	 * Get numeric value. It is a generic method.
	 * Used reflection to get attribute data type.
	 * @param value
	 * @param field
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	private static Object getNumericValue(String value, String field) throws SecurityException, NoSuchFieldException{
		
		Field classField = Object.class.getDeclaredField(field);
		
		if (classField.getType() == Integer.TYPE){
			
			return Integer.parseInt(value);
			
		} else if (classField.getType() == Byte.TYPE){
			
			return Byte.parseByte(value);
			
		} else if (classField.getType() == Long.TYPE){
			
			return Long.parseLong(value);
			
		} else if (classField.getType() == Float.TYPE){
			
			return Float.parseFloat(value);
			
		} else if (classField.getType() == Double.TYPE){
			
			return Double.parseDouble(value);
		}
		
		return value;
	}
}
