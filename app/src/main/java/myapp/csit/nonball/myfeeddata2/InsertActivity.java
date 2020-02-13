package myapp.csit.nonball.myfeeddata2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import myapp.csit.nonball.myfeeddata2.Bean.ProductBean;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import static myapp.csit.nonball.myfeeddata2.Bean.ProductBean.BASE_URL;

public class InsertActivity extends AppCompatActivity {
    private ProductBean mProductBean;

    //    GUI
    private EditText mInputProductidEDT;
    private EditText mInputNameEDT;
    private EditText mInputDetailEDT;
    private EditText mInputImageProductEDT;
    private EditText mInputPriceEDT;
    private Button mSaveButton;
    private ImageView mSelectImageIMV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

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
        mSaveButton = (Button) findViewById(R.id.saveBtn);
        mSelectImageIMV = (ImageView) findViewById(R.id.selectImageIMV);
    }
    //-->130263
    private void setupEventWidget() {
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InsertAsyn().execute(BASE_URL + "insert.php");
            }
        });
        mSelectImageIMV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.th/search?dcr=0&tbm=isch&q=iphone+x&spell=1&sa=X&ved=0ahUKEwia3fO5t-jXAhWDsI8KHQAPBIEQvwUIbigA&biw=1366&bih=651&dpr=1"));
                startActivity(_intent);
            }
        });

    }
    //<--130263



    private class InsertAsyn extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {

                OkHttpClient _okHttpClient = new OkHttpClient();
                @SuppressLint("WrongThread") RequestBody _requestBody = new FormBody.Builder()
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
                Toast.makeText(InsertActivity.this, "insert successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(InsertActivity.this, "insert failure",Toast.LENGTH_SHORT).show();
            }
        }

    }

    //<--130263
}
