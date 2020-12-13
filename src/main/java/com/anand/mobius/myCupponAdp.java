package com.anand.mobius;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class myCupponAdp extends RecyclerView.Adapter<myCupponAdp.ViewHolder> {

    JsonArray cupponslist;
    Context context;
    List<String> slabs;

    public myCupponAdp(JsonArray cupponslist, Context context) {
        this.cupponslist = cupponslist;
        this.context = context;
        slabs = new ArrayList<>();
    }


    @NonNull
    @Override
    public myCupponAdp.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.cuppon_layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myCupponAdp.ViewHolder holder, int position) {
        final JsonObject object = cupponslist.getAsJsonArray().get(position).getAsJsonObject();


//        Picasso.with(context).load(object.get("").getAsString()).into(holder.imageView);
//        Picasso.with(context).load(object.get("").getAsString()).into(holder.imageView1);

        Log.d("TAG_APP",slabs.toString());

        holder.code_tv.setText(object.get("code").getAsString());
        holder.ribbon_msg_tv.setText(object.get("ribbon_msg").getAsString());


//        int percentage = (int) (cupponslist.getSlabs().get(position).getWageredMax()+cupponslist.getSlabs().get(position).getOtcMax());
//        int money = object.get("slabs").getAsJsonArray().get(position).getAsJsonObject().get("wagered_max").getAsInt()+object.get("slabs").getAsJsonArray().get(position).getAsJsonObject().get("otc_max").getAsInt();
//        holder.slab_otc_percent_tv.setText(String.valueOf(percentage)+ "%");
//        holder.slab_otc_max_tv.setText("\u20B9"+String.valueOf(money));

    }

    @Override
    public int getItemCount() {
        return cupponslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,imageView1;
        TextView slab_max_cash_tv,slab_mid_cash_tv,slab_min_cash_tv,slab_max_waged_tv,slab_mid_waged_tv,slab_min_waged_tv,
                slab_max_tv,slab_mid_tv,slab_min_tv,slab_otc_max_tv,slab_otc_percent_tv,code_tv,ribbon_msg_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cuppon_front_img);
            imageView1 = itemView.findViewById(R.id.cuppon_back_img);
            slab_max_cash_tv = itemView.findViewById(R.id.slab_max_cash_tv);
            slab_mid_cash_tv = itemView.findViewById(R.id.slab_mid_cash_tv);
            slab_min_cash_tv = itemView.findViewById(R.id.slab_min_cash_tv);
            slab_max_waged_tv = itemView.findViewById(R.id.slab_max_waged_tv);
            slab_mid_waged_tv = itemView.findViewById(R.id.slab_mid_waged_tv);
            slab_min_waged_tv = itemView.findViewById(R.id.slab_min_waged_tv);
            slab_max_tv = itemView.findViewById(R.id.slab_max_tv);
            slab_mid_tv = itemView.findViewById(R.id.slab_mid_tv);
            slab_min_tv = itemView.findViewById(R.id.slab_min_tv);
            slab_otc_max_tv = itemView.findViewById(R.id.slab_otc_max_tv);
            slab_otc_percent_tv = itemView.findViewById(R.id.slab_otc_percent_tv);
            code_tv = itemView.findViewById(R.id.code_tv);
            ribbon_msg_tv = itemView.findViewById(R.id.ribbon_msg_tv);
        }
    }
}
