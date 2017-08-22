package hocandroid.lethuy.ptit.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hocandroid.lethuy.ptit.learnenglish.R;
import hocandroid.lethuy.ptit.model.Vocabulary;

/**
 * Created by Admin on 5/2/2017.
 */

public class VocabularyAdapter extends ArrayAdapter<Vocabulary> {
    Activity activity;
    int resource;
    List<Vocabulary> objects;


    public VocabularyAdapter(Activity activity, int resource, List<Vocabulary> objects) {
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

        ImageView image = (ImageView) item.findViewById(R.id.image);
        TextView txtWord = (TextView) item.findViewById(R.id.txtWord);
        TextView txtPronounce = (TextView) item.findViewById(R.id.txtPronounce);
        TextView txtMean = (TextView) item.findViewById(R.id.txtMean);
        TextView txtExample = (TextView) item.findViewById(R.id.txtExample);
        ImageButton btnMp3 = (ImageButton) item.findViewById(R.id.btnMp3);

        final Vocabulary vocabulary = this.objects.get(position);
        txtWord.setText(vocabulary.getWord());
        txtPronounce.setText(vocabulary.getPronounce());
        txtMean.setText(vocabulary.getMean());
        txtExample.setText(vocabulary.getExample());
        image.setImageBitmap(BitmapFactory.decodeByteArray(vocabulary.getImage(), 0, vocabulary.getImage().length));

        btnMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processMp3(vocabulary);
            }
        });

        return item;
    }

    private void processMp3(Vocabulary vocabulary) {
        String name = new StringBuilder().append("audio/").append(vocabulary.getWord()).append(".mp3").toString();
        try {
            AssetFileDescriptor afd = activity.getAssets().openFd(name);
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            player.prepare();
            player.start();

        } catch (Exception ex) {

        }
    }
}
