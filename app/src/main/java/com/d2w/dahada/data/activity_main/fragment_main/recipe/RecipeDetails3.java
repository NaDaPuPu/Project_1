package com.d2w.dahada.data.activity_main.fragment_main.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.d2w.dahada.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecipeDetails3 extends AppCompatActivity {

    private ItemAdapter3 adapter;

    public RecipeDetails3() {
    }

    private ArrayList<RecipeItem3> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private ImageView rcpImage;
    private TextView rcpName, rcpEx, rcpEx2, rcpGram, rcpKcal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recipe_detail1);

        Intent intent = getIntent();

        rcpName = findViewById(R.id.rcpDetailname);
        rcpKcal = findViewById(R.id.rcpDetailkcal);
        rcpGram= findViewById(R.id.rcpDetailgram);
        rcpEx = findViewById(R.id.rcpDetailex1);
        rcpEx2 = findViewById(R.id.rcpDetailex2);

        int position = intent.getIntExtra("position", 0);
        Log.d("RecipeDetails3", "position : " + position);

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("RecipeItem3/" + position); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RecipeItem3 recipeItem = dataSnapshot.getValue(RecipeItem3.class);
                assert recipeItem != null;
                rcpName.setText(recipeItem.getRecipeName());
                rcpKcal.setText(recipeItem.getRecipeKcal() + "kcal");
                rcpGram.setText(recipeItem.getRecipeGram() + "g");
                rcpEx.setText(recipeItem.getRecipeEx1());
                rcpEx2.setText(recipeItem.getRecipeEx2());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("RecipeDetails3", String.valueOf(databaseError.toException()));
            }

        });

    }
}
