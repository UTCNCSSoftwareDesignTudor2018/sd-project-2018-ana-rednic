package com.example.ana.seproject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.seproject.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Called when the user presses the button "com.example.ana.seproject.ScanQR"
    public void scanQR(View view){
        Intent intent = new Intent(this, ScanQR.class);
        intent.putExtra(EXTRA_MESSAGE, "Scan QR code");
        startActivity(intent);
    }

    //Called when the user presses the button "Cauta"
    public void sendMessage(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        switch (message) {
            case "La seceris":
                Intent intent = new Intent(this, Image.class);
                intent.putExtra(EXTRA_MESSAGE, "1");
                startActivity(intent);
                break;
            case "Intrarea in parc":
                Intent intent1 = new Intent(this, Image.class);
                intent1.putExtra(EXTRA_MESSAGE, "2");
                startActivity(intent1);
                break;
            case "Periferie baimareana":
                Intent intent2 = new Intent(this, Image.class);
                intent2.putExtra(EXTRA_MESSAGE, "3");
                startActivity(intent2);
                break;
            case "Frumoasa spaniola":
                Intent intent3 = new Intent(this, Image.class);
                intent3.putExtra(EXTRA_MESSAGE, "4");
                startActivity(intent3);
                break;
            case "Iluminare de primavara":
                Intent intent4 = new Intent(this, Image.class);
                intent4.putExtra(EXTRA_MESSAGE, "5");
                startActivity(intent4);
                break;
            case "Tata":
                Intent intent5 = new Intent(this, Image.class);
                intent5.putExtra(EXTRA_MESSAGE, "6");
                startActivity(intent5);
                break;
            case "Odihna la amiaza":
                Intent intent6 = new Intent(this, Image.class);
                intent6.putExtra(EXTRA_MESSAGE, "7");
                startActivity(intent6);
                break;
            case "Butinarii":
                Intent intent7 = new Intent(this, Image.class);
                intent7.putExtra(EXTRA_MESSAGE, "8");
                startActivity(intent7);
                break;
            case "Peisaj baimarean":
                Intent intent8 = new Intent(this, Image.class);
                intent8.putExtra(EXTRA_MESSAGE, "9");
                startActivity(intent8);
                break;
            case "Peisaj de vara":
                Intent intent9 = new Intent(this, Image.class);
                intent9.putExtra(EXTRA_MESSAGE, "10");
                startActivity(intent9);
                break;
            default:
                Intent intent10 = new Intent(this, DisplayMessageActivity.class);
                intent10.putExtra(EXTRA_MESSAGE, "Nu s-a gasit niciun rezultat.");
                startActivity(intent10);
        }
        editText.clearComposingText();
    }
    public void image1(View view) {
        Intent intent = new Intent(this, Image.class);
        intent.putExtra(EXTRA_MESSAGE, "1");
        startActivity(intent);
    }
    public void image2(View view) {
        Intent intent = new Intent(this, Image.class);
        intent.putExtra(EXTRA_MESSAGE, "2");
        startActivity(intent);
    }
    public void image3(View view) {
        Intent intent = new Intent(this, Image.class);
        intent.putExtra(EXTRA_MESSAGE, "3");
        startActivity(intent);
    }
    public void image4(View view) {
        Intent intent = new Intent(this, Image.class);
        intent.putExtra(EXTRA_MESSAGE, "4");
        startActivity(intent);
    }
    public void image5(View view) {
        Intent intent = new Intent(this, Image.class);
        intent.putExtra(EXTRA_MESSAGE, "5");
        startActivity(intent);
    }
    public void image6(View view) {
        Intent intent = new Intent(this, Image.class);
        intent.putExtra(EXTRA_MESSAGE, "6");
        startActivity(intent);
    }
    public void image7(View view) {
        Intent intent = new Intent(this, Image.class);
        intent.putExtra(EXTRA_MESSAGE, "7");
        startActivity(intent);
    }
    public void image8(View view) {
        Intent intent = new Intent(this, Image.class);
        intent.putExtra(EXTRA_MESSAGE, "8");
        startActivity(intent);
    }
    public void image9(View view) {
        Intent intent = new Intent(this, Image.class);
        intent.putExtra(EXTRA_MESSAGE, "9");
        startActivity(intent);
    }
    public void image10(View view) {
        Intent intent = new Intent(this, Image.class);
        intent.putExtra(EXTRA_MESSAGE, "10");
        startActivity(intent);
    }
}
