//package com.gmf.core.admin;
//
//
//import cn.hutool.extra.template.TemplateConfig;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// * @author gmf
// * @date 2020/12/31 12:08
// * @descrition:
// */
//public class CodeGenerator {
//
//    /**
//     * <p>
//     * 读取控制台内容
//     * </p>
//     */
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotBlank(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }
//
//    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("gmf");
//        gc.setOpen(false);
//        // gc.setSwagger2(true); 实体属性 Swagger2 注解
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/mall?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("gmf");
//        mpg.setDataSource(dsc);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
//        pc.setParent("com.gmf.dao");
//        mpg.setPackageInfo(pc);
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//
//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 如果模板引擎是 velocity
//        // String templatePath = "/templates/mapper.xml.vm";
//
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(com.baomidou.mybatisplus.generator.config.po.TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/gmf-admin/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//
//        });
//        /*
//        cfg.setFileCreate(new IFileCreate() {
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录，自定义目录用");
//                if (fileType == FileType.MAPPER) {
//                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
//                    return !new File(filePath).exists();
//                }
//                // 允许生成模板文件
//                return true;
//            }
//        });
//        */
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//
//        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//
//        // 配置自定义输出模板
//        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//        // templateConfig.setEntity("templates/entity2.java");
//        // templateConfig.setService();
//        // templateConfig.setController();
//
//        templateConfig.setXml(null);
//        mpg.setTemplate(templateConfig);
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
////        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
//        strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
//        // 公共父类
////        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
//        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
//        strategy.setInclude(scanner("cms_help, cms_help_category, cms_member_report, cms_prefrence_area, cms_prefrence_area_product_relation, cms_subject, cms_subject_category, cms_subject_comment, cms_subject_product_relation, cms_topic, cms_topic_category, cms_topic_comment, oms_cart_item, oms_company_address, oms_order, oms_order_item, oms_order_operate_history, oms_order_return_apply, oms_order_return_reason, oms_order_setting, pms_album, pms_album_pic, pms_brand, pms_comment, pms_comment_replay, pms_feight_template, pms_member_price, pms_product, pms_product_attribute, pms_product_attribute_category, pms_product_attribute_value, pms_product_category, pms_product_category_attribute_relation, pms_product_full_reduction, pms_product_ladder, pms_product_operate_log, pms_product_vertify_record, pms_sku_stock, sms_coupon, sms_coupon_history, sms_coupon_product_category_relation, sms_coupon_product_relation, sms_flash_promotion, sms_flash_promotion_log, sms_flash_promotion_product_relation, sms_flash_promotion_session, sms_home_advertise, sms_home_brand, sms_home_new_product, sms_home_recommend_product, sms_home_recommend_subject, ums_admin, ums_admin_login_log, ums_admin_permission_relation, ums_admin_role_relation, ums_growth_change_history, ums_integration_change_history, ums_integration_consume_setting, ums_member, ums_member_level, ums_member_login_log, ums_member_member_tag_relation, ums_member_product_category_relation, ums_member_receive_address, ums_member_rule_setting, ums_member_statistics_info, ums_member_tag, ums_member_task, ums_menu, ums_permission, ums_resource, ums_resource_category, ums_role, ums_role_menu_relation, ums_role_permission_relation, ums_role_resource_relation").split(","));
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//    }
//}
