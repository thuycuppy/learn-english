package hocandroid.lethuy.ptit.learnenglish;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hocandroid.lethuy.ptit.adapter.TopicAdapter;
import hocandroid.lethuy.ptit.dao.TopicDAO;
import hocandroid.lethuy.ptit.dao.TopicDAOImpl;
import hocandroid.lethuy.ptit.model.Topic;
import hocandroid.lethuy.ptit.model.Vocabulary;
import hocandroid.lethuy.ptit.util.DBUtil;

public class TopicActivity extends BaseActivity {
    ListView lvTopic;
    ArrayList<Topic> topics;
    TopicAdapter adapter;
    Topic topic;

    Intent intent;

    TopicDAO  topicDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_topic,null,false);
        mDrawerLayout.addView(contentView,0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SQLiteDatabase db = openOrCreateDatabase(DBUtil.DB_NAME,MODE_PRIVATE,null);
        topicDAO = new TopicDAOImpl(db);

        addControls();
        addEvents();

    }

    private void addEvents() {
        lvTopic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                topic = topics.get(position);
                intent = new Intent(TopicActivity.this,VocabularyActivity.class);
                intent.putExtra("TOPIC",topic);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        lvTopic = (ListView) findViewById(R.id.lvTopicVocabulary);
        topics = new ArrayList<>(topicDAO.findAll());
        adapter = new TopicAdapter(this,R.layout.item_topic,topics);
        lvTopic.setAdapter(adapter);
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
