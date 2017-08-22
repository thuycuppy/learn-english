package hocandroid.lethuy.ptit.learnenglish;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hocandroid.lethuy.ptit.adapter.VocabularyAdapter;
import hocandroid.lethuy.ptit.dao.VocabularyDAO;
import hocandroid.lethuy.ptit.dao.VocabularyDAOImpl;
import hocandroid.lethuy.ptit.model.Topic;
import hocandroid.lethuy.ptit.model.Vocabulary;
import hocandroid.lethuy.ptit.util.DBUtil;

public class VocabularyActivity extends BaseActivity {
    ListView lvVocabulary;
    ArrayList<Vocabulary> vocabularies = new ArrayList<>();
    VocabularyAdapter adapter;
    VocabularyDAO vocabularyDAO;

    Intent intent;
    Topic topic ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_vocabulary,null,false);
        mDrawerLayout.addView(contentView,0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SQLiteDatabase db = openOrCreateDatabase(DBUtil.DB_NAME,MODE_PRIVATE,null);
        vocabularyDAO = new VocabularyDAOImpl(db);
        
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {

        lvVocabulary = (ListView) findViewById(R.id.lvVocabulary);
        intent = getIntent();
        topic = (Topic) intent.getSerializableExtra("TOPIC");
        vocabularies.clear();
        vocabularies.addAll(vocabularyDAO.findTopic(topic.getId()));

        adapter = new VocabularyAdapter(VocabularyActivity.this,R.layout.item_vocabulary,vocabularies);
        lvVocabulary.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game,menu);
        inflater.inflate(R.menu.back,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_game){
            int id_topic = topic.getId();
            intent = new Intent(VocabularyActivity.this,GameActivity.class);
            intent.putExtra("ID_TOPIC",id_topic);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.action_back){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
