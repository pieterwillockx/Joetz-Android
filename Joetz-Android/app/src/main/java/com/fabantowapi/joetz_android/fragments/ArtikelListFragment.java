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
import com.fabantowapi.joetz_android.contentproviders.UserContentProvider;
import com.fabantowapi.joetz_android.database.UserTable;
import com.fabantowapi.joetz_android.model.Artikel;
import com.fabantowapi.joetz_android.adapters.ArtikelAdapter;
import com.fabantowapi.joetz_android.utils.Constants;

import java.util.ArrayList;
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
        View view = inflater.inflate(R.layout.fragment_artikel_ljst, container, false);
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

    private List<Artikel> downloadArtikels(){
       //todo laad echte artikels

       List<Artikel> artikels = new ArrayList<>();
        artikels.add(new Artikel(5, "BEESTIG LEUKE VAKANTIES", "Bij JOETZ betaal je geen euro te veel!\n" +
                "\n" +
                "Leden van De VoorZorg provincie Antwerpen kunnen genieten van een tegemoetkoming van de aanvullende verzekering. Deze tegemoetkoming wordt rechtstreeks verrekend op de basisprijs.\n" +
                "Als de betalende ouder lid is van ABVV Antwerpen geeft JOETZ nog een extra korting.\n" +
                "\n" +
                "Voor bepaalde vakanties geeft De VoorZorg provincie Antwerpen een extra tegemoetkoming aan leden met het voorkeurtarief. Ook deze tussenkomst wordt rechtstreeks verrekend op de basisprijs.\n" +
                "\n" +
                "De vakanties van JOETZ vzw zijn fiscaal aftrekbaar. Indien jouw kind jonger is dan 12 jaar en in 2016 deelneemt aan één van onze vakanties, dan ontvang je van ons automatisch in 2017 een fiscaal attest.\n" +
                "\n" +
                "Wil je een vakantie boeken en ze liever niet in één keer betalen? Kies dan voor een afbetalingsplan. Je betaalt voor het vertrek in schijven, zonder dat daar extra kosten aan verbonden zijn. Vraag er naar bij inschrijving.", "http://blog.joetz.be/wp-content/uploads/zee-3IMG_0063.jpg"));
        artikels.add(new Artikel(2, "100% PLEZIERGARANTIE", "Een JOETZ vakantie is gegarandeerd ambiance, vriendschap en plezier. Overgoten met een sausje avontuur. 1 ding is zeker: je verveelt je nooit!\n" +
                "\n" +
                "Kiezen voor JOETZ = kiezen voor kwaliteit\n" +
                "\n" +
                "100% zeker: alle deelnemers en begeleiders zijn verzekerd bij ongevallen.\n" +
                "100% kwaliteit: alle verblijven worden elk jaar gecontroleerd en voldoen in België aan de kwaliteitsnormen opgelegd door de Vlaamse of Waalse overheid.\n" +
                "100% veilig: JOETZ werkt enkel met erkende en gecontroleerde transportfirma’s.\n" +
                "80% gezond: JOETZ staat voor gezonde en evenwichtige voeding. Zelfs met mogelijkheid tot een aangepast voedingspatroon. Al mag er tijdens vakanties ook al eens gezondigd worden.\n" +
                "Verantwoord spelen: Spelen is plezant. En draagt bij tot jouw ontwikkeling.\n" +
                "100% communicatie: altijd bereikbaar in geval van nood.\n", "http://blog.joetz.be/wp-content/uploads/plezier.jpg"));
        artikels.add(new Artikel(3, "X-PLORE AUSTRIA", "Goed uitgeslapen vertrokken we vanochtend om de strijd aan te gaan tegen elkaar met onze lasershoots! Het werd de ideale combinatie van actie en genieten van de prachtige Oostenrijkse natuur. In de namiddag wandelden we (met veel lawaai ;)) naar de minigolf, iedereen mocht horen dat we (nu al!) een geweldige groep zijn! Met de kabelbaan gingen we weer naar boven, maar dit keer gingen we met de glijbanen naar beneden. We sloten deze aangename en sfeervolle namiddag af met een ijsje en/of een drankje in het dorpje. Na het avondeten hielden we het gezellig met wat spelletjes en veeeeeel gelach \uD83D\uDE42\n" +
                "\n" +
                "Verder laten we de foto’s voor zich spreken… \uD83D\uDE09\n" +
                "\n" +
                "Auf wiedersehen!", "http://blog.joetz.be/wp-content/uploads/x_plore_austria_01.jpg"));

        return artikels;
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
                List<Artikel> articles = Artikel.constructListFromCursor(data);

                mAdapter.setArtikels(articles);

                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
