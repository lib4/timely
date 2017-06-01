package tigerspike.com.timely;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import static tigerspike.com.timely.R.id.email;

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
        SendEmail sm = new SendEmail(this, "anaschaky@gmail.com","Timely App Notification!!!", "I am late for 5 Minute!!!");
        //Executing sendmail to send email
        sm.execute();
    }

    private void sendMessage() {
        String[] recipients = {"dawid.hyzy@tigerspike.com"};
        SendEmailAsyncTask email = new SendEmailAsyncTask();
        email.activity = this;
        email.m = new Mail("anaschaky@gmail.com", "wonderful_SUHANA@1989");
        email.m.set_from("AnasAbubacker");
        email.m.setBody("I am gonna late for 5 min!");
        email.m.set_to(recipients);
        email.m.set_subject("Timely Notification");
        email.execute();
    }

    public void displayMessage(String message) {
//        Snackbar.make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
    }

    class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
        Mail m;
        HomeActivity activity;

        public SendEmailAsyncTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                if (m.send()) {
                    activity.displayMessage("Email sent.");
                } else {
                    activity.displayMessage("Email failed to send.");
                }

                return true;
            } catch (AuthenticationFailedException e) {
                Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
                e.printStackTrace();
                activity.displayMessage("Authentication failed.");
                return false;
            } catch (MessagingException e) {
                Log.e(SendEmailAsyncTask.class.getName(), "Email failed");
                e.printStackTrace();
                activity.displayMessage("Email failed to send.");
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                activity.displayMessage("Unexpected error occured.");
                return false;
            }
        }


    }

    private void gotoSettingsScreen() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("Email","anas.abubacker@tigerspike.com");
        startActivity(intent);
        finishAffinity();
    }
}
