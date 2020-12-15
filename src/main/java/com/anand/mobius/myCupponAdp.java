package com.anand.mobius;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class myCupponAdp extends RecyclerView.Adapter<myCupponAdp.ViewHolder> {

    JsonArray cupponslist;
    Context context;
    List<JsonObject> slabs;

    public myCupponAdp(JsonArray cupponslist, Context context) {
        this.cupponslist = cupponslist;
        this.context = context;
        slabs = new ArrayList<>();
    }


    @NonNull
    @Override
    public myCupponAdp.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.cuppon_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myCupponAdp.ViewHolder holder, int position) {
        final JsonObject object = cupponslist.getAsJsonArray().get(position).getAsJsonObject();
        final JsonObject slabobject = object.get("slabs").getAsJsonArray().get(0).getAsJsonObject();

        slabs.add(slabobject);

        for (int i = 0; i == slabs.size(); i++) {

        }


//        Picasso.with(context).load(object.get("").getAsString()).into(holder.imageView);
//        Picasso.with(context).load(object.get("").getAsString()).into(holder.imageView1);

        Log.d("TAG_APP", slabobject.toString());

        holder.code_tv.setText(object.get("code").getAsString());
        holder.ribbon_msg_tv.setText(object.get("ribbon_msg").getAsString());


        int percentage = (int) (slabobject.get("wagered_percent").getAsInt() + slabobject.get("otc_percent").getAsInt());

        int money = slabobject.get("wagered_max").getAsInt() + slabobject.get("otc_max").getAsInt();

        holder.slab_otc_percent_tv.setText((String.valueOf(percentage) + "%"));
        holder.slab_otc_max_tv.setText(("\u20B9" + String.valueOf(money)));
        holder.slab_min_tv.setText(("<" + slabobject.get("min").getAsString()));
        holder.slab_mid_tv.setText((">=" + slabobject.get("min").getAsString() + " & <" + slabobject.get("max").getAsString()));
        holder.slab_max_tv.setText((">=" + slabobject.get("max").getAsString()));
        holder.slab_max_waged_tv.setText((slabobject.get("wagered_percent").getAsString() + "% (Max." + slabobject.get("wagered_max").getAsString() + ")"));
        holder.slab_max_cash_tv.setText((slabobject.get("otc_percent").getAsString() + "% (Max." + slabobject.get("otc_max").getAsString() + ")"));
        holder.slab_min_dep_tv.setText("\u20B9" + slabobject.get("min").getAsString());

        String numerator = object.get("wager_to_release_ratio_numerator").getAsString();
        String denamonitor = object.get("wager_to_release_ratio_denominator").getAsString();
        String experiy = object.get("wager_bonus_expiry").getAsString();
        String wagerReleasen = holder.getColoredSpanned(numerator, "#ffcc00");
        String wagerReleased = holder.getColoredSpanned(denamonitor, "#ffcc00");
        String wagerbonous = holder.getColoredSpanned(experiy, "#ffcc00");


        holder.wager_to_release_tv.setText(("For every \u20B9" + (Html.fromHtml(wagerReleasen)) + " bet \u20B9" + Html.fromHtml(wagerReleased) + " will be released from bonus amount"));
        holder.wager_bonus_expiry_tv.setText(("Bonus expiry " + Html.fromHtml(wagerbonous) + " days from the issue"));
//        holder.date_tv.setText(object.get("valid_until").getAsString());

        holder.details_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.details_layout.getVisibility() == View.GONE && holder.details_layout2.getVisibility() == View.GONE) {
                    holder.details_layout.setVisibility(View.VISIBLE);
                    holder.details_layout2.setVisibility(View.VISIBLE);
                    holder.details_tv.setText("Hide Details");
                } else {
                    holder.details_layout.setVisibility(View.GONE);
                    holder.details_layout2.setVisibility(View.GONE);
                    holder.details_tv.setText("Show Details");
                }
            }
        });

        Log.d("TAG_APP", slabs + "");

    }

    @Override
    public int getItemCount() {
        return cupponslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, imageView1;
        TextView slab_max_cash_tv, slab_mid_cash_tv, slab_min_cash_tv, slab_max_waged_tv, slab_mid_waged_tv, slab_min_waged_tv,
                slab_max_tv, slab_mid_tv, slab_min_tv, slab_otc_max_tv, slab_otc_percent_tv, code_tv, ribbon_msg_tv, wager_to_release_tv,
                wager_bonus_expiry_tv, details_tv, slab_min_dep_tv, date_tv;
        LinearLayout details_layout, details_layout2;

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
            wager_to_release_tv = itemView.findViewById(R.id.wager_to_release_tv);
            wager_bonus_expiry_tv = itemView.findViewById(R.id.wager_bonus_expiry_tv);
            details_tv = itemView.findViewById(R.id.details_tv);
            slab_min_dep_tv = itemView.findViewById(R.id.slab_min_dep_tv);
            date_tv = itemView.findViewById(R.id.date_tv);
            details_layout = itemView.findViewById(R.id.details_layout);
            details_layout2 = itemView.findViewById(R.id.details_layout2);

        }

        private String getColoredSpanned(String text, String color) {
            String input = "<font color=" + color + ">" + text + "</font>";
            return input;
        }
    }
}
