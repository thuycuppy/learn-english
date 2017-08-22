package hocandroid.lethuy.ptit.learnenglish;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hocandroid.lethuy.ptit.dao.VocabularyDAO;
import hocandroid.lethuy.ptit.dao.VocabularyDAOImpl;
import hocandroid.lethuy.ptit.model.Vocabulary;
import hocandroid.lethuy.ptit.util.DBUtil;

public class GameActivity extends BaseActivity {
    ImageButton btnMp3, btnA, btnB;
    TextView txtWord;
    Button btnEnd;
    VocabularyDAO vocabularyDAO;

    Intent intent;
    private Toast showToast;

    ArrayList<Vocabulary> vocabularies;
    Vocabulary vocabulary,vocabularyA,vocabularyB, key;

    int total;
    int correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_game,null,false);
        mDrawerLayout.addView(contentView,0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SQLiteDatabase db = openOrCreateDatabase(DBUtil.DB_NAME, MODE_PRIVATE, null);
        vocabularyDAO = new VocabularyDAOImpl(db);

        total = 0;
        correct = 0;

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processMp3();
            }
        });
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processBtnA();
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processBtnB();
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processEnd();
            }
        });
    }

    private void processEnd() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
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

    private void processBtnA() {
        if((vocabularyA.getWord()).equals(key.getWord())){
            correct++;
            if(showToast != null){
                showToast.cancel();
            }
            showToast = Toast.makeText(GameActivity.this,"TRUE",Toast.LENGTH_SHORT);
            showToast.show();
        } else{
            if(showToast != null){
                showToast.cancel();
            }
            showToast = Toast.makeText(GameActivity.this,"FALSE",Toast.LENGTH_SHORT);
            showToast.show();
        }
        total++;
        loadQuestion();
    }

    private void processBtnB() {
        if((vocabularyB.getWord()).equals(key.getWord())){
            correct++;
            if(showToast != null){
                showToast.cancel();
            }
            showToast = Toast.makeText(GameActivity.this,"TRUE",Toast.LENGTH_SHORT);
            showToast.show();
        }
        else{
            if(showToast != null){
                showToast.cancel();
            }
            showToast = Toast.makeText(GameActivity.this,"FALSE",Toast.LENGTH_SHORT);
            showToast.show();
        }
        total++;
        loadQuestion();
    }



    private void processMp3() {
        String name = new StringBuilder().append("audio/").append(key.getMp3()).append(".mp3").toString();
        try {
            AssetFileDescriptor afd = getAssets().openFd(name);
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            afd.close();
            player.prepare();
            player.start();
        } catch (Exception ex) {

        }

    }

    private void addControls() {
        btnMp3 = (ImageButton) findViewById(R.id.btnMp3);
        btnA = (ImageButton) findViewById(R.id.btnA);
        btnB = (ImageButton) findViewById(R.id.btnB);
        txtWord = (TextView) findViewById(R.id.txtWord);
        btnEnd = (Button) findViewById(R.id.btnEnd);

        intent = getIntent();
        int id_topic = intent.getIntExtra("ID_TOPIC", 0);
        vocabularies = new ArrayList<>();
        vocabularies.addAll(vocabularyDAO.findTopic(id_topic));

        loadQuestion();

    }


    private void loadQuestion() {

        List<Vocabulary> questions = new ArrayList<>();
        vocabulary = new Vocabulary();
        int size = vocabularies.size();
        int positionA,positonB;
        do {
            Random random = new Random();
            positionA = random.nextInt(size - 1);
            positonB = random.nextInt(size-1);
        }while (positionA==positonB);
        vocabularyA=vocabularies.get(positionA);
        questions.add(vocabularyA);
        vocabularyB=vocabularies.get(positonB);
        questions.add(vocabularyB);
        btnA.setImageBitmap(BitmapFactory.decodeByteArray(vocabularyA.getImage(), 0, vocabularyA.getImage().length));
        btnB.setImageBitmap(BitmapFactory.decodeByteArray(vocabularyB.getImage(), 0, vocabularyB.getImage().length));

        Random random = new Random();
        key = questions.get(random.nextInt(2));

        txtWord.setText(key.getWord());
        processMp3();

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
