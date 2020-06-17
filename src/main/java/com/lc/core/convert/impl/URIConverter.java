package com.lc.core.convert.impl;

import java.io.File;
import java.net.URI;
import java.net.URL;

import com.lc.core.convert.AbstractConverter;

/**
 * URI对象转换器
 *
 * @author LC
 */
public class URIConverter extends AbstractConverter<URI> {

    @Override
    protected URI convertInternal(Object value) {
        try {
            if (value instanceof File) {
                return ((File) value).toURI();
            }
            if (value instanceof URL) {
                return ((URL) value).toURI();
            }
            return new URI(convertToStr(value));
        } catch (Exception e) {
            // Ignore Exception
        }
        return null;
    }
}

