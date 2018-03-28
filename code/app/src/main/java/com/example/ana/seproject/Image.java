package com.example.ana.seproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Image extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        String message = getIntent().getStringExtra("com.example.seproject.MESSAGE");
        TextView text = (TextView) findViewById(R.id.textView3);
        TextView bigText = (TextView) findViewById(R.id.textView4);
        ImageView image = (ImageView) findViewById(R.id.imageView3);
        switch (message) {
            case "1":
                text.setText(R.string.first_image);
                bigText.setText(R.string.descriere1);
                image.setImageDrawable(getResources().getDrawable(R.drawable.image3));
                break;
            case "2":
                text.setText(R.string.second_image);
                bigText.setText(R.string.descriere2);
                image.setImageDrawable(getResources().getDrawable(R.drawable.image4));
                break;
            case "3":
                text.setText(R.string.third_image);
                bigText.setText(R.string.descriere3);
                image.setImageDrawable(getResources().getDrawable(R.drawable.image5));
                break;
            case "4":
                text.setText(R.string.fourth_image);
                bigText.setText(R.string.descriere4);
                image.setImageDrawable(getResources().getDrawable(R.drawable.image6));
                break;
            case "5":
                text.setText(R.string.fifth_image);
                bigText.setText(R.string.descriere5);
                image.setImageDrawable(getResources().getDrawable(R.drawable.image7));
                break;
            case "6":
                text.setText(R.string.sixth_image);
                bigText.setText(R.string.descriere6);
                image.setImageDrawable(getResources().getDrawable(R.drawable.image8));
                break;
            case "7":
                text.setText(R.string.seventh_image);
                bigText.setText(R.string.descriere7);
                image.setImageDrawable(getResources().getDrawable(R.drawable.image9));
                break;
            case "8":
                text.setText(R.string.eigth_image);
                bigText.setText(R.string.descriere8);
                image.setImageDrawable(getResources().getDrawable(R.drawable.image10));
                break;
            case "9":
                text.setText(R.string.ninth_image);
                bigText.setText(R.string.descriere9);
                image.setImageDrawable(getResources().getDrawable(R.drawable.image11));
                break;
            case "10":
                text.setText(R.string.tenth_image);
                bigText.setText(R.string.descriere10);
                image.setImageDrawable(getResources().getDrawable(R.drawable.image13));
                break;
        }
    }
}
