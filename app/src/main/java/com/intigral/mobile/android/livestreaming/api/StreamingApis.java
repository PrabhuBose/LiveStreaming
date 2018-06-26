package com.intigral.mobile.android.livestreaming.api;

import android.content.Context;

import com.intigral.mobile.android.livestreaming.interfaces.IServiceInvokerCallback;

/**
 * Created by Prabhu on 6/26/18 LiveStreaming.
 */
public class StreamingApis {

    public static void getTeamLineUpData(Context ctx, int REQUEST_TYPE, IServiceInvokerCallback callback) throws Exception {
        new InvokeVolleyService(ctx, REQUEST_TYPE, callback);
    }
}
