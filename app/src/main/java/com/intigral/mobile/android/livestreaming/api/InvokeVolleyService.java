package com.intigral.mobile.android.livestreaming.api;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.intigral.mobile.android.livestreaming.R;
import com.intigral.mobile.android.livestreaming.interfaces.IServiceInvokerCallback;
import com.intigral.mobile.android.livestreaming.utils.StreamingUtils;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prabhu on 6/26/18 LiveStreaming.
 */
public class InvokeVolleyService {
    private IServiceInvokerCallback mCallback;
    private Context mCtx;
    private String ServiceResponse;
    private int REQUEST_TYPE = 0;


    public InvokeVolleyService(Context ctx, int REQUEST_TYPE, IServiceInvokerCallback callback) {
        super();
        mCtx = ctx;
        mCallback = callback;
        this.REQUEST_TYPE = REQUEST_TYPE;

        callService();
    }

    private void callService() {

        if (isOnline()) {
            StreamingUtils.showLog("URL : ", getServiceUrl());
            StringRequest postRequest = new StringRequest(REQUEST_TYPE, getServiceUrl(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String utf8_response;

                            try {
                                byte ptext[] = response.getBytes("ISO-8859-1");
                                utf8_response = new String(ptext, "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                utf8_response = response;
                                e.printStackTrace();
                            }


                            ServiceResponse = utf8_response;
                            try {
                                mCallback.onRequestCompleted(ServiceResponse);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            StreamingUtils.showLog(" Json ERROR : ", String.valueOf(error));

                            try {
                                ServiceResponse = error.toString();
                                mCallback.onRequestCompleted(ServiceResponse);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {

                    return null;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(mCtx).add(postRequest);

        } else {
            android.support.v7.app.AlertDialog.Builder myAlertDialog = new android.support.v7.app.AlertDialog.Builder(mCtx);
            myAlertDialog.setTitle("Error");
            myAlertDialog.setMessage("Internet Connection is not available");
            myAlertDialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            dialog.cancel();
                        }
                    });
            myAlertDialog.show();
        }
    }

    private String getServiceUrl() {
        if (mCtx != null) {
            return mCtx.getString(R.string.api_url);

        } else {
            StreamingUtils.showLog("in ServiceClient.java", "In method getServiceUrl, Context is null");
            return "";
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}
