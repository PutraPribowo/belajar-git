package com.developerputra.myuserapigithub;

import android.content.AsyncTaskLoader;
import android.content.Context;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class UserAsyncTaskLoader extends AsyncTaskLoader<ArrayList<UserItems>> {

    private static final String API_KEY = "6dee297ff7dc89251640082eff0cb196ca5b4a70";
    private ArrayList<UserItems> mData;
    private boolean mHasResult = false;
    private String mUserTitle;

    public UserAsyncTaskLoader(final Context context, String userTitle) {
        super(context);
        onContentChanged();
        this.mUserTitle = userTitle;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    public void deliverResult(ArrayList<UserItems> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset(){
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private void onReleaseResources(ArrayList<UserItems> data) {

    }

    @Override
    public ArrayList<UserItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<UserItems> userItems_ = new ArrayList<>();
        String url = "https://api.github.com/search/users?q=$query" + API_KEY + "&language=en-US&query=" + mUserTitle;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++){
                        JSONObject movie = list.getJSONObject(i);
                        UserItems userItems = new UserItems(movie);
                        userItems_.add(userItems);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });
        return userItems_;
    }
}