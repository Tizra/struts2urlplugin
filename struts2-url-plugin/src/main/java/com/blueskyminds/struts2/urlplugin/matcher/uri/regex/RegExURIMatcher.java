package com.blueskyminds.struts2.urlplugin.matcher.uri.regex;

import com.blueskyminds.struts2.urlplugin.matcher.uri.DefaultURIMatcher;

/**
 * Sets up a URIMatcher that treats the method and path as a regular expression
 *
 * Substitutes a RegEx PatternMatcher into the default implementation
 *
 * Date Started: 22/02/2008
 * <p/>
 * History:
 */
public class RegExURIMatcher extends DefaultURIMatcher {

    public static final String DEFAULT_NAME = "regex";

    public RegExURIMatcher() {
        super(new RegExPatternMatcherFactory());
    }

}
