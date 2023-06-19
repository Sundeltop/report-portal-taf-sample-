package com.epam.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

import static org.aeonbits.owner.Config.LoadPolicy;
import static org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "classpath:config.properties",
        "classpath:jira.properties",
})
public interface Configuration extends Config {

    @Key("base.url")
    @ConverterClass(SystemPropertiesConvertor.class)
    String baseUrl();

    @Key("parallel.threads")
    Integer parallelThreads();

    @Key("default.user.login")
    String defaultUserLogin();

    @Key("default.user.password")
    String defaultUserPassword();

    @Key("slack.token")
    @ConverterClass(SystemPropertiesConvertor.class)
    String slackToken();

    @Key("jira.host")
    String jiraHost();

    @Key("jira.username")
    String jiraUsername();

    @Key("jira.password")
    String jiraPassword();
}
