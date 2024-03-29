package com.blueskyminds.struts2.urlplugin.matcher;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import com.blueskyminds.struts2.urlplugin.matcher.action.namespace.PlainTextNamespaceMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.action.namespace.NamespaceMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.action.name.PlainTextActionNameMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.action.name.ActionNameMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.action.DefaultActionMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.action.ActionMatcher;
import com.blueskyminds.struts2.urlplugin.configuration.ActionSelector;
import com.blueskyminds.struts2.urlplugin.configuration.MockConfigurationFactory;
import com.opensymphony.xwork2.config.Configuration;
import org.apache.struts2.dispatcher.mapper.ActionMapping;

/**
 * Runs some tests through the DefaultActionMatcher
 *
 * Date Started: 29/01/2008
 * <p/>
 * History:
 */
public class TestDefaultActionMatcher {

    private MockMatcherProvider<ActionNameMatcher> actionMatcherProvider;
    private MockMatcherProvider<NamespaceMatcher> namespaceMatcherProvider;
    private ActionMatcher actionMatcher;
    private Configuration configuration;


    public TestDefaultActionMatcher() {
        try {
            // setup the MatcherProvider. The matchers control how to match an
            // ActionConfig
            actionMatcherProvider = new MockMatcherProvider<ActionNameMatcher>();
            actionMatcherProvider.addMatcher(
                    PlainTextActionNameMatcher.DEFAULT_NAME,
                    new PlainTextActionNameMatcher());

            namespaceMatcherProvider = new MockMatcherProvider<NamespaceMatcher>();
            namespaceMatcherProvider.addMatcher(
                    PlainTextNamespaceMatcher.DEFAULT_NAME,
                    new PlainTextNamespaceMatcher());

            configuration = MockConfigurationFactory.createConfiguration();

            actionMatcher = new DefaultActionMatcher(namespaceMatcherProvider,
                    actionMatcherProvider);
        } catch (Exception ex) {
            throw new RuntimeException("Setup error", ex);
        }
    }

    @Test
    public void testActionMatcher() {
        MatchContext matchContext = new MatchContext();
        matchContext.put("path", "/example");
        matchContext.put("name", "example");

        ActionSelector actionSelector = new ActionSelector("${path}", "${name}", "execute");

        ActionMapping actionMapping = actionMatcher.match(actionSelector, matchContext, configuration);
        assertNotNull(actionMapping);
    }
}
