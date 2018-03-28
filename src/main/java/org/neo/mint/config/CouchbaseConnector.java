package org.neo.mint.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vanosov on 16.03.2018.
 */
@Configuration
public class CouchbaseConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(CouchbaseConnector.class);

    @Value("${couchbase.host}")
    private String host;

    @Value("${couchbase.bucket.name.person}")
    private String personBucketName;

    @Value("${couchbase.bucket.name.workunit}")
    private String workUnitBucketName;

    @Value("${couchbase.bucket.user.name}")
    private String userName;

    @Value("${couchbase.bucket.user.password}")
    private String password;

    @Bean
    public Cluster cluster() {
        LOGGER.info("Creating connection to host [{}] with username [{}]", host, userName);
        Cluster cluster = CouchbaseCluster.create(host);
        cluster.authenticate(userName, password);
        return cluster;
    }

    @Bean
    @ConditionalOnBean(Cluster.class)
    @Qualifier(value = "personBucketName")
    public Bucket personBucketName(Cluster cluster) {
        LOGGER.info("Opening bucket with name [{}]", personBucketName);
        return cluster.openBucket(personBucketName);
    }

    @Bean
    @ConditionalOnBean(Cluster.class)
    @Qualifier(value = "workUnitBucket")
    public Bucket workUnitBucket(Cluster cluster) {
        LOGGER.info("Opening bucket with name [{}]", workUnitBucketName);
        return cluster.openBucket(workUnitBucketName);
    }
}
