package com.intigral.mobile.android.livestreaming.interfaces;


import org.json.JSONException;

public interface IServiceInvokerCallback {
    void onRequestCompleted(String response, String mode) throws JSONException;
}

