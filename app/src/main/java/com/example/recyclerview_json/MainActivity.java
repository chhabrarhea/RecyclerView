package com.example.recyclerview_json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener{
private RecyclerView recyclerView;
private RequestQueue requestQueue;
private ExampleAdapter exampleAdapter;
private ArrayList<example_item> example_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        example_items=new ArrayList<>();
        requestQueue= Volley.newRequestQueue(this);
        parseJSON();


    }
    private void parseJSON()
    {
        String url="https://pixabay.com/api/?key=""&q=yellow+flowers&image_type=photo&pretty=true";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);
                        String creatorName = hit.getString("user");
                        String imageUrl = hit.getString("webformatURL");
                        int likeCount = hit.getInt("likes");
                        example_item newItem=new example_item(imageUrl, creatorName, likeCount);
                        example_items.add(newItem);
                    }
                    ExampleAdapter exampleAdapter=new ExampleAdapter(MainActivity.this, example_items);
                    recyclerView.setAdapter(exampleAdapter);
                    exampleAdapter.setOnItemClickListener(MainActivity.this);

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemClick(int position) {

            Intent detailIntent = new Intent(this, Detail.class);
            example_item clickedItem = example_items.get(position);
            detailIntent.putExtra("URL", clickedItem.getUrl());
            detailIntent.putExtra("CREATOR", clickedItem.getCreator());
            detailIntent.putExtra("LIKES", clickedItem.getLikes());
            startActivity(detailIntent);
        }
    }

