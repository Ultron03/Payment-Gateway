package com.ujjwaltiwari232.pay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    Button enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = findViewById(R.id.enter);
        String amount ="100";

        int realAmount = Math.round(Float.parseFloat(amount)*100);

        enter.setOnClickListener(view -> {

            Checkout checkout =new Checkout();
            checkout.setKeyID("rzp_test_gbY5tPeSvbbOfj");
            checkout.setImage(R.drawable.img);

            JSONObject object = new JSONObject();
            try {
                object.put("Name","Ujjwal Tiwari");
                object.put("Description","Gateway");
                object.put("theme color","#35EDF9");
                object.put("currency","INR");
                object.put("amount",realAmount);
                object.put("Contact","6874518955");
                object.put("email","ujjwaltiwari232@gmail.com");
                checkout.open(MainActivity.this,object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Payment ID");
        builder.setMessage(s);
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

    }
}