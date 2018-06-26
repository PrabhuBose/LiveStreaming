package com.intigral.mobile.android.livestreaming.interfaces;


import org.json.JSONException;

/**
 * Created by Prabhu on 6/26/18 LiveStreaming.
 */
public interface IServiceInvokerCallback {
    void onRequestCompleted(String response) throws JSONException;
}

