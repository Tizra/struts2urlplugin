package com.blueskyminds.struts20.urlplugin.configuration;


import java.util.Map;
import java.util.HashMap;

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
		matchers = new HashMap<String, T>();
	}

	public void addMatcher(String id, T matcher) {
		matchers.put(id, matcher);
	}

	/**
	 * Provides the Matcher with the specified Id
	 *
	 * @param id unique id of this matcher.
	 */
	public T getMatcher(String id) {
		return matchers.get(id);
	}
}