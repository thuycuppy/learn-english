package hocandroid.lethuy.ptit.learnenglish;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import hocandroid.lethuy.ptit.util.DBUtil;

public class MainActivity extends BaseActivity {
    LinearLayout main;
    ImageButton btnVocabulary, btnGrammar;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main,null,false);
        mDrawerLayout.addView(contentView,0);
        copyDBFromAsset();
        addControls();
        addEvents();
    }

    private void copyDBFromAsset() {
        File dbFile = getDatabasePath(DBUtil.DB_NAME);

        if (!dbFile.exists()) {
            try {
                String dbFolderPath = getApplicationInfo().dataDir + DBUtil.DB_PATH_SUFFIX;
                String dbFilePath = getApplicationInfo().dataDir + DBUtil.DB_PATH_SUFFIX + DBUtil.DB_NAME;
                File folder = new File(dbFolderPath);
                if (!folder.exists()) {
                    folder.mkdir();
                }
                InputStream input = getAssets().open(DBUtil.DB_NAME);
                OutputStream output = new FileOutputStream(dbFilePath);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
                output.close();
                output.close();
                input.close();

                Toast.makeText(this, "Import database successfully", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addEvents() {
        btnVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, TopicActivity.class);
                startActivity(intent);
            }
        });
        btnGrammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, GrammarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        main = (LinearLayout) findViewById(R.id.main);
        main.setBackgroundResource(R.drawable.main);

        btnVocabulary = (ImageButton) findViewById(R.id.btnVocabulary);
        btnGrammar = (ImageButton) findViewById(R.id.btnGrammar);
    }
}
