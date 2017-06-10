package com.dozydroid.prefrencesmenus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.HashMap;

public class UserDetailsActivity extends AppCompatActivity {

    TextView tvUserName, tvPassword;
    ToggleButton btnToggle;
    Button btnLogout;

    OurPreferences ourPreferences;
    HashMap<String, String> userDetails = new HashMap<>();
    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        ourPreferences = new OurPreferences(UserDetailsActivity.this);
        userDetails = ourPreferences.getUserDetails();
        userName = userDetails.get(OurPreferences.KEY_USERNAME);
        password = userDetails.get(OurPreferences.KEY_PASSWORD);

        tvUserName = (TextView) findViewById(R.id.tvUsername);
        tvPassword = (TextView) findViewById(R.id.tvPassword);

        btnToggle = (ToggleButton) findViewById(R.id.btnToggle);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ourPreferences.logoutUser();
            }
        });

        tvUserName.setText(userName);
        tvPassword.setText("********");

        btnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    tvPassword.setText(password);
                else
                    tvPassword.setText("********");
            }
        });

        registerForContextMenu(tvPassword);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        if(itemID == R.id.action_settings){

        }else if(itemID == R.id.action_username){
            Toast.makeText(this, "Username : "+ userName, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 1, 1, "Show/Hide Password");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == 1){
            if(tvPassword.getText().toString().equals("********")){
                tvPassword.setText(password);
            }else
                tvPassword.setText("********");
        }
        return true;
    }
}
