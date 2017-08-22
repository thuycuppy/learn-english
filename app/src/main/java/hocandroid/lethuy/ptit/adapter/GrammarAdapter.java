package hocandroid.lethuy.ptit.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import hocandroid.lethuy.ptit.learnenglish.R;
import hocandroid.lethuy.ptit.model.Grammar;

/**
 * Created by Admin on 5/3/2017.
 */

public class GrammarAdapter extends ArrayAdapter<Grammar> {
    Activity activity;
    int resource;
    List<Grammar> objects;
    public GrammarAdapter(Activity activity, int resource, List<Grammar> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View item = inflater.inflate(resource,null);

        TextView txtGrammar = (TextView) item.findViewById(R.id.txtGrammar);

        Grammar grammar = this.getItem(position);
        txtGrammar.setText(grammar.getName());
        return item;
    }
}
