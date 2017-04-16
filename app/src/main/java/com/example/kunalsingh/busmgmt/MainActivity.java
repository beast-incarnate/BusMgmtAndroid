package com.example.kunalsingh.busmgmt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kunalsingh.busmgmt.api.services.APIService;
import com.example.kunalsingh.busmgmt.models.Student;

import java.io.Serializable;
import java.util.List;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button btnLogin;
    ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

        btnLogin = (Button)findViewById(R.id.btn_login);

        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Logging In");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = username.getText().toString();
                String passWord = password.getText().toString();

                if(userName.equals("")||passWord.equals("")){
                    Toast.makeText(MainActivity.this, "Wrong Input", Toast.LENGTH_SHORT).show();
                }else{

                        mDialog.show();
                        login(userName,passWord);

                }
            }
        });

    }


    public void getStudentDetails(){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.102:3000/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        APIService service = retrofit.create(APIService.class);

      //  Call<List<Student>> call = service.getStudentDetails();

//        call.enqueue(new Callback<List<Student>>() {
//            @Override
//            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
//                List<Student> students = response.body();
//                String s = "";
//                for(int i =0 ; i<students.size();i++){
//                    s = s + "Name : " + students.get(i).getName()
//                           + "\n" + "Fathers Name : " +students.get(i).getFathersName() +"\n" + students.get(i).getAddress()
//                            + "\n" + students.get(i).getMothersName() + "\n" + students.get(i).getContact() + "\n" + students.get(i).getTotal()
//                            + "\n" + students.get(i).getPresent() + "\n" + students.get(i).isFees();
//
//                }
//
//                mDialog.dismiss();
//                tvJson.setText(s);
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Student>> call, Throwable t) {
//
//                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                mDialog.dismiss();
//            }
//        });

    }

    public void login(String username , String password){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.102:3000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Student> call = service.signInStudent(username,password);

        call.enqueue(new Callback<Student>() {

            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Student student = response.body();
                mDialog.dismiss();
                if(student!=null) {
                    Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                    intent.putExtra("student",student);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                mDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error in Login", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
