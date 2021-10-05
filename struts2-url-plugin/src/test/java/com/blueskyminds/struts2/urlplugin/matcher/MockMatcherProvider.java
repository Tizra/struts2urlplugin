package com.blueskyminds.struts2.urlplugin.matcher;

import com.blueskyminds.struts2.urlplugin.matcher.action.MatcherProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple implementation of a MatcherProvider that holds matcher instances Map keyed by id
 *
 * Date Started: 22/01/2008
 * <p/>
 * History:
 */
public class MockMatcherProvider<T> implements MatcherProvider<T> {

    private Map<String, T> matchers;

    public MockMatcherProvider() {
        matchers = new HashMap<>();
    }

    public void addMatcher(String id, T matcher) {
        matchers.put(id, matcher);
    }

    /**
     * Provides the Matcher with the specified Id
     *
     * @param id unique id of this matcher.
     */
    @Override
    public T getMatcher(String id) {
        return matchers.get(id);
    }
}
