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
import android.widget.ListView;

import java.util.ArrayList;

import hocandroid.lethuy.ptit.adapter.GrammarAdapter;
import hocandroid.lethuy.ptit.dao.GrammarDAO;
import hocandroid.lethuy.ptit.dao.GrammarDAOImpl;
import hocandroid.lethuy.ptit.model.Grammar;
import hocandroid.lethuy.ptit.util.DBUtil;

public class GrammarActivity extends BaseActivity {
    ListView lvGrammar;
    ArrayList<Grammar> grammars = new ArrayList<>();
    GrammarAdapter adapter;
    Intent intent;

    GrammarDAO grammarDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_grammar,null,false);
        mDrawerLayout.addView(contentView,0);
        SQLiteDatabase db = openOrCreateDatabase(DBUtil.DB_NAME, MODE_PRIVATE, null);
        grammarDAO = new GrammarDAOImpl(db);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvGrammar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(GrammarActivity.this, DetailGrammar.class);
                Grammar grammar = grammars.get(position);
                intent.putExtra("GRAMMAR", grammar);
                startActivity(intent);
            }
        });
    }

    private void addControls() {

        lvGrammar = (ListView) findViewById(R.id.lvGrammar);
        grammars.clear();
        grammars.addAll(grammarDAO.findAll());
        adapter = new GrammarAdapter(this, R.layout.item_grammar, grammars);
        lvGrammar.setAdapter(adapter);
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


