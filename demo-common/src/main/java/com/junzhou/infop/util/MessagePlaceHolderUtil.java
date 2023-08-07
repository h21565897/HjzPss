package com.junzhou.infop.util;

import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Map;

public class MessagePlaceHolderUtil {
    private static final String PLACE_HOLDER_PREFIX = "{$";
    private static final String PLACE_HOLDER_SUFFIX = "}";

    private static final PropertyPlaceholderHelper PROPERTY_PLACEHOLDER_HELPER = new PropertyPlaceholderHelper(PLACE_HOLDER_PREFIX, PLACE_HOLDER_SUFFIX);

    public static String replacePlaceHolder(final String template, final Map<String, String> paramMap) {
        return PROPERTY_PLACEHOLDER_HELPER.replacePlaceholders(template, new CustomPlaceholderResolver(template, paramMap));
    }

    private static class CustomPlaceholderResolver implements PropertyPlaceholderHelper.PlaceholderResolver {
        private final String template;
        private final Map<String, String> paramMap;

        public CustomPlaceholderResolver(String template, Map<String, String> paramMap) {
            super();
            this.template = template;
            this.paramMap = paramMap;
        }

        @Override
        public String resolvePlaceholder(String placeholderName) {
            String value = paramMap.get(placeholderName);
            if (StringUtils.isEmpty(value)) {
                String errorStr = MessageFormat.format("template:{0} require param:{1},but not exist! paramMap:{2}", template, placeholderName, paramMap.toString());
                throw new IllegalArgumentException(errorStr);
            }
            return value;
        }
    }
}
