package hocandroid.lethuy.ptit.learnenglish;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import hocandroid.lethuy.ptit.model.Grammar;

public class DetailGrammar extends BaseActivity {
    TextView txtName,txtUse,txtForm,txtExample,txtNote;

    Intent intent;

    Grammar grammar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_detail_grammar,null,false);
        mDrawerLayout.addView(contentView,0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addControls();
        addEvents();

    }

    private void addEvents() {
    }

    private void addControls() {
        txtName = (TextView) findViewById(R.id.txtName);
        txtUse = (TextView) findViewById(R.id.txtUse);
        txtForm = (TextView) findViewById(R.id.txtForm);
        txtExample = (TextView) findViewById(R.id.txtExample);
        txtNote = (TextView) findViewById(R.id.txtNote);

        intent = getIntent();
        grammar = (Grammar) intent.getSerializableExtra("GRAMMAR");

        txtName.setText(grammar.getName());
        txtUse.setText(grammar.getUse());
        txtForm.setText(grammar.getForm());
        txtExample.setText(grammar.getExample());
        txtNote.setText(grammar.getNote());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test,menu);
        inflater.inflate(R.menu.back,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_test){
            int id_gramar = grammar.getId();
            intent = new Intent(DetailGrammar.this, TestActivity.class);
            intent.putExtra("ID_GRAMMAR",id_gramar);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.action_back) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
