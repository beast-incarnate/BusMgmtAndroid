package com.example.kunalsingh.busmgmt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunalsingh.busmgmt.api.services.APIService;
import com.example.kunalsingh.busmgmt.models.Student;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {

    public static final String TAG = "UpdateActivity";

    TextView tvName,tvFathersName,tvMothersName,tvAddress, tvContact,tvTotal,tvPresent,tvFees;
    EditText etName , etFathersName , etMothersName , etAddress , etContact ;
    Button btnChange , btnUpdate;
    boolean status = true;
    RelativeLayout rl;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent  = getIntent();

        final Student student = intent.getParcelableExtra("student");
        tvName = (TextView)findViewById(R.id.name2);
        tvName.setText(student.getName());
        tvFathersName = (TextView)findViewById(R.id.fathers_name2);
        tvFathersName.setText(student.getFathersName());
        tvMothersName = (TextView)findViewById(R.id.mothers_name2);
        tvMothersName.setText(student.getMothersName());
        tvAddress = (TextView)findViewById(R.id.address2);
        tvAddress.setText(student.getAddress());
        tvContact = (TextView)findViewById(R.id.contact2);
        tvContact.setText(student.getContact());
        tvTotal = (TextView)findViewById(R.id.total2);
        tvTotal.setText(String.valueOf(student.getTotal()));
        tvPresent = (TextView)findViewById(R.id.present_two);
        tvPresent.setText(String.valueOf(student.getPresent()));
        tvFees = (TextView)findViewById(R.id.fees2);
        tvFees.setText(String.valueOf(student.isFees()));

        etName  = (EditText)findViewById(R.id.et_name);
        etName.setText(tvName.getText());
        etFathersName = (EditText) findViewById(R.id.et_fathers_name);
        etFathersName.setText(tvFathersName.getText());
        etMothersName = (EditText)findViewById(R.id.et_mothers_name);
        etMothersName.setText(tvMothersName.getText());
        etAddress = (EditText)findViewById(R.id.et_address);
        etAddress.setText(tvAddress.getText());
        etContact = (EditText)findViewById(R.id.et_contact);
        etContact.setText(tvContact.getText());


        rl = (RelativeLayout)findViewById(R.id.rl_show);
        ll = (LinearLayout)findViewById(R.id.ll_show);

        btnChange = (Button)findViewById(R.id.btn_change);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status){
                    status = false;
                    btnChange.setText("Cancel Update");
                    ll.setVisibility(View.VISIBLE);
                    rl.setVisibility(View.GONE);



                }else{
                    btnChange.setText("Update");
                    status = true;
                    ll.setVisibility(View.GONE);
                    rl.setVisibility(View.VISIBLE);
                }

            }
        });

        btnUpdate = (Button)findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = String.valueOf(etName.getText());
                String fathersName = String.valueOf(etFathersName.getText());
                String mothersName = String.valueOf(etMothersName.getText());
                String address = String.valueOf(etAddress.getText());
                String contact = String.valueOf(etContact.getText());

                if(!name.equals("") && !fathersName.equals("") && !mothersName.equals("") && !address.equals("")
                        && !address.equals("") && !contact.equals("")){

                            updateStudentDetails(student.getAccess_token() , name , fathersName , mothersName , address , contact );


                }else{
                    Toast.makeText(UpdateActivity.this, "Wrong Input", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void updateStudentDetails(String access_token , String name , String fathersName , String mothersName ,
                                     String address , String contact){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.102:3000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Student> call = service.updateStudent(access_token , name , fathersName , mothersName , address , contact);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Student student = response.body();

                btnChange.setText("Update");
                status = true;
                ll.setVisibility(View.GONE);
                rl.setVisibility(View.VISIBLE);

                tvName.setText(student.getName());
                tvFathersName.setText(student.getFathersName());
                tvMothersName.setText(student.getMothersName());
                tvAddress.setText(student.getAddress());
                tvContact.setText(student.getContact());

            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {

                Toast.makeText(UpdateActivity.this, "Error in Updation", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
