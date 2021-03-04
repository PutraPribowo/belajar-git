package com.developerputra.myuserapigithub;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Utama extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<ArrayList<UserItems>> {
    ListView listView;
    EditText edituser;
    ImageView imgPoster;
    Button btnSearch;
    UserAdapter adapter;

    static final String EXTRAS_USER = "EXTRAS_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        adapter = new UserAdapter(this);
        adapter.notifyDataSetChanged();

        listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                UserItems item = (UserItems)parent.getItemAtPosition(position);
                Intent intent = new Intent(Utama.this, Detail.class);
                intent.putExtra(Detail.EXTRA_USERNAME, item.getUsername());
                intent.putExtra(Detail.EXTRA_COMPANY, item.getCompany());
                intent.putExtra(Detail.EXTRA_LOCATION, item.getLocation());
                intent.putExtra(Detail.EXTRA_IMG, item.getImg());

                startActivity(intent);
            }
        });

        edituser   = (EditText)findViewById(R.id.edituser);
        imgPoster   = (ImageView)findViewById(R.id.img);
        btnSearch   = (Button)findViewById(R.id.search);
        btnSearch.setOnClickListener(movieListener);

        String username = edituser.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_USER, username);

        getLoaderManager().initLoader(0, bundle, this);
    }

    @Override
    public android.content.Loader<ArrayList<UserItems>> onCreateLoader(int i, Bundle bundle) {
        String username = "";
        if (bundle != null){
            username = bundle.getString(EXTRAS_USER);
        }

        return new UserAsyncTaskLoader(this,username);
    }

    @Override
    public void onLoadFinished(android.content.Loader<ArrayList<UserItems>> loader, ArrayList<UserItems> userItems) {
        adapter.setData(userItems);
    }

    @Override
    public void onLoaderReset(android.content.Loader<ArrayList<UserItems>> loader) {
        adapter.setData(null);
    }

    //Button search diklik
    View.OnClickListener movieListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String movieTitle = edituser.getText().toString();
            if(TextUtils.isEmpty(movieTitle)){
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_USER, movieTitle);
            getLoaderManager().restartLoader(0, bundle, Utama.this);
        }
    };
}
