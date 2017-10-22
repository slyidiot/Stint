package com.inskade.stint.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.inskade.stint.R;
import com.inskade.stint.Stint;
import com.inskade.stint.database.AppDatabase;
import com.inskade.stint.database.model.Item;
import com.inskade.stint.database.model.ItemCollection;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class AddNewItemActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText costInput;
    private FloatingActionButton doneFAB;

    private String name;
    private float cost;
    private String[] names;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        findViews();
        names = getResources().getStringArray(R.array.students_array);

        doneFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameInput.getText().toString();
                cost = (Float) Float.parseFloat(costInput.getText().toString());
                if(name.equals(null) || String.valueOf(cost).equals(null)) {
                    Toast.makeText(AddNewItemActivity.this, "Enter Details!", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(name, cost);
                    Toast.makeText(AddNewItemActivity.this, "Added "+name, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddNewItemActivity.this, MainActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }
        });

    }

    private void findViews() {
        nameInput = (EditText) findViewById(R.id.name_input);
        costInput = (EditText) findViewById(R.id.single_cost_input);
        doneFAB = (FloatingActionButton) findViewById(R.id.done_adding);
    }

    private void insertData(String insertName, Float insertCost) {

        UUID uuid = UUID.randomUUID();
        String itemID = uuid.toString();

        ItemCollection itemCollection = ItemCollection.builder().setID(itemID).setName(insertName).setIndividualCost(insertCost).setDeliverStatus(false).build();
        Stint.getInstance().database.itemCollectionModel().addItemCollection(itemCollection);

        for(int i=0; i<names.length;i++) {
            Item item = Item.builder().setID(itemID+i+1).setCollectionID(itemID).setName(names[i]).setPaid(false).build();
            Stint.getInstance().database.itemModel().addItem(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddNewItemActivity.this, MainActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
