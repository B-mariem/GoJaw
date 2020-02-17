package com.mariem.gojaw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button login;
    TextView signup;
    EditText edtEmail,edtPassword;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;
    String BASE_URL="http://192.168.43.117:4000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface=retrofit.create(RetrofitInterface.class);

        initUi();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailLogin = edtEmail.getText().toString();
                final String passwordLogin = edtPassword.getText().toString();
                if(checkfields(emailLogin,passwordLogin)!=false){
                    loginUser();

                }

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignupDialog();
            }
        });

    }

    private void handleSignupDialog() {
        View view = getLayoutInflater().inflate(R.layout.signup_dialog, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("inscrivez-vous");
        builder.setView(view).show();
        builder.setCancelable(true);


        Button signupBtn = view.findViewById(R.id.signup);
        final EditText nameEdit = view.findViewById(R.id.nameEdit);
        final EditText emailEdit = view.findViewById(R.id.emailEdit);
        final EditText passwordEdit = view.findViewById(R.id.passwordEdit);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=emailEdit.getText().toString();
                final String password=passwordEdit.getText().toString();
                if(checkfields(email,password)!=false){
                    HashMap<String,String> map=new HashMap<>();
                    map.put("name",nameEdit.getText().toString());
                    map.put("email",email);
                    map.put("password",password);
                    Call<Void> call=retrofitInterface.executeSignup(map);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.code()==200){
//                          builder .setPositiveButton("SKIP", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    //this for skip dialog
//                                    dialog.cancel();
//                                }
//                            });

                                startActivity(new Intent(getApplicationContext(),MainActivity.class));


                                Toast.makeText(getApplicationContext(),"go to login activity",Toast.LENGTH_LONG).show();
                            }else if (response.code() == 400) {
                                Toast.makeText(MainActivity.this,
                                        "Already registered", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });

                }

            }
        });
    }

    public  boolean checkfields(String email,String password){


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Merci d'entrer votre email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Merci d'entrer un email valide", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password) || password.length() < 4) {
            Toast.makeText(this, "Votre mot de passe doit contenir plus que 6 caractères", Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;

    }
    private void loginUser() {

        HashMap <String,String> map=new HashMap<>();
        map.put("email",edtEmail.getText().toString());
        map.put("password",edtPassword.getText().toString());
        Call<LoginResult> call=retrofitInterface.executeLogin(map);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.code() == 200) {

                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                    LoginResult result = response.body();
                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                    intent.putExtra(Constant.ARG_ID,result.getId());
                    intent.putExtra(Constant.ARG_NAME,result.getName());
                    startActivity(intent);
                    finish();

                }
                if(response.code()==404){
                    Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    private void initUi() {
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);
        edtEmail=findViewById(R.id.emailEdit);
        edtPassword=findViewById(R.id.passwordEdit);
    }

}