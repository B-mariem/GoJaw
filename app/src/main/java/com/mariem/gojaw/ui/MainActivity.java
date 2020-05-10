package com.mariem.gojaw.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.mariem.gojaw.DATA.Constant;
import com.mariem.gojaw.models.User;
import com.mariem.gojaw.R;
import com.mariem.gojaw.DATA.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button signup;
    EditText edtEmail,edtPassword;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;


    private SharedPreferences sharedpreferences;
    private String MyPREFERENCES = "prefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (isConnected()) {
            openHomeActivity();
        }
        retrofit=new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
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


        final Button signupBtn = view.findViewById(R.id.signup);
        final EditText nameEdit = view.findViewById(R.id.nameEdit);
        final EditText userUrlEdit = view.findViewById(R.id.edt_user_url);
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
                    map.put("userURL",userUrlEdit.getText().toString());
                    map.put("email",email);
                    map.put("password",password);
                    Call<Void> call=retrofitInterface.executeSignup(map);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.code()==200){

                                nameEdit.setText(" ");
                                userUrlEdit.setText(" ");
                                emailEdit.setText(" ");
                                passwordEdit.setText(" ");

                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finishAffinity();



                            }else if (response.code() == 400) {
                                Snackbar.make(signupBtn,
                                        "Already registered", Snackbar.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Snackbar.make(signupBtn,
                                    t.getMessage(), Snackbar.LENGTH_LONG).show();

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

        Call<User> call=retrofitInterface.executeLogin(map);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {

                    User result = response.body();
                    saveSession(result.getId());
                    Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                   finishAffinity();

                }
                if(response.code()==404){
                    Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
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

    private void saveSession(String id) {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("IS_CONNECTED", true);
        editor.putString("ID_USER", id);
        editor.apply();
    }


    private void openHomeActivity() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finishAffinity();
    }

    private boolean isConnected() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getBoolean("IS_CONNECTED", false);

    }


}