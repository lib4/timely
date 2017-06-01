package tigerspike.com.timely;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tigerspike.com.timely.databinding.SettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private static final List<String> emails = Arrays.asList(
            "adrian.roney@tigerspike.com",
            "steve.burrows@tigerspike.com",
            "ronan.donohoe@tigerspike.com",
            "andy.boyle@tigerspike.com"
    );

    private static final List<String> workingDays = Arrays.asList(
            "Sunday - Thursday",
            "Monday - Friday"
    );

    private static final List<String> workingHours = Arrays.asList(
            "8:00 - 16:30",
            "8:15 - 16:45",
            "8:30 - 17:00",
            "8:45 - 17:15",
            "9:00 - 17:30"
    );

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_today:
                    gotoHomeScreen();
                    return true;
                case R.id.navigation_stats:
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }

    };
    private SettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        binding.account.setText(getIntent().getStringExtra("Email"));
        binding.navigation.setSelectedItemId(R.id.navigation_settings);
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        binding.chooseYourWorkingHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareAndShowWorkingHours();
            }
        });
        binding.chooseYourWorkingDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareAndShowWorkingDays();
            }
        });
        binding.chooseYourLm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareAndShowLineManagers();
            }
        });
    }

    private void prepareAndShowWorkingDays() {
        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(new MaterialSimpleListAdapter.Callback() {
            @Override
            public void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialSimpleListItem item) {
                // TODO
                binding.chooseYourWorkingDays.setText(item.getContent());
                dialog.cancel();
            }
        });

        for (MaterialSimpleListItem item : getDialogItems(workingDays)) {
            adapter.add(item);
        }

        new MaterialDialog.Builder(this)
                .title("Choose Working Days")
                .adapter(adapter, null)
                .show();
    }

    private void prepareAndShowWorkingHours() {
        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(new MaterialSimpleListAdapter.Callback() {
            @Override
            public void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialSimpleListItem item) {
                // TODO
                binding.chooseYourWorkingHours.setText(item.getContent());
                dialog.cancel();
            }
        });

        for (MaterialSimpleListItem item : getDialogItems(workingHours)) {
            adapter.add(item);
        }

        new MaterialDialog.Builder(this)
                .title("Choose Your Working Hours")
                .adapter(adapter, null)
                .show();
    }


    private void prepareAndShowLineManagers() {
        final MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(new MaterialSimpleListAdapter.Callback() {
            @Override
            public void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialSimpleListItem item) {
                // TODO
                binding.chooseYourLm.setText(item.getContent());
                dialog.cancel();
            }
        });

        for (MaterialSimpleListItem item : getDialogItems(emails)) {
            adapter.add(item);
        }

        new MaterialDialog.Builder(this)
                .title("Choose Your Line Manager")
                .adapter(adapter, null)
                .show();
    }

    private List<MaterialSimpleListItem> getDialogItems(List<String> items) {
        List<MaterialSimpleListItem> dialogItems = new ArrayList<>();
        for (String item : items) {
            dialogItems.add(new MaterialSimpleListItem.Builder(this)
                    .content(item)
                    .backgroundColor(Color.WHITE)
                    .build());
        }
        return dialogItems;
    }

    private void gotoHomeScreen() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finishAffinity();
    }

}
