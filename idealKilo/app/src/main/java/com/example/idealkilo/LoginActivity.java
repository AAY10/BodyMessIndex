package com.example.idealkilo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        // tanımlama işlemlerini yapıyoruz
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
//      signin = (Button) findViewById(R.id.btnsignin);
        setTitle("Body Mass Index");
        DB = new DBHelper(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // database kısmındakileri tanımlıyoruz
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                // gelen verilere göre durumu söylüyor(alttaki uyarı mesajı)
                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(LoginActivity.this, "Lütfen tüm boşlukları doldurunuz", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(LoginActivity.this, "Kaydınız oluşturulmuştur", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Giris.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Kayıt işlemi başarısız", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Zaten kaydınız mevcut, lütfen giriş yapın", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Şifre aynı değil", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), Giris.class);
//                startActivity(intent);
//
//
//            }
//        });
    }

}
