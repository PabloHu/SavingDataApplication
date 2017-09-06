package com.example.admin.savingdataapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etSaveData,etName, etPhone;
    TextView tvRetreivedData;
    private static final String MY_PREF_FILE = "com.example.user.savingdata.mypreffile";
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSaveData = (EditText) findViewById(R.id.etText);
        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        tvRetreivedData = (TextView) findViewById(R.id.tvRetreiveData);

    }

    public void usingSharedpref(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE);

        switch (view.getId()) {

            case R.id.btnSaveData:

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("keyData", etSaveData.getText().toString());
                editor.commit();//.apply or commit, apply does it when it need to on different thread

                boolean isSaved = editor.commit();

                if (isSaved) {
                    Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "Not saved", Toast.LENGTH_SHORT).show();
                //    Toast.makeText(this, "Saved Data", Toast.LENGTH_SHORT).show();
                break;


            case R.id.btnRetrieveData:
                String retrieveData = sharedPreferences.getString("keyData", "default");
             tvRetreivedData.setText(retrieveData);
                break;

        }
    }

    public void usingSQLite(View view) {
        switch (view.getId()) {

            case R.id.btnSaveDataSQL:
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();

                long rowNumber = databaseHelper.saveNewContact(name, phone);

                Toast.makeText(this, "row: "+ rowNumber, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnGetDataSQL:

                databaseHelper.getContacts();


                break;

        }
    }
}
