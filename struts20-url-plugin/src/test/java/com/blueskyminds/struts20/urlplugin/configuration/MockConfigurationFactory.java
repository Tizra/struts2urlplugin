package com.blueskyminds.struts20.urlplugin.configuration;

import com.opensymphony.xwork2.config.impl.MockConfiguration;
import com.opensymphony.xwork2.config.entities.PackageConfig;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.config.Configuration;
import com.blueskyminds.struts20.urlplugin.configuration.Example1Action;

/**
 * Used to setup Struts2.0 configuration for testing
 *
 * Date Started: 29/01/2008
 * <p/>
 * History:
 */
public class MockConfigurationFactory {

    public static final String DEFAULT_PACKAGE_NAME = "default";
    public static final String DEFAULT_PACKAGE_NAMESPACE = "/";
    public static final String PACKAGE1_NAME = "example";
    public static final String PACKAGE1_NAMESPACE = "/example";
    public static final String ACTION1_NAME = "example";
    public static final String ACTION2_NAME = "example2";

    /**
     * Defines the following actions:
     *    package: /
     *         action: example
     *
     *    package: /example
     *         action: example
     *         action: example2
     * @return
     */
    public static Configuration createConfiguration() {
        // setup a Struts2 configuration
        Configuration configuration = new MockConfiguration();
        // Example1Action is used in the default package as well as package1.  The only difference is namespace
        ActionConfig default1Action = new ActionConfig.Builder("com.blueskyminds.struts20.urlplugin.configuration",
            "example1", Example1Action.class.getName()).build();
        PackageConfig defaultPackage = new PackageConfig.Builder(DEFAULT_PACKAGE_NAME)
                .namespace(DEFAULT_PACKAGE_NAMESPACE).strictMethodInvocation(false)
                .addActionConfig(ACTION1_NAME, default1Action).build();


        ActionConfig example1Action = new ActionConfig.Builder("com.blueskyminds.struts20.urlplugin.configuration",
            "example1", Example1Action.class.getName()).build();
        // Example2Action is used only in package1
        ActionConfig example2Action = new ActionConfig.Builder("com.blueskyminds.struts20.urlplugin.configuration",
            "example2", Example2Action.class.getName()).build();
        PackageConfig example1Package = new PackageConfig.Builder(PACKAGE1_NAME)
                .namespace(PACKAGE1_NAMESPACE).strictMethodInvocation(false)
                .addActionConfig(ACTION1_NAME, example1Action)
                .addActionConfig(ACTION2_NAME, example2Action).build();

        configuration.addPackageConfig(DEFAULT_PACKAGE_NAME, defaultPackage);
        configuration.addPackageConfig(PACKAGE1_NAME, example1Package);

        return configuration;
    }
}
