package com.blueskyminds.struts2.urlplugin.matcher.uri.keyword;

import com.blueskyminds.struts2.urlplugin.matcher.uri.PatternMatcher;
import com.blueskyminds.struts2.urlplugin.matcher.uri.PatternMatcherFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A factory that creates the keyword patterns
 *
 * Keyword patterns are evaluated and converted to regex patterns
 *
 * Date Started: 22/02/2008
 * <p/>
 * History:
 */
public class KeywordPatternMatcherFactory implements PatternMatcherFactory {

	/** Cached PatternMatchers keyed by pattern */
	private Map<String, PatternMatcher> cachedMatchers;

	public KeywordPatternMatcherFactory() {
		cachedMatchers = new ConcurrentHashMap<String, PatternMatcher>(200, 0.85f, 32);
	}

	/** Gets a complied pattern */
	public PatternMatcher get(String pattern) {
		PatternMatcher patternMatcher = cachedMatchers.get(pattern);
		if (patternMatcher == null) {
			patternMatcher = new KeywordPatternMatcher(pattern);
			cachedMatchers.put(pattern, patternMatcher);
		}
		return patternMatcher;
	}

}
