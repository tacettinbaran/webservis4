package com.baran.webservis4.Activities;

import android.app.ProgressDialog;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.baran.webservis4.Adapters.CommentsAdapter;
import com.baran.webservis4.Models.CommentsModel;
import com.baran.webservis4.R;
import com.baran.webservis4.RestApi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<CommentsModel> commnetsList;
    ProgressDialog progressDialog;
    CommentsAdapter commentsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        commentsGetir();
    }


    public void tanimla() {
        listView = findViewById(R.id.listview);
    }

    public void commentsGetir() {
        Call<List<CommentsModel>> istek = ManagerAll.getInstance().getComments();
        commnetsList = new ArrayList<>();
        createProgres();
        istek.enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(Call<List<CommentsModel>> call, Response<List<CommentsModel>> response) {
                if (response.isSuccessful()) {
                    commnetsList = response.body();
                    commentsAdapter = new CommentsAdapter(commnetsList, getApplicationContext(), MainActivity.this);
                    listView.setAdapter(commentsAdapter);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<CommentsModel>> call, Throwable t) {

                progressDialog.dismiss();
            }
        });
    }

    private void createProgres() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(" Bilgi Ekranı");
        progressDialog.setMessage("Lütfen Bekleyiniz");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


}