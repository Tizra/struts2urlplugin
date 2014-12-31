package com.blueskyminds.struts2.urlplugin.matcher.action;

/**
 * Provides the matcher instances used by URIMatcher and ActionMatcher
 */
public interface MatcherProvider<T> {

    /**
     * Provides the matcher with the specified Id
     * @param id        unique id of this matcher.
     * */
    T getMatcher(String id);

}
