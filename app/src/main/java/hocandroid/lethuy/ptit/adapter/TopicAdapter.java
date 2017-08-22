package hocandroid.lethuy.ptit.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hocandroid.lethuy.ptit.learnenglish.R;
import hocandroid.lethuy.ptit.model.Topic;

/**
 * Created by Admin on 5/3/2017.
 */

public class TopicAdapter extends ArrayAdapter<Topic> {
    Activity activity;
    int resource;
    List<Topic> objects;


    public TopicAdapter(Activity activity, int resource, List<Topic> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        ImageView imgTopic = (ImageView) item.findViewById(R.id.imgTopic);
        TextView txtName = (TextView) item.findViewById(R.id.txtName);

        Topic topic = this.objects.get(position);
        txtName.setText(topic.getName());
        imgTopic.setImageBitmap(BitmapFactory.decodeByteArray(topic.getImage(), 0,topic.getImage().length));


        return item;
    }
}
