package mediseen.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import mediseen.customtextview.TextViewPlus;
import mediseen.work.pearlsantos.mediseen.MainActivity;
import mediseen.work.pearlsantos.mediseen.R;

/**
 * Created by elysi on 3/10/2016.
 */
public class LoginPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SpannableStringBuilder builder = new SpannableStringBuilder();

        String blue = "medi";
        SpannableString blueSpannable= new SpannableString(blue);
        blueSpannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.backgroundBlue)), 0, blue.length(), 0);
        builder.append(blueSpannable);

        String green = "seen";
        SpannableString greenSpannable= new SpannableString(green);
        greenSpannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.backgroundGreen)), 0, green.length(), 0);
        builder.append(greenSpannable);


        ((TextViewPlus) findViewById(R.id.mediseenLabel)).setText(builder, TextView.BufferType.SPANNABLE);
        Picasso.with(this).load(R.drawable.logomark).resizeDimen(R.dimen.logoMarkWidth,
                R.dimen.logoMarkHeight).into((ImageView) findViewById(R.id.logo));

    }

    public void login(View view){
        SharedPreferences prefs = this.getSharedPreferences(CheckStart.PREFS_NAME, Context.MODE_PRIVATE);
        String username = prefs.getString(SetAccount.USERNAME, "");
        String password = prefs.getString(SetAccount.PASSWORD, "");

        String userN = ((EditText) findViewById(R.id.username)).getText().toString().trim();
        String pass = ((EditText) findViewById(R.id.password)).getText().toString().trim();
        if(userN.isEmpty() || pass.isEmpty()){
            Toast.makeText(LoginPage.this, R.string.fillDetails, Toast.LENGTH_SHORT).show();
        }
        else if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(LoginPage.this, "User does not exist", Toast.LENGTH_SHORT).show();
        } else if (username.equals(userN) && password.equals(pass)){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        } else{
            Toast.makeText(LoginPage.this, "User does not exist", Toast.LENGTH_SHORT).show();
        }

    }

}
