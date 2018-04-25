package com.rockontrol.env.test;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

import parsii.eval.Expression;
import parsii.eval.Parser;
import parsii.eval.Scope;

public class expressionTest {
	public static void main(String[] args) {
		String a = handleData("20","mydata*0.3+lanlian*0.7","");
		System.out.println(a);
	}
	private static String handleData(String value, String exp, String type) {
		if(NumberUtils.toDouble(value)==0){
			return "0.0";
		}
		Map<String, Double> values = new HashMap<String, Double>();
		values.put("lanlian", 15.0);
		values.put("zhigong", 20.0);
		values.put("shengwu", 16.0);
		values.put("tielu", 23.0);
		values.put("average", (values.get("lanlian")+values.get("zhigong")+values.get("shengwu")+values.get("tielu"))/4);
		values.put("lanlianpm10", 4.4);
		values.put("zhigongpm10", 4.4);
		values.put("shengwupm10", 4.4);
		values.put("tielupm10", 4.4);
		values.put("averagepm10", (values.get("lanlianpm10")+values.get("zhigongpm10")+values.get("shengwupm10")+values.get("tielupm10"))/4);
		// compile  
		Scope scope = new Scope();  
		Expression parsiiExpr;
		try {
			parsiiExpr = Parser.parse(exp,scope);
			scope.getVariable("mydata").setValue(NumberUtils.toDouble(value));
			if(values.get("average"+type)!=null)
			scope.getVariable("average").setValue(values.get("average"+type));
			if(values.get("zhigong"+type)!=null)
			scope.getVariable("zhigong").setValue(values.get("zhigong"+type)); 
			if(values.get("lanlian"+type)!=null)  
			scope.getVariable("lanlian").setValue(values.get("lanlian"+type));  
			if(values.get("shengwu"+type)!=null) 
			scope.getVariable("shengwu").setValue(values.get("shengwu"+type));  
			if(values.get("tielu"+type)!=null) 
			scope.getVariable("tielu").setValue(values.get("tielu"+type));  
		  
			// evaluate  
			double result = parsiiExpr.evaluate();  
			DecimalFormat df = new DecimalFormat("0");  
			return df.format(result);
		} catch (parsii.tokenizer.ParseException e) {
			e.printStackTrace();
		}  
		return null;
	}
}
