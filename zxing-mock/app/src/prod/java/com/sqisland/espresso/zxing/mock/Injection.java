package com.sqisland.espresso.zxing.mock;

public class Injection {
  public static ZXingBridge provideZXingBridge() {
    return new ZXingBridge();
  }
}