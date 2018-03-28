package com.example.ana.seproject;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.example.ana.seproject.barcode.BarcodeCaptureActivity;

import static com.example.ana.seproject.MainActivity.EXTRA_MESSAGE;

public class ScanQR extends AppCompatActivity {
    private static final String LOG_TAG = ScanQR.class.getSimpleName();
    private static final int BARCODE_READER_REQUEST_CODE = 1;

    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        mResultTextView = (TextView) findViewById(R.id.result_textView);
        Button scanBarcodeButton = (Button) findViewById(R.id.scan_barcode_button);
        scanBarcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
                startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Point[] p = barcode.cornerPoints;
                    String result = barcode.displayValue;
                    switch (result){
                        case "Image 1": mResultTextView.setText(R.string.first_image);
                                        Intent intent = new Intent(this, Image.class);
                                        intent.putExtra(EXTRA_MESSAGE, "1");
                                        startActivity(intent);break;
                        case "Image 2": mResultTextView.setText(R.string.second_image);
                                        Intent intent2 = new Intent(this, Image.class);
                                        intent2.putExtra(EXTRA_MESSAGE, "2");
                                        startActivity(intent2);break;
                        case "Image 3": mResultTextView.setText(R.string.third_image);
                                        Intent intent3 = new Intent(this, Image.class);
                                        intent3.putExtra(EXTRA_MESSAGE, "3");
                                        startActivity(intent3);break;
                        case "Image 4": mResultTextView.setText(R.string.fourth_image);
                                        Intent intent4 = new Intent(this, Image.class);
                                        intent4.putExtra(EXTRA_MESSAGE, "4");
                                        startActivity(intent4);break;
                        case "Image 5": mResultTextView.setText(R.string.fifth_image);
                                        Intent intent5 = new Intent(this, Image.class);
                                        intent5.putExtra(EXTRA_MESSAGE, "5");
                                        startActivity(intent5);break;
                        case "Image 6": mResultTextView.setText(R.string.sixth_image);
                                        Intent intent6 = new Intent(this, Image.class);
                                        intent6.putExtra(EXTRA_MESSAGE, "6");
                                        startActivity(intent6);break;
                        case "Image 7": mResultTextView.setText(R.string.seventh_image);
                                        Intent intent7 = new Intent(this, Image.class);
                                        intent7.putExtra(EXTRA_MESSAGE, "7");
                                        startActivity(intent7);break;
                        case "Image 8": mResultTextView.setText(R.string.eigth_image);
                                        Intent intent8 = new Intent(this, Image.class);
                                        intent8.putExtra(EXTRA_MESSAGE, "8");
                                        startActivity(intent8);break;
                        case "Image 9": mResultTextView.setText(R.string.ninth_image);
                                        Intent intent9 = new Intent(this, Image.class);
                                        intent9.putExtra(EXTRA_MESSAGE, "9");
                                        startActivity(intent9);break;
                        case "Image 10": mResultTextView.setText(R.string.tenth_image);
                                        Intent intent10 = new Intent(this, Image.class);
                                        intent10.putExtra(EXTRA_MESSAGE, "10");
                                        startActivity(intent10);break;
                        default: mResultTextView.setText("Image not found"); break;
                    }
                } else mResultTextView.setText(R.string.no_barcode_captured);
            } else Log.e(LOG_TAG, String.format(getString(R.string.barcode_error_format),
                    CommonStatusCodes.getStatusCodeString(resultCode)));
        } else super.onActivityResult(requestCode, resultCode, data);
    }
}
