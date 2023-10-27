package com.example.a112_1_lab4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_meal;
    private Button btn_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_meal = findViewById(R.id.tv_meal);
        btn_select = findViewById(R.id.btn_select);
        btn_select.setOnClickListener(view -> {
            mStartForResult.launch(
                    new Intent(this, MainActivity2.class)
            );
        });
    }

    private final ActivityResultLauncher<Intent> mStartForResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
                Intent intent = result.getData();

                if(intent != null && intent.getExtras() != null){
                    Bundle b = intent.getExtras();
                    String str1 = b.getString("drink");
                    String str2 = b.getString("sugar");
                    String str3 = b.getString("ice");
                    tv_meal.setText(String.format("飲料: %s\n\n甜度: %s\n\n冰塊: %s",str1,str2,str3));
                }
            });
}