package com.agarwal.arpit.easyfly;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agarwal.arpit.easyfly.flighdataitemclasses.FareItemDao;
import com.agarwal.arpit.easyfly.flighdataitemclasses.FlightItemDao;

import java.util.List;

/**
 * Created by arpitagarwal on 12/09/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<FlightItemDao> mDataList;


    public RecyclerAdapter(List<FlightItemDao> dataList) {
        mDataList = dataList;
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
        holder.ori.setText(data.getOriginCode());
        holder.dest.setText(data.getDestinationCode());
        holder.dept_time.setText(data.getDepartureTime());
        holder.arr_time.setText(data.getArrivalTime());
        holder.provider_fare.setText("");
        for (FareItemDao itemDao : data.getFares()) {
            holder.provider_fare.append(itemDao.getProviderId() + " - " + itemDao.getFare() + " , ");
        }
        holder.airline.setText(data.getAirlineCode());
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
}
