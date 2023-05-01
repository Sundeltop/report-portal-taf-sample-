//package com.epam.config;
//
//import org.junit.platform.engine.ConfigurationParameters;
//import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
//import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;
//
//import static com.epam.config.ConfigurationManager.configuration;
//
//public class ParallelExecutionStrategy implements ParallelExecutionConfiguration, ParallelExecutionConfigurationStrategy {
//
//    @Override
//    public int getParallelism() {
//        return configuration().parallelThreads();
//    }
//
//    @Override
//    public int getMinimumRunnable() {
//        return 0;
//    }
//
//    @Override
//    public int getMaxPoolSize() {
//        return configuration().parallelThreads();
//    }
//
//    @Override
//    public int getCorePoolSize() {
//        return configuration().parallelThreads();
//    }
//
//    @Override
//    public int getKeepAliveSeconds() {
//        return 30;
//    }
//
//    @Override
//    public ParallelExecutionConfiguration createConfiguration(final ConfigurationParameters configurationParameters) {
//        return this;
//    }
//}
