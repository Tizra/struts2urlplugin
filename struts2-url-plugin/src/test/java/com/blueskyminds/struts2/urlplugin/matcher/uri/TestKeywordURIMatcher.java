package com.blueskyminds.struts2.urlplugin.matcher.uri;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import com.blueskyminds.struts2.urlplugin.configuration.URIPattern;
import com.blueskyminds.struts2.urlplugin.utils.ComponentURI;
import com.blueskyminds.struts2.urlplugin.matcher.uri.keyword.KeywordURIMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.MatchContext;

/**
 * Runs some unit tests through the DefaultURIMatcher
 *
 * Date Started: 21/01/2008
 * <p/>
 * History:
 */
public class TestKeywordURIMatcher {

    private KeywordURIMatcher uriMatcher = new KeywordURIMatcher();
    private MatchContext matchContext = new MatchContext();

    @Test
    public void testMatch() {
        URIPattern uriPattern = new URIPattern("1", "keyword", "GET", "namespace/action[ext]");
        assertTrue(uriMatcher.matchesPath(new ComponentURI("get", "/exampleNamespace/example.action", null), uriPattern, matchContext));
        assertEquals("/exampleNamespace", matchContext.get("namespace"));
        assertEquals("example", matchContext.get("action"));
//        URIPattern uriPattern2 = new URIPattern("1", "GET", "namespace/action/new[ext]");
//        URIPattern uriPattern3 = new URIPattern("1", "GET", "namespace/action[ext];new");
//        URIPattern uriPattern4 = new URIPattern("1", "GET", "namespace/action/[ext]");
//        URIPattern uriPattern5 = new URIPattern("1", "GET", "namespace/action/id[ext]");

    }

}