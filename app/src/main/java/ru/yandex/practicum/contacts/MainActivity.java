package ru.yandex.practicum.contacts;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ru.yandex.practicum.contacts.databinding.ActivityMainBinding;
import ru.yandex.practicum.contacts.ui.adapter.ContactAdapter;
import ru.yandex.practicum.contacts.ui.main.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        adapter = new ContactAdapter();
        binding.recycler.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.init();
        viewModel.getContactsLiveDate().observe(this, uiContacts -> adapter.setItems(uiContacts));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_sort) {
            toast(R.string.menu_sort);
            return true;
        }
        if (id == R.id.menu_filter) {
            toast(R.string.menu_filter);
            return true;
        }
        if (id == R.id.menu_search) {
            toast(R.string.menu_search);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toast(@StringRes int res) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }
}