package com.example.recyclerview_json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i=getIntent();
        String a=i.getStringExtra("URL");
        String b=i.getStringExtra("CREATOR");
        int bj=i.getIntExtra("LIKES",0);
        ImageView image=(ImageView) findViewById(R.id.detailImage);
        TextView creator=(TextView) findViewById(R.id.detailCreator);
        TextView likes=(TextView) findViewById(R.id.detailLikes);
        Picasso.get().load(a).fit().centerInside().into(image);
        creator.setText(b);
        likes.setText("Likes: "+bj);
    }
}
