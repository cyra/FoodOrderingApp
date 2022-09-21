package edu.curtin.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        // Create FooterFragment
        FooterFragment footer = (FooterFragment) fm.findFragmentById(R.id.footerFragment);
        if (footer == null) {
            footer = FooterFragment.newInstance();
            fm.beginTransaction().add(R.id.footerFragment, footer).commit();
        }

        // Create HeaderFragment
        HeaderFragment header = (HeaderFragment) fm.findFragmentById(R.id.headerFragment);
        if (header == null) {
            header = HeaderFragment.newInstance();
            fm.beginTransaction().add(R.id.headerFragment, header).commit();
        }
    }
}