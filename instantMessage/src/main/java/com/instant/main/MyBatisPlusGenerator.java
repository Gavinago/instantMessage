package com.instant.main;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 类说明
 *
 * @author grl
 * @date 2019年11月15日 新建
 */
public class MyBatisPlusGenerator {



	public void config() {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		// 配置代码输出目录
		gc.setOutputDir(projectPath + "/src/main/java");
		// 配置作者
		gc.setAuthor("grl");
		gc.setOpen(false);
		//实体属性 Swagger2 注解
		gc.setSwagger2(true);
		gc.setBaseResultMap(true);
		gc.setIdType(IdType.AUTO);
		gc.setEnableCache(true);// XML 二级缓存
		gc.setFileOverride(true);// 是否覆盖文件
		gc.setActiveRecord(true);// 开启 activeRecord 模式
		gc.setBaseColumnList(true);// XML columList
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setControllerName("%sController");
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl("jdbc:mysql://192.168.1.159:3306/im?useUnicode=true&useSSL=false&characterEncoding=utf-8&serverTimezone=GMT%2B8");
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername("admin");
		dsc.setPassword("123456");
		mpg.setDataSource(dsc);
		// 包配置
		PackageConfig pc = new PackageConfig();
		// pc.setModuleName("模块名");
		pc.setParent("com.instant");
		pc.setController("controller");
		pc.setService("service");
		pc.setServiceImpl("service.Impl");
		pc.setMapper("mapper");
		pc.setEntity("model");
		pc.setXml("xml");
		mpg.setPackageInfo(pc);
		
		// 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
		// 包配置

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// 表名生成策略(下划线转驼峰命名)
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// 列名生成策略(下划线转驼峰命名)
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		// 是否启动Lombok配置
		strategy.setEntityLombokModel(true);
		// 是否启动REST风格配置
		strategy.setRestControllerStyle(true);
		//strategy.setTablePrefix(new String[] { "mmall_" });// 此处可以修改为您的表前缀
		strategy.setInclude(new String[] { "im_monitor",  "t_account", "t_recodermessage", "t_user"}); // 需要生成的表

		strategy.setSuperServiceClass(null);
		strategy.setSuperServiceImplClass(null);
		strategy.setSuperMapperClass(null);
		mpg.setStrategy(strategy);
		/*
		 *  // .setCapitalMode(true)// 全局大写命名
                                .setDbColumnUnderline(true)// 全局下划线命名
                                // .setTablePrefix(new String[]{"unionpay_"})// 此处可以修改为您的表前缀
                                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                                // .setInclude(new String[] {"user"}) // 需要生成的表
                                // .setExclude(new String[]{"test"}) // 排除生成的表
                                // 自定义实体，公共字段
                                // .setSuperEntityColumns(new String[]{"test_id"})
                                .setTableFillList(tableFillList)
                                // 自定义实体父类
                                // .setSuperEntityClass("com.baomidou.demo.base.BsBaseEntity")
                                // // 自定义 mapper 父类
                                // .setSuperMapperClass("com.baomidou.demo.base.BsBaseMapper")
                                // // 自定义 service 父类
                                // .setSuperServiceClass("com.baomidou.demo.base.BsBaseService")
                                // // 自定义 service 实现类父类
                                // .setSuperServiceImplClass("com.baomidou.demo.base.BsBaseServiceImpl")
                                // 自定义 controller 父类
                                // .setSuperControllerClass("com.baomidou.demo.TestController")
                                // 【实体】是否生成字段常量（默认 false）
                                // public static final String ID = "test_id";
                                .setEntityColumnConstant(true)
                                // 【实体】是否为构建者模型（默认 false）
                                // public User setName(String name) {this.name = name; return this;}
                                .setEntityBuilderModel(true)
                                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                                .setEntityLombokModel(true)
		 */
		
		
		
		
		

		// 执行
		mpg.execute();

	}
	
	public static void main(String[] args) {
		MyBatisPlusGenerator my = new MyBatisPlusGenerator();
		my.config();
	}
}