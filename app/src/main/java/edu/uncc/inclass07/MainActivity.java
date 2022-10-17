// Stanley_InClass07
// MainActivity.java
// Ken Stanley

package edu.uncc.inclass07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.uncc.inclass07.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, MainFragment.newInstance())
                .commit();
    }
}