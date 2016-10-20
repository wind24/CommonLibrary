package com.commonlibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huangzefeng on 2016/10/20.
 */

public class DateUtil {

  private final static String YMD = "yyyy-MM-dd";
  private final static String YMDTIME = "yyyy-MM-dd HH:mm:ss";
  private final static String HM = "HH:mm";
  private final static String HMS = "HH:mm:ss";

  private static SimpleDateFormat dateFormat;

  static {
    dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
  }

  public static String applyYMD(Date date) {
    dateFormat.applyPattern(YMD);
    return dateFormat.format(date);
  }

  public static String applyYMDTime(Date date) {
    dateFormat.applyPattern(YMDTIME);
    return dateFormat.format(date);
  }

  public static String applyHM(Date date) {
    dateFormat.applyPattern(HM);
    return dateFormat.format(date);
  }

  public static String applyHMS(Date date) {
    dateFormat.applyPattern(HMS);
    return dateFormat.format(date);
  }

  public static Date parseYMD(String source) {
    dateFormat.applyPattern(YMD);
    try {
      return dateFormat.parse(source);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static Date parseYMDTime(String source) {
    dateFormat.applyPattern(YMDTIME);
    try {
      return dateFormat.parse(source);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static Date parseHM(String source) {
    dateFormat.applyPattern(HM);
    try {
      return dateFormat.parse(source);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static Date parseHMS(String source) {
    dateFormat.applyPattern(HMS);
    try {
      return dateFormat.parse(source);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }
}
