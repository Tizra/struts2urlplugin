package com.blueskyminds.struts2.urlplugin.matcher.configuration;

import com.blueskyminds.struts2.urlplugin.matcher.MatchContext;
import com.blueskyminds.struts2.urlplugin.matcher.uri.PatternMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.uri.PatternMatcherFactory;
import com.blueskyminds.struts2.urlplugin.matcher.uri.regex.RegExPatternMatcherFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the default RegEx PatternMatcher
 *
 * Date Started: 21/01/2008
 * <p/>
 * History:
 */
public class TestRegExPatternMatcher {

    private static final Log LOG = LogFactory.getLog(TestRegExPatternMatcher.class);

    private PatternMatcherFactory factory = new RegExPatternMatcherFactory();
    private MatchContext matchContext = new MatchContext();

    /** Match everything - no groups */
    @Test
    public void testMatchNotNull() {
        PatternMatcher matcher = factory.get(".*");
        assertNotNull(matcher.matches("", matchContext));
        assertTrue(matcher.matches("", matchContext));
        assertTrue(matcher.matches("abc", matchContext));
        assertFalse(matcher.matches(null, matchContext));
    }

    /** Null pattern always succeeds */
    @Test
    public void testMatchAll() {
        PatternMatcher matcher = factory.get(null);
        assertTrue(matcher.matches("", matchContext));
        assertTrue(matcher.matches("", matchContext));
        assertTrue(matcher.matches("abc", matchContext));
        assertTrue(matcher.matches(null, matchContext));
    }

    /** A commonly used path */
    @Test
    public void testMatchPath() {
        PatternMatcher matcher = factory.get("/example/.+");
        assertFalse(matcher.matches("", matchContext));  // no match
        assertFalse(matcher.matches("/", matchContext));  // no match
        assertFalse(matcher.matches("/example", matchContext));  // no match
        assertFalse(matcher.matches("/example/", matchContext));  // no match
        assertTrue(matcher.matches("/example/action", matchContext)); // match
        assertTrue(matcher.matches("/example/path/", matchContext)); // match
        assertTrue(matcher.matches("/example/path/action", matchContext)); // match
    }

    /** Another commonly used path */
    @Test
    public void testMatchPath2() {
        PatternMatcher matcher = factory.get("/example/.*");
        assertFalse(matcher.matches("", matchContext));  // no match
        assertFalse(matcher.matches("/", matchContext));  // no match
        assertFalse(matcher.matches("/example", matchContext));  // no match
        assertTrue(matcher.matches("/example/", matchContext));  // match
        assertTrue(matcher.matches("/example/action", matchContext));  // match
        assertTrue(matcher.matches("/example/path/", matchContext)); // match
        assertTrue(matcher.matches("/example/path/action", matchContext)); // match
    }

    private String toString(String[] array) {
        boolean first = true;
        if (array != null) {
            StringBuilder sb = new StringBuilder("[");

            for (String value : array) {
                if (!first) {
                    sb.append(",");
                } else {
                    first = false;
                }
                sb.append(value);
            }
            sb.append("]");
            return sb.toString();
        } else {
            return null;
        }
    }
    private String toString(Collection<String> collection) {
        boolean first = true;
        if (collection != null) {
            StringBuilder sb = new StringBuilder("[");
            for (String aCollection : collection) {
                if (!first) {
                    sb.append(",");
                } else {
                    first = false;
                }
                sb.append(aCollection);
            }
            sb.append("]");
            return sb.toString();
        } else {
            return null;
        }
    }

    private boolean groupsMatch(PatternMatcher matcher, String pattern, String[] groupValues) {

        boolean okay = false;

        matchContext = new MatchContext();
        if (matcher.matches(pattern, matchContext)) {
            List<String> groupResults = matchContext.getGroups();
            if (groupResults == null) {
                LOG.error("groupResults is null");
                return false;
            }

            if (groupValues == null) {
                LOG.error("groupValues is null");
                return false;
            }

            if (groupResults.size() != groupValues.length) {
                LOG.error("groupResults contains "+groupResults.size()+" values, groupValues contains "+groupValues.length);
                LOG.error("groupResults: "+toString(groupResults));
                LOG.error("groupValues: "+toString(groupValues));
                return false;
            }

            okay = true;
            int index = 0;
            for (String groupResult : groupResults) {
                if (groupResult != null) {
                    if (!groupResult.equals(groupValues[index])) {
                        okay = false;
                        break;
                    }
                } else {
                    if (groupValues[index] != null) {
                        okay = false;
                        break;
                    }
                }
                index++;
            }

            if (!okay) {
                LOG.error("groupResults do not match groupValues:");
                LOG.error("groupResults: "+toString(groupResults));
                LOG.error("groupValues: "+toString(groupValues));
            }
        } else {
            LOG.error("groupResults do not match groupValues");
        }

        return okay;
    }

    /** Matches any path containing .action */
    @Test
    public void testGroup1() {
        PatternMatcher matcher = factory.get("^(.*)/(.*)\\.action$");
        assertFalse(matcher.matches("", matchContext));  // no match
        assertFalse(matcher.matches("/", matchContext));  // no match
        assertFalse(matcher.matches("/example", matchContext));  // no match
        assertFalse(matcher.matches("/example/", matchContext));  // no match
        //assertTrue(groupsMatch(matcher.matches("example.action"), new String[] {"example.action", "", "example"}));
        //assertTrue(groupsMatch(matcher.matches(".action"), new String[] {".action", "", ""}));
        assertTrue(groupsMatch(matcher, "/.action", new String[] {"/.action", "", ""}));
        assertTrue(groupsMatch(matcher, "/example.action", new String[] {"/example.action", "", "example"}));
        assertTrue(groupsMatch(matcher, "/example/.action", new String[] {"/example/.action", "/example", ""}));
        assertTrue(groupsMatch(matcher, "/example/path/example.action", new String[] {"/example/path/example.action", "/example/path", "example"}));
        assertTrue(groupsMatch(matcher, "/example/path/.action", new String[] {"/example/path/.action", "/example/path", ""}));
    }

}
