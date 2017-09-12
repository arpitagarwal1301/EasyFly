package com.agarwal.arpit.easyfly;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agarwal.arpit.easyfly.flighdataitemclasses.FareItemDao;
import com.agarwal.arpit.easyfly.flighdataitemclasses.FlightItemDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by arpitagarwal on 12/09/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<FlightItemDao> mDataList;
    private Map<String, String> aLMap = new HashMap<>();
    private Map<String, String> aPMap = new HashMap<>();
    private Map<String, String> pLMap = new HashMap<>();


    public RecyclerAdapter(List<FlightItemDao> dataList, Map<String, String> airlineCodeMap, Map<String, String> airportCodeMap, Map<String, String> providersList) {
        mDataList = dataList;
        aLMap = airlineCodeMap;
        aPMap = airportCodeMap;
        pLMap = providersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_row_item_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FlightItemDao data = mDataList.get(position);
        holder.ori.setText(aPMap.get(data.getOriginCode()));
        holder.dest.setText(aPMap.get(data.getDestinationCode()));
        holder.dept_time.setText(getTimeStamp(data.getDepartureTime()));
        holder.arr_time.setText(getTimeStamp(data.getArrivalTime()));
        holder.provider_fare.setText("");
        for (FareItemDao itemDao : data.getFares()) {
            holder.provider_fare.append(pLMap.get(itemDao.getProviderId()) + " - " + itemDao.getFare() + " , ");
        }
        holder.airline.setText(aLMap.get(data.getAirlineCode()));
        holder.className.setText(data.getClassName());

    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ori;
        TextView dest;
        TextView dept_time;
        TextView arr_time;
        TextView provider_fare;
        TextView airline;
        TextView className;

        public MyViewHolder(View itemView) {
            super(itemView);

            ori = itemView.findViewById(R.id.ori);
            dest = itemView.findViewById(R.id.dest);
            dept_time = itemView.findViewById(R.id.dep_time);
            arr_time = itemView.findViewById(R.id.arr_time);
            provider_fare = itemView.findViewById(R.id.provider_fare);
            airline = itemView.findViewById(R.id.airline);
            className = itemView.findViewById(R.id.className);
        }
    }

    //Converting epoch time to desirable format
    private String getTimeStamp(String timeInMilli) {
        if (!TextUtils.isEmpty(timeInMilli)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US);
            return simpleDateFormat.format(new Date(Long.parseLong(timeInMilli)));
        } else {
            return "";
        }
    }
}
