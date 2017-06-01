package tigerspike.com.timely;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;

public class SettingsActivity extends AppCompatActivity {

    private TextView mChooseLineManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mChooseLineManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareAndShowLineManagers();
            }
        });
    }


    private void prepareAndShowLineManagers() {
        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(new MaterialSimpleListAdapter.Callback() {
            @Override
            public void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialSimpleListItem item) {
                // TODO
                mChooseLineManager.setText(item.getContent());
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

}
