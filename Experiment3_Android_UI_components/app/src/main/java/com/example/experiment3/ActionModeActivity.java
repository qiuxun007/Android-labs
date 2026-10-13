package com.example.experiment3;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionModeActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private final List<String> data = new ArrayList<>(
            Arrays.asList("One", "Two", "Three", "Four", "Five"));

    private ActionMode actionMode;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode);

        listView = findViewById(R.id.listViewAction);
        adapter = new ArrayAdapter<>(this,
                R.layout.list_item_action, R.id.tv_item, data);
        listView.setAdapter(adapter);

        //关键：长按列表项
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            if (actionMode != null) {
                return false;
            }
            selectedPosition = position;

            //三件事一起做，item 才会变蓝
            listView.setItemChecked(position, true);
            view.setSelected(true);
            view.setActivated(true);

            actionMode = startSupportActionMode(actionModeCallback);
            return true;
        });
    }

    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_context, menu);
            mode.setTitle("1 selected");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.action_delete) {
                if (selectedPosition >= 0 && selectedPosition < data.size()) {
                    data.remove(selectedPosition);
                    adapter.notifyDataSetChanged();
                }
                mode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            //ActionMode 结束时，清掉选中状态，item 恢复白色
            if (selectedPosition >= 0) {
                listView.setItemChecked(selectedPosition, false);
            }
            actionMode = null;
            selectedPosition = -1;
        }
    };
}