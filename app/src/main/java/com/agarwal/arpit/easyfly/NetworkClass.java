package com.agarwal.arpit.easyfly;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by arpitagarwal on 11/09/17.
 * Singleton class
 * Using volley library for asynchronously loading of data
 */

public class NetworkClass {

    //Declared static to be used in static getInstance method
    private static Context mContext;
    private static NetworkClass currentContext = null;

    private RequestQueue mRequestQueue;
    private static String TAG;


    public NetworkClass() {
    }

    /**
     * Initialising the context and getting single instance of network class
     *
     * @param context
     * @return
     */
    public static NetworkClass getInstance(Context context) {
        mContext = context;
        TAG = mContext.getClass().getSimpleName();

        if (currentContext == null) {
            currentContext = new NetworkClass();
        }
        return currentContext;
    }

    /**
     * Method for getting request queue
     *
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (null == mRequestQueue) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }

        return mRequestQueue;
    }

    /**
     * Add the request to queue while assigning different tags to different requests
     *
     * @param request
     * @param tag
     */
    public void addToRequestQueue(Request request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    /**
     * Cancel pending requests
     *
     * @param tag
     */
    public void cancelPendingRequest(String tag) {
        if (null != mRequestQueue) {
            mRequestQueue.cancelAll(tag);
        }
    }


    /**
     * Method for sending the request
     *
     * @param connectionURL
     * @param tag
     */
    public void sendRequest(String connectionURL, String tag, final ResponseListener responseListener) {
        JsonObjectRequest request = new JsonObjectRequest(connectionURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                responseListener.handleResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        NetworkClass.getInstance(mContext).addToRequestQueue(request, tag);

    }

}
