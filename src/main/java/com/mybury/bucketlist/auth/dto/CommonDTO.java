package com.mybury.bucketlist.auth.dto;

import java.lang.reflect.Method;

/**
 * fileName      : CommonDTO
 * date         : 2022/01/22
 * description  :
 */
public class CommonDTO {
  public void dataCopy(Object original) {
    for (Method method : original.getClass().getDeclaredMethods()) {
      String methodName = method.getName();
      if (!methodName.startsWith("get") || methodName.equals("getClass")) {
        continue;
      }
      String setMethodName = methodName.replaceFirst("get", "set");
      try {
        Object methodValue = method.invoke(original);
        if (methodValue == null) {
          continue;
        }
        try {
          this.getClass().getMethod(setMethodName, method.getReturnType()).invoke(this, methodValue);
        } catch (NoSuchMethodException e) {
          this.getClass().getMethod(setMethodName, methodValue.getClass()).invoke(this, methodValue);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
