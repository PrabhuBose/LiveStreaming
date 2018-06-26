package com.intigral.mobile.android.livestreaming.utils;

import android.util.Log;

import com.android.volley.Request;
import com.intigral.mobile.android.livestreaming.constants.StreamingConstants;

/**
 * Created by Prabhu on 6/26/18 LiveStreaming.
 */
public class StreamingUtils {

    public static void showLog(String key, String message) {
        Log.e(key, message);
    }


    public static int getRequest(String requestType) {
        if (requestType.equalsIgnoreCase(StreamingConstants.POST))
            return Request.Method.POST;
        else if (requestType.equalsIgnoreCase(StreamingConstants.GET))
            return Request.Method.GET;
        else if (requestType.equalsIgnoreCase(StreamingConstants.DELETE))
            return Request.Method.DELETE;
        else
            return Request.Method.PUT;
    }
}
