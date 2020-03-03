package com.mtrilogic.interfaces;

@SuppressWarnings("unused")
public interface OnMakeToastListener extends Thread.UncaughtExceptionHandler{

    // ================< PUBLIC ABSTRACT METHODS >==================================================

    void onMakeToast(String line);
    void onMakeLog(String line);
}
