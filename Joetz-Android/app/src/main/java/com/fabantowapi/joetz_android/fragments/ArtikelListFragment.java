package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.activities.MainActivity;
import com.fabantowapi.joetz_android.contentproviders.ArticleContentProvider;
import com.fabantowapi.joetz_android.adapters.ArtikelAdapter;
import com.fabantowapi.joetz_android.model.Article;
import com.fabantowapi.joetz_android.utils.Constants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Anton Rooseleer on 1-8-2016.
 */
public class ArtikelListFragment extends Fragment implements android.app.LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.artikel_recycler_view)
    public RecyclerView mRecyclerView;
    private ArtikelAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artikel_list, container, false);
        ButterKnife.bind(this, view);

        mainActivity = (MainActivity) getActivity();
        mainActivity.hideActionBarMenu();;

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ArtikelAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        this.getLoaderManager().initLoader(Constants.LOADER_ARTICLES, null, this);

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch(id){
            case Constants.LOADER_ARTICLES:
                Uri uri10 = ArticleContentProvider.CONTENT_URI;
                return new CursorLoader(this.getActivity(), uri10, null, null, null, "");
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch(loader.getId()){
            case Constants.LOADER_ARTICLES:
                List<Article> articles = Article.constructListFromCursor(data);
                mAdapter.setArtikels(articles);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
}
