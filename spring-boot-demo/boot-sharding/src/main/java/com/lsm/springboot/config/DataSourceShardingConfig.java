package com.lsm.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.MasterSlaveDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.dangdang.ddframe.rdb.sharding.id.generator.self.CommonSelfIdGenerator;
import com.dangdang.ddframe.rdb.sharding.jdbc.ShardingDataSource;
import com.lsm.springboot.algorithm.StudentSingleKeyDatabaseShardingAlgorithm;
import com.lsm.springboot.algorithm.StudentSingleKeyTableShardingAlgorithm;
import com.lsm.springboot.algorithm.UserSingleKeyDatabaseShardingAlgorithm;
import com.lsm.springboot.algorithm.UserSingleKeyTableShardingAlgorithm;
import com.lsm.springboot.constant.TransactionalManagerConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenming.li on 2017/6/13.
 */
@Slf4j
@Configuration
@MapperScan(basePackages = DataSourceShardingConfig.DATA_PACKAGE, sqlSessionFactoryRef = DataSourceShardingConfig.SQL_SESSION_FACTORY_DATA)
public class DataSourceShardingConfig {

    static final String DATA_PACKAGE = "com.lsm.springboot.mapper.sharding";

    static final String SQL_SESSION_FACTORY_DATA = "shardingSqlSessionFactory";

    private static final String DATA_SOURCE_DATA = "shardingDataSource";

    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;

    private DataSource createDataSource(final String dataSourceName) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://192.168.31.80:3306/%s", dataSourceName));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(15);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(280); // sec
        dataSource.setMaxWait(30000);
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        dataSource.setTestOnBorrow(true);
        return dataSource;

    }

    @Bean(name = SQL_SESSION_FACTORY_DATA)
    public SqlSessionFactory sqlSessionFactoryBeanData(@Qualifier(DATA_SOURCE_DATA) DataSource dataSource) {
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            return sessionFactory.getObject();
        } catch (Exception e) {
            log.error("faile to create data sql session", e);
            throw new RuntimeException(e);
        }
    }

    /**
     *  配置好dataSourceRulue,即对数据源进行管理
     * @return
     */
    @Bean
    public DataSourceRule dataSourceRule(){
       /* // 构建读写分离数据源
        DataSource masterDataSourceSharding0 = createDataSource("sharding_0");
        DataSource slaveDataSourceSharding0 = createDataSource("slave_sharding_0");
        DataSource masterSlaveds0 = MasterSlaveDataSourceFactory.createDataSource("ms_0", masterDataSourceSharding0, slaveDataSourceSharding0);
        DataSource masterDataSourceSharding1 = createDataSource("sharding_1");
        DataSource slaveDataSourceSharding1 = createDataSource("slave_sharding_1");
        DataSource masterSlaveds1 = MasterSlaveDataSourceFactory.createDataSource("ms_1", masterDataSourceSharding1, slaveDataSourceSharding1);
        // 构建分库分表数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("ms_0", masterSlaveds0);
        dataSourceMap.put("ms_1", masterSlaveds1);
        return new DataSourceRule(dataSourceMap);*/
       //分库分表demo
        //设置分库映射
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        //添加两个数据库ds_0,ds_1到map里
        dataSourceMap.put("sharding_0", createDataSource("sharding_0"));
        dataSourceMap.put("sharding_1", createDataSource("sharding_1"));
        return new DataSourceRule(dataSourceMap, "sharding_0");
    }

    /**
     * 对t_user表的配置，进行分库配置，逻辑表名为t_user，每个库有实际的三张表
     * @param dataSourceRule
     * @return
     */
    @Bean
    public TableRule userTableRule(@Qualifier("dataSourceRule") DataSourceRule dataSourceRule, DatabaseShardingStrategy userDatabaseShardingStrategy, TableShardingStrategy userTableShardingStrategy) {

        return TableRule.builder("t_user")
                .actualTables(Arrays.asList("t_user_0", "t_user_1", "t_user_2"))
                .dataSourceRule(dataSourceRule)
                .databaseShardingStrategy(userDatabaseShardingStrategy)
                .tableShardingStrategy(userTableShardingStrategy)
                .build();
    }

    /**
     * t_user分库策略
     * @return
     */
    @Bean
    public DatabaseShardingStrategy userDatabaseShardingStrategy() {

        return new DatabaseShardingStrategy("user_id", new UserSingleKeyDatabaseShardingAlgorithm());
    }

    /**
     * t_user 分表策略
     * @return
     */
    @Bean
    public TableShardingStrategy userTableShardingStrategy() {

        return new TableShardingStrategy("user_id", new UserSingleKeyTableShardingAlgorithm());
    }

    /**
     * 对t_user表的配置，进行分库配置，逻辑表名为t_user，每个库有实际的三张表
     * @param dataSourceRule
     * @return
     */
    @Bean
    public TableRule studentTableRule(@Qualifier("dataSourceRule") DataSourceRule dataSourceRule, DatabaseShardingStrategy studentDatabaseShardingStrategy, TableShardingStrategy studentTableShardingStrategy) {

        return TableRule.builder("t_student")
                .actualTables(Arrays.asList("t_student_0", "t_student_1"))
                .dataSourceRule(dataSourceRule)
                .databaseShardingStrategy(studentDatabaseShardingStrategy)
                .tableShardingStrategy(studentTableShardingStrategy)
                .build();
    }

    /**
     * t_student 分库策略
     * @return
     */
    @Bean
    public DatabaseShardingStrategy studentDatabaseShardingStrategy() {

        return new DatabaseShardingStrategy("student_id", new StudentSingleKeyDatabaseShardingAlgorithm());
    }

    /**
     * t_student 分表策略
     * @return
     */
    @Bean
    public TableShardingStrategy studentTableShardingStrategy() {

        return new TableShardingStrategy("student_id", new StudentSingleKeyTableShardingAlgorithm());
    }

    /**
     * 构成分库分表的规则 传入数据源集合和每个表的分库分表的具体规则
     * @param dataSourceRule
     * @param userTableRule
     * @param studentTableRule
     * @return
     */
    @Bean
    public ShardingRule shardingRule(DataSourceRule dataSourceRule, TableRule userTableRule, TableRule studentTableRule) {
        return ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(userTableRule, studentTableRule))
                .build();
    }

    /**
     * 对datasource进行封装
     * @param shardingRule
     * @return
     */
    @Bean(name = DATA_SOURCE_DATA)
    public DataSource shardingDataSource(ShardingRule shardingRule) {

        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }

    /**
     * 事务
     * @param shardingDataSource
     * @return
     */
    @Bean(name = TransactionalManagerConstant.SHARDING_DATASOURCE_TRANSACTIONAL_MANAGER_NAME)
    public DataSourceTransactionManager shardingTransactionManager(@Qualifier(DATA_SOURCE_DATA) DataSource shardingDataSource) {

        return new DataSourceTransactionManager(shardingDataSource);
    }

    @Bean
    public IdGenerator getIdGenerator() {
        return new CommonSelfIdGenerator();
    }
}
