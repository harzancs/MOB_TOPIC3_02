package myapp.csit.nonball.myfeeddata2;

import androidx.appcompat.app.AppCompatActivity;
import myapp.csit.nonball.myfeeddata2.Bean.ProductBean;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static myapp.csit.nonball.myfeeddata2.Bean.ProductBean.BASE_URL;

public class EditActivity extends AppCompatActivity {

    private ProductBean mProductBean;

    //    GUI
    private EditText mInputProductidEDT, mInputNameEDT, mInputDetailEDT, mInputImageProductEDT, mInputPriceEDT;
    private Button mSaveButton, mDeleteButton;
    private ImageView mSelectImageIMV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mProductBean = getIntent().getParcelableExtra("product_bean");

        if (mProductBean == null) {
            finish();
        }

        bindWidget();
        setupEventWidget();
//        setupToolbar();
    }

    private void bindWidget() {
        mInputProductidEDT = (EditText) findViewById(R.id.inputProductIdEDT);
        mInputNameEDT = (EditText) findViewById(R.id.inputNameEDT);
        mInputDetailEDT = (EditText) findViewById(R.id.inputDetailEDT);
        mInputPriceEDT = (EditText) findViewById(R.id.inputPriceEDT);
        mInputImageProductEDT = (EditText) findViewById(R.id.inputImageProductEDT);
        mSaveButton = findViewById(R.id.saveBtn);
        mDeleteButton = findViewById(R.id.delete);
        mSelectImageIMV = findViewById(R.id.selectImageIMV);
    }

    private void setupEventWidget() {
        mInputProductidEDT.setText(mProductBean.getId_product());
        mInputNameEDT.setText(mProductBean.getName());
        mInputDetailEDT.setText(mProductBean.getDetail());
        mInputPriceEDT.setText(mProductBean.getPrice());
        mInputImageProductEDT.setText(mProductBean.getImage_url());

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InsertAsyn().execute(BASE_URL + "update.php");
            }
        });
        mSelectImageIMV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Edit Database");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class InsertAsyn extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient _okHttpClient = new OkHttpClient();
                @SuppressLint("WrongThread") RequestBody _requestBody = new FormBody.Builder()
                        .add("id", mProductBean.getId())
                        .add("id_product", mInputProductidEDT.getText().toString())
                        .add("name", mInputNameEDT.getText().toString())
                        .add("detail", mInputDetailEDT.getText().toString())
                        .add("price", mInputPriceEDT.getText().toString())
                        .add("img_url", mInputImageProductEDT.getText().toString())
                        .build();
                Request _request = new Request.Builder().url(strings[0]).post(_requestBody).build();
                _okHttpClient.newCall(_request).execute();
                return "successfully";
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                Toast.makeText(EditActivity.this, "update successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(EditActivity.this, "update failure", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private class DeleteAsyn extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient _okHttpClient = new OkHttpClient();
                RequestBody _requestBody = new FormBody.Builder()
                        .add("id", mProductBean.getId())
                        .build();
                Request _request = new Request.Builder().url(strings[0]).post(_requestBody).build();
                _okHttpClient.newCall(_request).execute();
                return "successfully";
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                Toast.makeText(EditActivity.this, "delete successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(EditActivity.this, "delete failure", Toast.LENGTH_SHORT).show();
            }

        }
    }
    //<--130263

}



