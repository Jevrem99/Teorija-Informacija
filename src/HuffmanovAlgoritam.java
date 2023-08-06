import javax.swing.text.ComponentView;
import java.util.*;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class HuffmanovAlgoritam {

    Cvor koren;
    LinkedList<String> kTorke;
    LinkedList<Integer> brojPojavljivanja;

    public HuffmanovAlgoritam(LinkedList<String> kTorke, LinkedList<Integer> brojPojavljivanja) {
        koren = null;
        this.kTorke = kTorke;
        this.brojPojavljivanja = brojPojavljivanja;
    }



    public String kodiraj(String text, int k) {
        String kodiranTekst = "";
        napraviStablo();
        Stack<Cvor> stack = new Stack<>();
        String kTorka;
        StringBuilder putanja;
        Cvor trazen;

        //Neelegantno resenje
        for (int i = 0; i < text.length() - k+1; i += k) {
            kTorka = text.substring(i, i + k);
            trazen = nadjiCvor(kTorka);
            putanja = new StringBuilder(nadjiPutanju(trazen));
            putanja.reverse();
            System.out.println(kTorka + ":" + putanja);
            kodiranTekst += putanja;
        }

        if (text.length() % k != 0) {
            kTorka =  text.substring(text.length() - (text.length() % k), text.length()); //Dodavanje poslednje kTorke ako nije obuhvacena
            trazen = nadjiCvor(kTorka);
            putanja = new StringBuilder(nadjiPutanju(trazen));
            putanja.reverse();
            System.out.println(kTorka + ":" + putanja);
            kodiranTekst += putanja;
        }

        return kodiranTekst;
    }

    public String dekodiraj(String kodiranText)
    {
        String text = "";
        Cvor trenutni = koren;

        for(int i = 0;i < kodiranText.length();i++)
        {
            if(kodiranText.charAt(i) == '0')
            {
                trenutni = trenutni.levi;
            }
            else
                trenutni = trenutni.desni;

            if(trenutni.daLiJeList())
            {
                text += trenutni.simbol;
                trenutni = koren;
            }
        }

        return text;
    }

    public Cvor nadjiCvor(String kTorka) {
        Stack<Cvor> stack = new Stack<Cvor>();
        Cvor trenutni = koren;

        while (trenutni != null || !stack.isEmpty())
        {
            while (trenutni !=  null)
            {
                stack.push(trenutni);
                trenutni = trenutni.levi;
            }

            trenutni = stack.pop();

            if(trenutni.daLiJeList() && trenutni.simbol.equals(kTorka)) {
                return trenutni;
            }
            trenutni = trenutni.desni;
        }
        return null;
    }

    public String nadjiPutanju(Cvor nadjen)
    {
        Cvor trenutni = nadjen;
        String putanja = "";
        while(trenutni != koren)
        {
            if(trenutni.roditelj.levi == trenutni)
                putanja += "0";
            else
                putanja += "1";

            trenutni = trenutni.roditelj;
        }

        return putanja;
    }




    public void napraviStablo() {
        Cvor[] cvorovi = new Cvor[kTorke.size()];

        for (int i = 0; i < kTorke.size(); i++) {
            cvorovi[i] = new Cvor(brojPojavljivanja.get(i), kTorke.get(i));
        }

        PriorityQueue<Cvor> red = new PriorityQueue<>();

        for (Cvor cvor : cvorovi) {
            red.offer(cvor);
        }

        while (red.size() > 1) {
            Cvor levi = red.poll();
            Cvor desni = red.poll();
            System.out.println("Izbaceno: " + levi.brojPojavljivanja + " " + desni.brojPojavljivanja);
            int zbirPojavljivanja = levi.brojPojavljivanja + desni.brojPojavljivanja;
            Cvor noviCvor = new Cvor(zbirPojavljivanja, levi, desni);
            levi.roditelj = noviCvor;
            desni.roditelj = noviCvor;

            red.offer(noviCvor);
        }

        koren = red.poll();
    }

}

