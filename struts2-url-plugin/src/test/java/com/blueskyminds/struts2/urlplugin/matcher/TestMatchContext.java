package com.blueskyminds.struts2.urlplugin.matcher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Date Started: 29/01/2008
 * <p/>
 * History:
 */
public class TestMatchContext {

    @Test
    public void testGroupMatch() {

        MatchContext matchContext = new MatchContext();
        matchContext.addGroup("everything");
        matchContext.addGroup("example");

        assertEquals("example", matchContext.evaluateExpression("$1"));
        assertEquals("everything", matchContext.evaluateExpression("$0"));
        assertEquals("", matchContext.evaluateExpression("$2"));
        assertEquals("testexample", matchContext.evaluateExpression("test$1"));
        assertEquals("", matchContext.evaluateExpression("${1}"));
        assertEquals("test", matchContext.evaluateExpression("${1}test"));
        assertEquals("test", matchContext.evaluateExpression("test${1}"));
        assertEquals("testtest", matchContext.evaluateExpression("test${1}test"));
        assertEquals("testexampletest", matchContext.evaluateExpression("test$1test"));
        assertEquals("testeverythingtest", matchContext.evaluateExpression("test$0test"));
        assertEquals(null, matchContext.evaluateExpression(null));
    }


    @Test
    public void testParamMatch() {

        MatchContext matchContext = new MatchContext();
        matchContext.put("name", "example");
        String pattern = "${name}";

        String result = matchContext.evaluateExpression(pattern);

        assertEquals("example", result);
    }
}
