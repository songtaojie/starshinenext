package com.starshine.application.codegen;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

/**
 * PostgreSQL 代码生成
 * @author songtaojie
 * @version 1.0
 * @since 2025-08-28 下午 周四
 */
public class PostgreSQLGeneratorTest extends BaseGeneratorTest {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:postgresql://localhost:5432/starshine_admin", "postgres", "stj123456")
            .build();

    @Test
    public void testSimple() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        var globalConfig = globalConfig()
                .outputDir(System.getProperty("user.dir") + "/src/main/java")
                .build();
        var strategyConfig = strategyConfig()
                .addInclude("t_simple") // 表名
                .entityBuilder()
                .enableLombok()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .build();
        var packaageConfig = packageConfig()
                .parent(parentPackage)
                .build();
        generator.global(globalConfig)
                .strategy(strategyConfig)
                .packageInfo(packaageConfig)
                .execute();
        System.out.println("✅ 代码生成完成！");
    }
}
