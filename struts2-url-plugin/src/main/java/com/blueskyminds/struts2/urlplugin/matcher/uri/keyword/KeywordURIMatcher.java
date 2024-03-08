package com.blueskyminds.struts2.urlplugin.matcher.uri.keyword;

import com.blueskyminds.struts2.urlplugin.matcher.uri.DefaultURIMatcher;

/**
 * A specialisation of the DefaultURIMatcher that uses keywords for the commonly
 *   used expression
 *
 * Date Started: 22/02/2008
 * <p/>
 * History:
 */
public class KeywordURIMatcher extends DefaultURIMatcher {

    public static final String DEFAULT_NAME = "keyword";

    public KeywordURIMatcher() {
        super(new KeywordPatternMatcherFactory());
    }
}
