package com.mybury.bucketlist.core.util;

import org.apache.commons.lang3.StringUtils;

import com.mybury.bucketlist.core.exception.ParameterException;

public class ParameterUtil {

  public static void checkParameter(Object... args) {
    if (isEmpty(args))
      throw new ParameterException();
  }

  private static boolean isEmpty(Object... args) {
    if (args == null)
      return true;

    for (Object arg : args) {
      if (arg == null) {
        return true;
      } else if (arg instanceof String && StringUtils.isBlank((String)arg)) {
        return true;
      }
    }

    return false;
  }
}
