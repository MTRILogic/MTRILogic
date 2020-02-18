package com.mtrilogic.interfaces;

@SuppressWarnings("unused")
public interface OnMakeLogListener extends Thread.UncaughtExceptionHandler {
    void onMakeLog(String line);
}
