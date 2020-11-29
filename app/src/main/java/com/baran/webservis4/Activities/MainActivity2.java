package com.baran.webservis4.Activities;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.baran.webservis4.Models.ResultModel;
import com.baran.webservis4.R;
import com.baran.webservis4.RestApi.ManagerAll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    String id, postId;
    TextView postIdEdittext, idEdittext, nameEdittext, emailEdittext, bodyEdittext;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        parametreAl();
        tanimla();
        resultsGetir(postId, id);
    }

    public void tanimla() {
        postIdEdittext = findViewById(R.id.postIdEdittext);
        idEdittext = findViewById(R.id.idEdittext);
        nameEdittext = findViewById(R.id.nameEdittext);
        emailEdittext = findViewById(R.id.emailEdittext);
        bodyEdittext = findViewById(R.id.bodyEdittext);
    }

    public void parametreAl() {
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("idm");
        postId = bundle.getString("post_id");
    }

    public void resultsGetir(String postId, String id) {
        Call<List<ResultModel>> istek = ManagerAll.getInstance().getResults(postId, id);
        createProgres();
        istek.enqueue(new Callback<List<ResultModel>>() {
            @Override
            public void onResponse(Call<List<ResultModel>> call, Response<List<ResultModel>> response) {
                if (response.isSuccessful()) {
                    atamalar(response);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<ResultModel>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void atamalar(final Response<List<ResultModel>> response) {
        postIdEdittext.setText(postIdEdittext.getText() + " " + response.body().get(0).getPostId());
        idEdittext.setText(idEdittext.getText() + " " + response.body().get(0).getId());
        nameEdittext.setText(nameEdittext.getText() + " " + response.body().get(0).getName());
        emailEdittext.setText(emailEdittext.getText() + " " + response.body().get(0).getEmail());
        bodyEdittext.setText(bodyEdittext.getText() + " " + response.body().get(0).getBody());
    }

    private void createProgres() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(" Bilgi Ekranı");
        progressDialog.setMessage("Lütfen Bekleyiniz");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}