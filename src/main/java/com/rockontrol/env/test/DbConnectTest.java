package com.rockontrol.env.test;

import java.sql.SQLException;
import java.util.List;

import cn.hutool.core.lang.Console;
import cn.hutool.db.Entity;
import cn.hutool.db.SqlRunner;

/**
 * 数据库连接测试工具
 * @author dongpeng
 */
public class DbConnectTest {
	
	public static void main(String[] args) {
		SqlRunner runner = SqlRunner.create();
		
		try {
			List<Entity> query = runner.query("select * from rk_users where id = ?", 1);
			//TODO need to detete
			Console.log(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
