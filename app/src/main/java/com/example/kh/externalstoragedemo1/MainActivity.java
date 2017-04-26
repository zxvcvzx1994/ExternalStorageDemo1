package com.example.kh.externalstoragedemo1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kh.externalstoragedemo1.ViewHolder.viewholder_mainactivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    com.example.kh.externalstoragedemo1.ViewHolder.viewholder_mainactivity holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        intit();
    }

    private void intit() {
        holder = new viewholder_mainactivity();
        holder.etdata = (EditText) findViewById(R.id.etdata);
        holder.btnDelete = (Button) findViewById(R.id.btndelete);
        holder.btnShowData = (Button) findViewById(R.id.btnShowdata);
        holder.btnExternalStorage = (Button) findViewById(R.id.btnExternalStoreage);
        holder.txtdata = (TextView) findViewById(R.id.txtdata);

        holder.btnDelete.setOnClickListener(onclicklistenner);
        holder.btnShowData.setOnClickListener(onclicklistenner);
        holder.btnExternalStorage.setOnClickListener(onclicklistenner);
    }

    private static final String fileName="vinh.txt";
    private View.OnClickListener onclicklistenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btndelete:

                  Boolean delete=  com.example.kh.externalstoragedemo1.Module.externalStorageHelper.getInstance().deleteData(fileName);
                    if(delete)
                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnExternalStoreage:
                    try {
                        com.example.kh.externalstoragedemo1.Module.externalStorageHelper.getInstance().setData(fileName,holder.etdata.getText().toString().trim());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btnShowdata:
                    try {
                     String data=  com.example.kh.externalstoragedemo1.Module.externalStorageHelper.getInstance().getData(fileName);
                        holder.txtdata.setText(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

}
