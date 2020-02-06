package myapp.csit.nonball.myfeeddata2;

import androidx.appcompat.app.AppCompatActivity;
import myapp.csit.nonball.myfeeddata2.Bean.ProductBean;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

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

//        if (mProductBean == null) {
//            finish();
//        }

        bindWidget();
        setupEventWidget();
        setupToolbar();
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


}


