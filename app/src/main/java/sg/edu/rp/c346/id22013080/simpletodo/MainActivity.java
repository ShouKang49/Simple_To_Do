package sg.edu.rp.c346.id22013080.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner addRemoveSpn;

    EditText userInp;

    Button add;
    Button delete;
    Button clear;

    ListView taskDis;

    ArrayList<String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addRemoveSpn = findViewById(R.id.spinner);
        userInp = findViewById(R.id.editTextUserInp);
        add = findViewById(R.id.buttonAdd);
        delete = findViewById(R.id.buttonDelete);
        clear = findViewById(R.id.buttonClear);
        taskDis = findViewById(R.id.taskListView);

        tasks = new ArrayList<String>();

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        taskDis.setAdapter(adapter);

        addRemoveSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        add.setEnabled(true);
                        delete.setEnabled(false);
                        userInp.setHint(R.string.addATask);
                        userInp.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        add.setEnabled(false);
                        delete.setEnabled(true);
                        userInp.setHint(R.string.removeATask);
                        userInp.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addTask = userInp.getText().toString();

                userInp.setText("");

                tasks.add(addTask);
                Toast.makeText(MainActivity.this, "Task added", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(userInp.getText().toString());
                boolean found = false;

                userInp.setText("");

                int listSize = tasks.size();

                if(listSize < 1){
                    Toast.makeText(MainActivity.this, "No Tasks to remove", Toast.LENGTH_SHORT).show();
                }
                else if(listSize > 0){
                    for(int i = 0; i < listSize; i++){
                        if(pos == i){
                            found = true;
                        }
                    }
                    if(found == true){
                        tasks.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Wrong Index number",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = tasks.size();

                if(size > 0){
                    tasks.clear();
                    userInp.setText("");
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "All tasks have been cleared", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "No task to clear", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}