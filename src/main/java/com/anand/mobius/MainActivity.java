package com.anand.mobius;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.anand.mobius.utils.ConnectionChecking;
import com.anand.mobius.utils.HttpHelpers;
import com.google.gson.JsonArray;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ConnectionChecking checking;
    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        checking = new ConnectionChecking();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        if (checking.isConnectingToInternet(MainActivity.this)) {
            final SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
            dialog.setTitle("Loading...");
            dialog.setCancelable(false);
            dialog.show();

            JsonArray jsonArray = new JsonArray();


            Retrofit retrofit = HttpHelpers.getInstance();
            apiInterface = retrofit.create(ApiInterface.class);


            Call<JsonArray> grades = apiInterface.getcupons();
            grades.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                    Log.d("TAG_RESPONSE_CODE", "code no : " + response.code());
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            //MAIN COLLEGES

//                            Log.d("TAG_APP", response.body().getAsJsonArray().toString());


                            recyclerView.setAdapter(new myCupponAdp(response.body().getAsJsonArray(), MainActivity.this));

                            dialog.dismiss();

                        } else {
                            //NULL BODY
                            dialog.setTitle("Something Went Wrong 1");
                            dialog.setConfirmText("Done");
                            dialog.setConfirmClickListener(null);
                            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            Log.d("TAG_APP", "NULL BODY");
                        }
                    } else {
                        //RESPONSE IS NOT SUCCESSFUL
                        //NULL BODY
                        dialog.setTitle("Something Went Wrong 2");
                        dialog.setConfirmText("Done");
                        dialog.setConfirmClickListener(null);
                        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    }
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    Log.d("GRADE_DETAILS", t.toString());
                    t.getStackTrace();
                }
            });
        } else {
            SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
            dialog.setCancelable(false);
            dialog.setTitle("Network Error");
            dialog.setContentText("Please check Your Network Connection");
            dialog.setConfirmText("OK");
            dialog.setConfirmClickListener(null);
            dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
            dialog.show();
        }

    }
}