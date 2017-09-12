package com.agarwal.arpit.easyfly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.agarwal.arpit.easyfly.flighdataitemclasses.FlightItemDao;
import com.agarwal.arpit.easyfly.flighdataitemclasses.ParseFlightDataResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements ResponseListener, View.OnClickListener {

    private static final String JSON_URL = "http://www.mocky.io/v2/5979c6731100001e039edcb3";
    private static final String JSON_URL_TAG = "REQUEST_TAG";
    private ResponseListener responseListener = this;
    private RecyclerAdapter recyclerAdapter;
    private ParseFlightDataResponse parseDataResponse;
    private List<FlightItemDao> dataList = new ArrayList<>();
    private NetworkClass ob;
    private Boolean isDepTimeAscending = true;
    private Boolean isArrTimeAscending = true;
    private Boolean isMinFareAscending = true;

    private final String DEP_TIME_SORT = "DEPARTURE_TIME";
    private final String ARR_TIME_SORT = "ARRIVAL_TIME";
    private final String MIN_FARE_SORT = "MIN_FARE";

    @BindView(R.id.content_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.dep_time_sort_btn)
    Button depTimeSort;

    @BindView(R.id.arr_time_sort_btn)
    Button arrTimeSort;

    @BindView(R.id.min_fare_sort_btn)
    Button minFareSort;

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

        //set click listeners
        depTimeSort.setOnClickListener(this);
        arrTimeSort.setOnClickListener(this);
        minFareSort.setOnClickListener(this);

        //Send request to populate initial data
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


    private void doSorting(String sortType, boolean isAscending) {
        switch (sortType) {
            case DEP_TIME_SORT:
                if (isAscending) {
                    Collections.sort(dataList, FlightItemDao.COMPARATOR_DEP_TIME_INC);
                } else {
                    Collections.sort(dataList, FlightItemDao.COMPARATOR_DEP_TIME_DEC);
                }
                recyclerAdapter.notifyDataSetChanged();
                break;
            case ARR_TIME_SORT:
                if (isAscending) {
                    Collections.sort(dataList, FlightItemDao.COMPARATOR_ARR_TIME_INC);
                } else {
                    Collections.sort(dataList, FlightItemDao.COMPARATOR_ARR_TIME_DEC);
                }
                recyclerAdapter.notifyDataSetChanged();
                break;
            case MIN_FARE_SORT:
                if (isAscending) {
                    Collections.sort(dataList, FlightItemDao.COMPARATOR_MIN_FARE_INC);
                } else {
                    Collections.sort(dataList, FlightItemDao.COMPARATOR_MIN_FARE_DEC);
                }
                recyclerAdapter.notifyDataSetChanged();
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dep_time_sort_btn:
                doSorting(DEP_TIME_SORT, isDepTimeAscending);
                isDepTimeAscending = !isDepTimeAscending;
                break;
            case R.id.arr_time_sort_btn:
                doSorting(ARR_TIME_SORT, isArrTimeAscending);
                isArrTimeAscending = !isArrTimeAscending;
                break;
            case R.id.min_fare_sort_btn:
                doSorting(MIN_FARE_SORT, isMinFareAscending);
                isMinFareAscending = !isMinFareAscending;
                break;
        }

    }
}
