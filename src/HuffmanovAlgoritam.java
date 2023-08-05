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

        for (int i = 0; i < text.length() - k + 1; i += k) {
            String kTorka = text.substring(i, i + k);
            Cvor trazen = nadjiCvor(kTorka);
            //String putanja = nadjiPutanju(cvor);

        }


        return kodiranTekst;
    }

    public Cvor nadjiCvor(String kTorka) {
        Cvor trenutni = koren;
        Stack<Cvor> stack = new Stack<>();

        while (trenutni != null || !stack.isEmpty()) {

            while (trenutni != null) {
                stack.push(trenutni);
                trenutni = trenutni.levi;
            }

            if (!stack.isEmpty()) {
                trenutni = stack.pop();

                System.out.println(trenutni.simbol);
                trenutni = trenutni.desni;
            }
        }
        return null;
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

