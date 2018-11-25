package com.bms.config;

import com.bms.controller.BookController;
import com.bms.controller.IndexController;
import com.bms.model._MappingKit;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * @author lichao 项目配置 2018年11月25日
 */
public class BmsInterfaceConfig extends JFinalConfig {
	
	/**
	 * 启动入口，运行此 main 方法可以启动项目，此 main 方法可以放置在任意的 Class 类定义中，不一定要放于此
	 * 使用本方法启动过第一次以后，会在开发工具的 debug、run configuration 中自动生成
	 * 一条启动配置项，可对该自动生成的配置再继续添加更多的配置项，例如 Program arguments
	 */
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 8085, "/", 5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jfinal.config.JFinalConfig#configConstant(com.jfinal.config.Constants)
	 * 初始化配置
	 */
	@Override
	public void configConstant(Constants me) {

		PropKit.use("mysql.properties");
		me.setDevMode(PropKit.getBoolean("devMode"));
		me.setEncoding("UTF-8");

		// 支持 Controller、Interceptor 之中使用 @Inject 注入业务层，并且自动实现 AOP
		me.setInjectDependency(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jfinal.config.JFinalConfig#configRoute(com.jfinal.config.Routes) 路由
	 */
	@Override
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add("/book", BookController.class);

	}

	@Override
	public void configEngine(Engine me) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jfinal.config.JFinalConfig#configPlugin(com.jfinal.config.Plugins)
	 * 配置数据库连接池
	 */
	@Override
	public void configPlugin(Plugins me) {
		DruidPlugin mysqlPlugin = new DruidPlugin(PropKit.get("url"), PropKit.get("username"), PropKit.get("password"));
		me.add(mysqlPlugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(mysqlPlugin);
		// 输出 sql 语句
		arp.setShowSql(PropKit.getBoolean("devMode"));
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
	}

	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("url"), PropKit.get("username"), PropKit.get("password"));
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub

	}
}
