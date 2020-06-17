package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * URL对象转换器
 *
 * @author LC
 */
public class URLConverter extends AbstractConverter<URL> {

    @Override
    protected URL convertInternal(Object value) {
        try {
            if (value instanceof File) {
                return ((File) value).toURI().toURL();
            }
            if (value instanceof URI) {
                return ((URI) value).toURL();
            }
            return new URL(convertToStr(value));
        } catch (Exception e) {
            // Ignore Exception
        }
        return null;
    }
}

