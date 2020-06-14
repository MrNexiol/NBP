package tomasz.kopycinski.nbp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import tomasz.kopycinski.nbp.fragments.TableFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentView, new TableFragment('A')).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment;

            switch (item.getItemId()){
                case R.id.table_a:
                    selectedFragment = new TableFragment('A');
                    break;
                case R.id.table_b:
                    selectedFragment = new TableFragment('B');
                    break;
                default:
                    selectedFragment = new TableFragment('C');
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentView, selectedFragment).commit();
            return true;
        });
    }
}
