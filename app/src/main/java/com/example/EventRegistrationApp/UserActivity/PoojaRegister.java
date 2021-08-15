package com.example.EventRegistrationApp.UserActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.EventRegistrationApp.ModelClasses.RegList;
import com.example.EventRegistrationApp.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class PoojaRegister extends AppCompatActivity implements PaymentResultListener{

    private TextView name, price, date, desc;
    private TextView reg_name, email, phone;
    Button btn_pay;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    String rname, remail,rphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooja_register);
        Intent intent = getIntent();
        String pass_name = intent.getStringExtra("nameINTENT");
        String pass_price = intent.getStringExtra("priceINTENT");
        String pass_date = intent.getStringExtra("dateINTENT");
        String pass_desc = intent.getStringExtra("descINTENT");

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();


        btn_pay = findViewById(R.id.pay);
        name = findViewById(R.id.id_name);
        price = findViewById(R.id.id_price);
        date = findViewById(R.id.id_date);
        desc = findViewById(R.id.id_desc);

        name.setText("Pooja Name : " + getIntent().getStringExtra("nameINTENT"));
        price.setText("Pooja Price : " + getIntent().getStringExtra("priceINTENT"));
        date.setText("Pooja Date : " + getIntent().getStringExtra("dateINTENT"));
        desc.setText("Description : " + getIntent().getStringExtra("descINTENT"));


        DocumentReference dref = fStore.collection("Users").document(userId);
        dref.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                 rname = value.getString("FullName");
                 remail = value.getString("UserEmail");
                 rphone = value.getString("PhoneNumber");
            }
        });


        int amt= Math.round(Float.parseFloat(pass_price)*100);//don't know *100?
        String sAmt = String.valueOf(amt);

        btn_pay.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                Checkout checkout= new Checkout();
                checkout.setKeyID("rzp_test_80Jeicr0dG4uXD");
                checkout.setImage(R.drawable.razorpay_logo);

                JSONObject object= new JSONObject();
                try
                {
                    object.put("name", pass_name);
                    object.put("description", "Test payment");
                    object.put("theme.color","#F48B29");//#0093DD
                    object.put("currency", "INR");
                    object.put("amount",sAmt);
                    object.put("prefill.contact",rphone);
                    object.put("prefill.email",remail);
                    checkout.open(PoojaRegister.this, object);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Payment ID");
        builder.setMessage(s);
        builder.show();

        Intent intent = getIntent();
        String pname= intent.getStringExtra("nameINTENT");
        String pprice=  intent.getStringExtra("priceINTENT");
        String pdate = intent.getStringExtra("dateINTENT");
        String user_name = intent.getStringExtra("userNameIntent");
        String user_phone = intent.getStringExtra("userPhoneIntent");



        RegList rl = new RegList(pname, pprice, pdate, user_name, user_phone);
        FirebaseUser user = fAuth.getCurrentUser();
        Task<DocumentReference> df = fStore.collection("Users").document(user.getUid()).collection("Registrations").add(rl);
        Map<String, Object> reg = new HashMap<>();
        reg.put("PoojaName", pname.toString());
        reg.put("PoojaPrice", pprice.toString());
        reg.put("PoojaDate", pdate.toString());


        Task<DocumentReference> rref = fStore.collection("AllRegistrations").add(rl);
        Map<String, Object> allreg = new HashMap<>();
        allreg.put("PoojaName", pname.toString());
        allreg.put("PoojaDate", pdate.toString());
        allreg.put("UserName", user_name);
        allreg.put("UserPhone", user_phone);

        Intent it = new Intent(getApplicationContext(), DisplayRegistrations.class);
        startActivity(it);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }
}
