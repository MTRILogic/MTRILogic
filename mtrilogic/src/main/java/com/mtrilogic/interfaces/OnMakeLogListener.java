package com.mtrilogic.interfaces;

@SuppressWarnings("unused")
public interface OnMakeLogListener extends Thread.UncaughtExceptionHandler {

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    void onMakeLog(String line);
}
