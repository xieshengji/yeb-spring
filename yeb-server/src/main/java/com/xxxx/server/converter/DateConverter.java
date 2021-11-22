package com.xxxx.server.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author: QiuXuechen
 * @Date: 2021/8/30 18:02
 */
@Component
public class DateConverter implements Converter<String, LocalDate> {
  @Override
  public LocalDate convert(String s) {
    return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }
}
