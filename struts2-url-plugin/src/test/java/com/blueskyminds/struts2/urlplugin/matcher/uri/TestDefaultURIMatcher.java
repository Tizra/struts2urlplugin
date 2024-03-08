package com.blueskyminds.struts2.urlplugin.matcher.uri;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import com.blueskyminds.struts2.urlplugin.configuration.URIPattern;
import com.blueskyminds.struts2.urlplugin.utils.ComponentURI;
import com.blueskyminds.struts2.urlplugin.matcher.MatchContext;

/**
 * Runs some unit tests through the DefaultURIMatcher
 *
 * Date Started: 21/01/2008
 * <p/>
 * History:
 */
public class TestDefaultURIMatcher {

    private DefaultURIMatcher uriMatcher = new DefaultURIMatcher();
    private MatchContext matchContext = new MatchContext();

    @Test
    public void testMatch() {
        URIPattern uriPattern = new URIPattern("1", "regex", "GET", ".*");

        assertFalse(uriMatcher.matchesMethod(new ComponentURI("get", "/", null), uriPattern, matchContext));
        assertTrue(uriMatcher.matchesMethod(new ComponentURI("GET", "/", null), uriPattern, matchContext));
        assertFalse(uriMatcher.matchesMethod(new ComponentURI("post", "/", null), uriPattern, matchContext));
        assertFalse(uriMatcher.matchesMethod(new ComponentURI(null, "/", null), uriPattern, matchContext));

        assertTrue(uriMatcher.matchesPath(new ComponentURI("GET", "/", null), uriPattern, matchContext));
        assertTrue(uriMatcher.matchesPath(new ComponentURI("GET", "", null), uriPattern, matchContext));
        assertTrue(uriMatcher.matchesPath(new ComponentURI("GET", "/example", null), uriPattern, matchContext));
        assertTrue(uriMatcher.matchesPath(new ComponentURI("GET", "/example/", null), uriPattern, matchContext));
    }

}
