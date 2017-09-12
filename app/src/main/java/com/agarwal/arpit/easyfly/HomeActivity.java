package com.agarwal.arpit.easyfly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.agarwal.arpit.easyfly.flighdataitemclasses.FlightItemDao;
import com.agarwal.arpit.easyfly.flighdataitemclasses.ParseFlightDataResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements ResponseListener {

    private static final String JSON_URL = "http://www.mocky.io/v2/5979c6731100001e039edcb3";
    private static final String JSON_URL_TAG = "REQUEST_TAG";
    private ResponseListener responseListener = this;
    private RecyclerAdapter recyclerAdapter;
    private ParseFlightDataResponse parseDataResponse;
    private List<FlightItemDao> dataList = new ArrayList<>();
    private NetworkClass ob;

    @BindView(R.id.content_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.action_button)
    Button actionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setUpController();
    }

    private void setUpController() {
        recyclerAdapter = new RecyclerAdapter(dataList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Data Refreshed", Toast.LENGTH_SHORT).show();
                sendFlightDataRequest();
            }
        });

        sendFlightDataRequest();
    }


    /**
     * Method for sending the request
     */
    private void sendFlightDataRequest() {
        ob = NetworkClass.getInstance(this);
        ob.sendRequest(JSON_URL, JSON_URL_TAG, responseListener);
    }

    /**
     * Overridden Method of ResponseListener interface for handling the parsed response and populating data
     *
     * @param response
     */
    public void handleResponse(JSONObject response) {
        parseDataResponse = new ParseFlightDataResponse();
        try {
            parseDataResponse.parseResponse(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        dataList.clear();
        dataList.addAll(parseDataResponse.getFlighDataItemsList());

        recyclerAdapter.notifyDataSetChanged();

    }

}
