package tigerspike.com.timely;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_today:
                    mTextMessage.setText(R.string.title_today);
                    return true;
                case R.id.navigation_stats:
                    mTextMessage.setText(R.string.title_stats);
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText(R.string.title_settings);
                    gotoSettingsScreen();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        sendMessage();

        //Creating SendMail object
        SendEmail sm = new SendEmail(this, "anaschaky@gmail.com", "Timely App Notification!!!", "I am late for 5 Minute!!!");
        //Executing sendmail to send email
        sm.execute();
    }
    
    private void gotoSettingsScreen() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("Email", "anas.abubacker@tigerspike.com");
        startActivity(intent);
        finishAffinity();
    }
}
