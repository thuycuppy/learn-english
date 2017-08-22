package hocandroid.lethuy.ptit.learnenglish;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import hocandroid.lethuy.ptit.dao.TestDAO;
import hocandroid.lethuy.ptit.dao.TestDAOImpl;
import hocandroid.lethuy.ptit.model.Test;
import hocandroid.lethuy.ptit.util.DBUtil;

public class TestActivity extends BaseActivity {
    TextView txtQuestion;
    Button btnStop,btnCheck, btnNext;
    RadioGroup group;
    RadioButton radA,radB,radC,radD;

    TestDAO  testDAO;
    Test test;
    ArrayList<Test> tests;

    Intent intent;
    int correct,total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_test,null,false);
        mDrawerLayout.addView(contentView,0);
        SQLiteDatabase db = openOrCreateDatabase(DBUtil.DB_NAME,MODE_PRIVATE,null);
        testDAO = new TestDAOImpl(db);
        correct =0;
        total =0;
        addControls();
        addEvents();

    }

    private void addEvents() {
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(TestActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn đạt được "+correct+"/"+total+" câu hỏi. Bạn có muốn thoát khỏi chương trình?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processCheck();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadQuestion();
            }
        });

    }

    private void processCheck() {
        btnNext.setEnabled(true);
        btnCheck.setEnabled(false);
        total++;
        if(radA.isChecked()){
            if(radA.getText().equals(test.getCorrect())){
                radA.setBackgroundColor(Color.parseColor("#69f013"));
                correct++;
            }else {
                radA.setChecked(false);
                radA.setBackgroundColor(Color.RED);
            }
        }
        else if(radB.isChecked()){
                if(radB.getText().equals(test.getCorrect())){
                    radB.setBackgroundColor(Color.parseColor("#69f013"));
                    correct++;
                }else {
                    radB.setChecked(false);
                    radB.setBackgroundColor(Color.RED);
                }
        }
        else if(radC.isChecked()){
            if(radC.getText().equals(test.getCorrect())){
                radC.setBackgroundColor(Color.parseColor("#69f013"));
                correct++;
            }else {
                radC.setChecked(false);
                radC.setBackgroundColor(Color.RED);
            }
        }
        else if(radD.isChecked()){
            if(radD.getText().equals(test.getCorrect())){
                radD.setBackgroundColor(Color.parseColor("#69f013"));
                correct++;
            }else {
                radD.setChecked(false);
                radD.setBackgroundColor(Color.RED);
            }
        }
    }

    private void addControls() {
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        group = (RadioGroup) findViewById(R.id.group);
        radA = (RadioButton) findViewById(R.id.radA);
        radC = (RadioButton) findViewById(R.id.radC);
        radD = (RadioButton) findViewById(R.id.radD);
        radB = (RadioButton) findViewById(R.id.radB);
        tests = new ArrayList<>();
        test = new Test();
        intent = getIntent();
        tests = (ArrayList<Test>) testDAO.findAll(intent.getIntExtra("ID_GRAMMAR",0));
        group.clearCheck();

        loadQuestion();

    }
    private void loadQuestion() {
        btnCheck.setEnabled(true);
        btnNext.setEnabled(false);
        group.clearCheck();
        radA.setBackgroundColor(Color.WHITE);
        radB.setBackgroundColor(Color.WHITE);
        radC.setBackgroundColor(Color.WHITE);
        radD.setBackgroundColor(Color.WHITE);

        int size = tests.size();
        Random random = new Random();
        int position = random.nextInt(size-1);
        test = tests.get(position);
        txtQuestion.setText(test.getQuestion());
        radA.setText(test.getChooseA());
        radB.setText(test.getChooseB());
        radC.setText(test.getChooseC());
        radD.setText(test.getChooseD());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_back) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
