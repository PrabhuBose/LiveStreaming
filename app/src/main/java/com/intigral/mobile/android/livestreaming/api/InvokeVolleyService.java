package ae.brandimpact.teleport.api;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ae.brandimpact.teleport.R;
import ae.brandimpact.teleport.helper.CustomProgressDialog;
import ae.brandimpact.teleport.helper.TelePortUtil;
import ae.brandimpact.teleport.interfaces.IServiceInvokerCallback;

public class InvokeVolleyService {
    boolean mIsPost;
    String mMethod;
    Map mRequestParameters;
    IServiceInvokerCallback mCallback;
    Context mCtx;
    String ServiceResponse;
    boolean progressDialog;
    int REQUEST_TYPE = 0;


    public InvokeVolleyService(Context ctx, int REQUEST_TYPE, String method,
                               HashMap requestParameters, IServiceInvokerCallback callback,  boolean progressDialog) {
        super();
        mCtx = ctx;
        mMethod = method;
        mRequestParameters = requestParameters;
        mCallback = callback;
        this.REQUEST_TYPE = REQUEST_TYPE;
        this.progressDialog = progressDialog;

        callService();
    }

    private void callService() {

        if (isOnline()) {

            if (progressDialog)
                CustomProgressDialog.showProgressDailog(mCtx);
            TelePortUtil.showLog("URL : ", getServiceUrl() +  mMethod);
            TelePortUtil.showLog("Parameters : ", "" + mRequestParameters);
            JSONObject jsonObject = new JSONObject();
            StringRequest postRequest = new StringRequest(REQUEST_TYPE,getServiceUrl() + mMethod,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            CustomProgressDialog.dismissProgressDailog();
                            TelePortUtil.showLog(" Json Response : ", "" + response);
                            ServiceResponse = response;
                            try {
                                mCallback.onRequestCompleted(ServiceResponse, mMethod);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            CustomProgressDialog.dismissProgressDailog();
                            error.printStackTrace();
                            TelePortUtil.showLog(" Json ERROR : ", String.valueOf(error));

                            try {
                                ServiceResponse = error.toString();
                                mCallback.onRequestCompleted(ServiceResponse, mMethod);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            ) {
                @Override
                protected Map<String,String> getParams(){

                    return mRequestParameters;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("Content-Type","application/x-www-form-urlencoded");
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
            String serviceUri = mCtx.getString(R.string.api_url);
//            AnotherUtil.showLog("", "API URL:" + serviceUri);
            return serviceUri;

        } else {
            TelePortUtil.showLog("in ServiceClient.java", "In method getServiceUrl, Context is null");
            return "";
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private JSONObject GetCustomErrorJSON(String errorMsg) {
        try {
            JSONObject errorJSON = new JSONObject();
            errorJSON.put("IsSuccess", false);
            errorJSON.put("Error", errorMsg);

            return errorJSON;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
