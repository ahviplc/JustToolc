package com.lc.core.convert.impl;

import com.lc.core.convert.AbstractConverter;

import java.util.Currency;

/**
 * 货币{@link Currency} 转换器
 *
 * @author LC
 * @since 0.0.1
 */
public class CurrencyConverter extends AbstractConverter<Currency> {

    @Override
    protected Currency convertInternal(Object value) {
        return Currency.getInstance(value.toString());
    }
}
