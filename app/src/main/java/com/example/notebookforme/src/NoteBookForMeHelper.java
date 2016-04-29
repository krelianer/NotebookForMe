package com.example.notebookforme.src;

import android.app.Activity;
import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.example.notebookforme.R;
import com.example.notebookforme.adapter.MovieAdapter;
import com.example.notebookforme.model.Movie;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Kreliou on 25/04/2016.
 */
public class NoteBookForMeHelper {

    private static MobileServiceClient mClient;

    public static boolean initMobileService(Context context)
    {
        try {
            mClient = new MobileServiceClient("https://notebookforme.azurewebsites.net", context);
        }
        catch (MalformedURLException e) {
            return false;
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public static void updateMovieList(String movie, final MovieAdapter movieAdapter, final Activity activity)
    {
        activity.findViewById((R.id.progress_bar)).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.movieSearchListView).setVisibility(View.INVISIBLE);

        ArrayList<Pair<String, String>> parameters = new ArrayList<>();
        parameters.add(new Pair<String, String>("name", movie));

        ListenableFuture<JsonElement> result = mClient.invokeApi("theMovieDB/SelectByName", "GET", parameters);

        Futures.addCallback(result, new FutureCallback<JsonElement>() {
            @Override
            public void onFailure(Throwable exc) {
                return ;
            }

            @Override
            public void onSuccess(JsonElement result) {
                fillMovieAdapter(getMovieList(result), movieAdapter, activity);
                return ;
            }
        });
        return ;
    }

    private static void fillMovieAdapter(final ArrayList<Movie> movieArrayList, final MovieAdapter movieAdapter, final Activity activity)
    {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                movieAdapter.resetDataSet(movieArrayList);
                activity.findViewById((R.id.progress_bar)).setVisibility(View.INVISIBLE);
                activity.findViewById(R.id.movieSearchListView).setVisibility(View.VISIBLE);
            }
        });
        return ;
    }

    private static ArrayList<Movie> getMovieList(JsonElement result)
    {
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();

        JsonArray array = result.getAsJsonArray();
        final ArrayList<Movie> myObjects = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            myObjects.add(gson.fromJson(array.get(i).getAsJsonObject().toString(), Movie.class));
        }

        //Clean Hours from dates and also checks if the movie has a release date
        for (Iterator<Movie> iterator = myObjects.iterator(); iterator.hasNext();)
        {
            Movie temp = iterator.next();
            if (temp.getRelease() == null)
                iterator.remove();
            else
                temp.cutReleaseHours();
        }

        return myObjects;

    }

}


