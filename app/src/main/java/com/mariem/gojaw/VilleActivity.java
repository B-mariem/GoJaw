package com.mariem.gojaw;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mariem.gojaw.adapters.VillesAdapter;
import com.mariem.gojaw.models.Ville;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VilleActivity extends AppCompatActivity {
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;


    RecyclerView rvVille;
    private VillesAdapter VillesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ville);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        fetchVille();
    }

    private void fetchVille() {
        Intent intent = getIntent();
        String gouv = intent.getStringExtra("GOUV");


        HashMap<String, String> map = new HashMap<>();
        map.put("gouvernorat", gouv);

        Call<List<Ville>> call = retrofitInterface.getAllVille(map);
        call.enqueue(new Callback<List<Ville>>() {
            @Override
            public void onResponse(Call<List<Ville>> call, Response<List<Ville>> response) {
                if (response.code() == 200) {
                    showData(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Ville>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showData(List<Ville> body) {
        rvVille = findViewById(R.id.rv_villes);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rvVille.setLayoutManager(manager);

        VillesAdapter = new VillesAdapter(this, body);
        rvVille.setAdapter(VillesAdapter);

    }

}
