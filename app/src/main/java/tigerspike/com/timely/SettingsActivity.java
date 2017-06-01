package tigerspike.com.timely;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;

public class SettingsActivity extends AppCompatActivity {

    private TextView mChooseLineManager;
    private TextView mAccountEmail;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    gotoHomeScreen();
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mChooseLineManager = (TextView) findViewById(R.id.choose_your_lm);
        mAccountEmail = (TextView) findViewById(R.id.account);
        mAccountEmail.setText(getIntent().getStringExtra("Email"));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mChooseLineManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareAndShowLineManagers();
                showNotification();
            }
        });
    }


    private void prepareAndShowLineManagers() {
        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(new MaterialSimpleListAdapter.Callback() {
            @Override
            public void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialSimpleListItem item) {
                // TODO
                mChooseLineManager.setText(item.getContent());
                dialog.cancel();
            }
        });

        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("Adrian.roney@tigerspike.com")
//                .icon(R.drawable.ic_account_circle)
                .backgroundColor(Color.WHITE)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("steve.burrows@tigerspike.com")
//                .icon(R.drawable.ic_account_circle)
                .backgroundColor(Color.WHITE)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("ronan.donohoe@tigerspike.com")
//                .icon(R.drawable.ic_content_add)
                .iconPaddingDp(8)
                .build());
        adapter.add(new MaterialSimpleListItem.Builder(this)
                .content("andy.boyle@tigerspike.com")
//                .icon(R.drawable.ic_content_add)
                .iconPaddingDp(8)
                .build());

        new MaterialDialog.Builder(this)
                .title("Choose Your Line Manager")
                .adapter(adapter, null)
                .show();
    }


    private void gotoHomeScreen() {
        Intent intent   =   new Intent(this, HomeActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void showNotification() {

        Intent intent = new Intent(this, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(this);

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_dashboard_black_24dp)
                .addAction(R.drawable.yes,"YES",contentIntent)
                .setTicker("Hearty365")
                .setContentTitle("Default notification")
                .setContentText("Are you goign to be late? If Yes TAP Here")
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo("Info");


        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());
    }

}
