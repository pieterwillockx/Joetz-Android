package com.fabantowapi.joetz_android.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.fabantowapi.joetz_android.R;
import com.fabantowapi.joetz_android.model.Artikel;
import com.fabantowapi.joetz_android.model.ArtikelAdapter;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Anton Rooseleer on 1-8-2016.
 */
public class ArtikelListFragment extends Fragment {

    private List<Artikel> artikels;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_artikel_list, container, false);

        ButterKnife.bind(this, view);
        downloadArtikels();
        return view;
    }

    private void downloadArtikels(){
       //todo laad echte artikels

        artikels = new ArrayList<>();
        artikels.add(new Artikel(5,"BEESTIG LEUKE VAKANTIES","Bij JOETZ betaal je geen euro te veel!\n" +
                "\n" +
                "Leden van De VoorZorg provincie Antwerpen kunnen genieten van een tegemoetkoming van de aanvullende verzekering. Deze tegemoetkoming wordt rechtstreeks verrekend op de basisprijs.\n" +
                "Als de betalende ouder lid is van ABVV Antwerpen geeft JOETZ nog een extra korting.\n" +
                "\n" +
                "Voor bepaalde vakanties geeft De VoorZorg provincie Antwerpen een extra tegemoetkoming aan leden met het voorkeurtarief. Ook deze tussenkomst wordt rechtstreeks verrekend op de basisprijs.\n" +
                "\n" +
                "De vakanties van JOETZ vzw zijn fiscaal aftrekbaar. Indien jouw kind jonger is dan 12 jaar en in 2016 deelneemt aan één van onze vakanties, dan ontvang je van ons automatisch in 2017 een fiscaal attest.\n" +
                "\n" +
                "Wil je een vakantie boeken en ze liever niet in één keer betalen? Kies dan voor een afbetalingsplan. Je betaalt voor het vertrek in schijven, zonder dat daar extra kosten aan verbonden zijn. Vraag er naar bij inschrijving.","http://blog.joetz.be/wp-content/uploads/zee-3IMG_0063.jpg"));
        artikels.add(new Artikel(2,"100% PLEZIERGARANTIE","Een JOETZ vakantie is gegarandeerd ambiance, vriendschap en plezier. Overgoten met een sausje avontuur. 1 ding is zeker: je verveelt je nooit!\n" +
                "\n" +
                "Kiezen voor JOETZ = kiezen voor kwaliteit\n" +
                "\n" +
                "100% zeker: alle deelnemers en begeleiders zijn verzekerd bij ongevallen.\n" +
                "100% kwaliteit: alle verblijven worden elk jaar gecontroleerd en voldoen in België aan de kwaliteitsnormen opgelegd door de Vlaamse of Waalse overheid.\n" +
                "100% veilig: JOETZ werkt enkel met erkende en gecontroleerde transportfirma’s.\n" +
                "80% gezond: JOETZ staat voor gezonde en evenwichtige voeding. Zelfs met mogelijkheid tot een aangepast voedingspatroon. Al mag er tijdens vakanties ook al eens gezondigd worden.\n" +
                "Verantwoord spelen: Spelen is plezant. En draagt bij tot jouw ontwikkeling.\n" +
                "100% communicatie: altijd bereikbaar in geval van nood.\n","http://blog.joetz.be/wp-content/uploads/plezier.jpg"));
        artikels.add(new Artikel(3,"X-PLORE AUSTRIA","Goed uitgeslapen vertrokken we vanochtend om de strijd aan te gaan tegen elkaar met onze lasershoots! Het werd de ideale combinatie van actie en genieten van de prachtige Oostenrijkse natuur. In de namiddag wandelden we (met veel lawaai ;)) naar de minigolf, iedereen mocht horen dat we (nu al!) een geweldige groep zijn! Met de kabelbaan gingen we weer naar boven, maar dit keer gingen we met de glijbanen naar beneden. We sloten deze aangename en sfeervolle namiddag af met een ijsje en/of een drankje in het dorpje. Na het avondeten hielden we het gezellig met wat spelletjes en veeeeeel gelach \uD83D\uDE42\n" +
                "\n" +
                "Verder laten we de foto’s voor zich spreken… \uD83D\uDE09\n" +
                "\n" +
                "Auf wiedersehen!","http://blog.joetz.be/wp-content/uploads/x_plore_austria_01.jpg"));

        toonArtikelLijst();

    }
    private void toonArtikelLijst(){

        ListView listView = (ListView) view.findViewById(R.id.artikel_list);
        ArtikelAdapter artikelAdapter = new ArtikelAdapter(
                getActivity(), R.layout.fragment_artikel_list_item, artikels.toArray(new Artikel[artikels.size()]));
        listView.setAdapter(artikelAdapter);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                laadArtikel(position);
            }
        };
        listView.setOnItemClickListener(listener);
    }

    public void laadArtikel( int positie){

        Bundle args =new Bundle();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ArtikelDetailFragment artikelDetailFragment = new ArtikelDetailFragment();
        artikelDetailFragment.setArguments(args);

        Artikel artikel = artikels.get(positie);
        getActivity().getIntent().putExtra("artikel", artikel);
        ft.replace(R.id.mainpage_container, artikelDetailFragment);
        ft.commit();

    }
}
