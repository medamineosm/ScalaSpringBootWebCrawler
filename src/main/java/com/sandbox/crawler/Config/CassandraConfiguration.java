package com.sandbox.crawler.Config;

import org.springframework.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.cassandra.config.DataCenterReplication;
import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ouasmine on 03/05/2017.
 */
@Configuration
@EnableCassandraRepositories(basePackages = "com.example.com.sandbox.crawler.Repository")
public class CassandraConfiguration extends AbstractCassandraConfiguration{

    private final static String KEYSPACE = "crawler";

    @Bean
    public CassandraCqlClusterFactoryBean cluster(){
        CassandraCqlClusterFactoryBean cluster = new CassandraCqlClusterFactoryBean();
        cluster.setKeyspaceCreations(getKeyspaceCreations());
        cluster.setContactPoints("127.0.0.1");
        cluster.setPort(9042);
        return cluster;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }


    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.sandbox.crawler.com.sandbox.crawler.Model"};
    }

    @Bean
    public CassandraMappingContext cassandraMapping(){
        return new BasicCassandraMappingContext();
    }

    // Below method creates "my_keyspace" if it doesnt exist.
    private CreateKeyspaceSpecification getKeySpaceSpecification() {
        CreateKeyspaceSpecification pandaCoopKeyspace = new CreateKeyspaceSpecification();
        DataCenterReplication dcr = new DataCenterReplication("dc1", 3L);
        pandaCoopKeyspace.name(KEYSPACE);
        pandaCoopKeyspace.ifNotExists(true).createKeyspace().withNetworkReplication(dcr);
        return pandaCoopKeyspace;
    }

    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        List<CreateKeyspaceSpecification> createKeyspaceSpecifications = new ArrayList<>();
        createKeyspaceSpecifications.add(getKeySpaceSpecification());
        return createKeyspaceSpecifications;
    }




}
