package com.mxtech.demo.listener;

public interface ISkinLoader {
    void attach(ISkinUpdate observer);
    void detach(ISkinUpdate observer);
    void notifySkinUpdate();
}
