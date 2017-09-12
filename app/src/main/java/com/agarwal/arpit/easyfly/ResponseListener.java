package com.agarwal.arpit.easyfly;

import org.json.JSONObject;

/**
 * Created by arpitagarwal on 12/09/17.
 * Interface for handling the acquired response
 */

public interface ResponseListener {

    void handleResponse(JSONObject response);
}
