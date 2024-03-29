package com.blueskyminds.struts2.urlplugin;

import com.blueskyminds.struts2.urlplugin.configuration.ActionMapConfiguration;
import com.blueskyminds.struts2.urlplugin.configuration.MockConfigurationFactory;
import com.blueskyminds.struts2.urlplugin.configuration.SampleOriginalActionMapConfiguration;
import com.blueskyminds.struts2.urlplugin.matcher.MockMatcherProvider;
import com.blueskyminds.struts2.urlplugin.matcher.action.ActionMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.action.DefaultActionMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.action.name.ActionNameMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.action.name.PlainTextActionNameMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.action.namespace.NamespaceMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.action.namespace.PlainTextNamespaceMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.uri.DefaultURIMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.uri.URIMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.uri.keyword.KeywordURIMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.uri.regex.RegExURIMatcher;
import com.blueskyminds.struts2.urlplugin.utils.ComponentURI;
import com.opensymphony.xwork2.config.Configuration;

import org.apache.struts2.dispatcher.mapper.ActionMapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the URLPatternActionMapper
 *
 * Date Started: 21/01/2008
 * <p/>
 * History:
 */
public class TestPatternActionMapper {

    private Configuration configuration;

    private MockMatcherProvider<ActionNameMatcher> actionMatcherProvider;
    private MockMatcherProvider<NamespaceMatcher> namespaceMatcherProvider;
    private MockMatcherProvider<URIMatcher> uriMatcherProvider;
    private ActionMatcher actionMatcher;
    private ActionMapConfiguration actionMapConfiguration;

    public TestPatternActionMapper() {

        //setup the MatcherProvider.  The matchers control how to match an ActionConfig
        actionMatcherProvider = new MockMatcherProvider<>();
        actionMatcherProvider.addMatcher(PlainTextActionNameMatcher.DEFAULT_NAME, new PlainTextActionNameMatcher());

        namespaceMatcherProvider = new MockMatcherProvider<>();
        namespaceMatcherProvider.addMatcher(PlainTextNamespaceMatcher.DEFAULT_NAME, new PlainTextNamespaceMatcher());

        uriMatcherProvider = new MockMatcherProvider<>();
        uriMatcherProvider.addMatcher(RegExURIMatcher.DEFAULT_NAME, new RegExURIMatcher());
        uriMatcherProvider.addMatcher(DefaultURIMatcher.DEFAULT_NAME, new DefaultURIMatcher());
        uriMatcherProvider.addMatcher(KeywordURIMatcher.DEFAULT_NAME, new KeywordURIMatcher());

        actionMatcher = new DefaultActionMatcher(namespaceMatcherProvider, actionMatcherProvider);

        // setup the url-mapping configuration
        actionMapConfiguration = new SampleOriginalActionMapConfiguration();

        configuration = MockConfigurationFactory.createConfiguration();
    }

    /**
     * match an action named 'example' in the /example namespace
     */
    @Test
    public void testPackages() {
        URLPatternActionMapper actionMapper = new URLPatternActionMapper(actionMapConfiguration, actionMatcher, uriMatcherProvider);
        ActionMapping mapping = actionMapper.getMapping(new ComponentURI("get", "/example/example.action", null), configuration);

        assertNotNull(mapping);
        assertEquals(MockConfigurationFactory.ACTION1_NAME, mapping.getName());
        assertEquals(MockConfigurationFactory.PACKAGE1_NAMESPACE, mapping.getNamespace());

        assertNull(actionMapper.getMapping(new ComponentURI("get", "/example/acb.action", null), configuration));
        assertNull(actionMapper.getMapping(new ComponentURI("get", "/example/.action", null), configuration));
        assertNull(actionMapper.getMapping(new ComponentURI("get", "/example/example", null), configuration));
        assertNull(actionMapper.getMapping(new ComponentURI("get", "/example/example/example.action", null), configuration));

        assertNotNull(actionMapper.getMapping(new ComponentURI("post", "/example/example.action", null), configuration));

        assertNotNull(actionMapper.getMapping(new ComponentURI("get", "/example.action", null), configuration));
        assertNull(actionMapper.getMapping(new ComponentURI("get", "/example2.action", null), configuration));
        assertNotNull(actionMapper.getMapping(new ComponentURI("get", "/example/example2.action", null), configuration));
    }

}
