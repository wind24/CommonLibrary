package com.commonlibrary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangzefeng on 2016/10/3.
 */

public class RegexUtils {

  private final static String CHAR_OR_NUMBER_LIMIT = "[0-9A-Za-z]\\d*";
  private final static String EMAIL_LIMIT =
      "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

  private final static Pattern CHAR_OR_NUMBER_PATTERN;
  private final static Pattern EMAIL_PATTERN;

  static {
    CHAR_OR_NUMBER_PATTERN = Pattern.compile(CHAR_OR_NUMBER_LIMIT);
    EMAIL_PATTERN = Pattern.compile(EMAIL_LIMIT);
  }

  public static boolean isOnlyCharOrNumber(String text) {
    if (text == null) return false;

    Matcher matcher = CHAR_OR_NUMBER_PATTERN.matcher(text);
    return matcher.matches();
  }

  public static boolean isEmail(String text) {
    if (text == null) return false;

    Matcher matcher = EMAIL_PATTERN.matcher(text);
    return matcher.matches();
  }
}
