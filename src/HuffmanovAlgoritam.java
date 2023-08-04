import java.util.LinkedList;
import java.util.PriorityQueue;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class HuffmanovAlgoritam {

    private Cvor koren = null;
    String[] kodovi = null;


    public String dekodirajTekst(String kodiranText) {
        StringBuilder dekodiranTekst = new StringBuilder();

        Cvor trenutniCvor = koren; // Počinjemo od korena stabla

        for (int i = 0; i < kodiranText.length(); i++) {
            char bit = kodiranText.charAt(i);

            if (bit == '0') {
                trenutniCvor = trenutniCvor.levi;
            } else if (bit == '1') {
                trenutniCvor = trenutniCvor.desni;
            }

            if (trenutniCvor.daLiJeList()) {
                dekodiranTekst.append(trenutniCvor.simbol);
                trenutniCvor = koren; // Vraćamo se na koren za dekodiranje sledećeg simbola
            }
        }

        // Dodajte sledeće linije za čišćenje stabla nakon dekodiranja
        if (trenutniCvor != koren) {
            dekodiranTekst.append(trenutniCvor.simbol);
        }

        return dekodiranTekst.toString();
    }
    public String kodirajText(String text, LinkedList<String> kTorke, LinkedList<Integer> brojPojavljivanja) {
        String kodiranText = "";
        enkoder(kTorke, brojPojavljivanja);

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c) && Character.isUpperCase(c)) {
                kodiranText += kodovi[c - 'A'];
            }
        }

        return kodiranText;
    }


    public void nacrtajStablo(Cvor cvor, String prefiks, boolean poslednji) {
        if (cvor != null) {
            System.out.print(prefiks);
            System.out.print(poslednji ? "└─" : "├─");

            if (cvor.daLiJeList()) {
                System.out.println(cvor.simbol);
            } else {
                System.out.println();
                String noviPrefiks = prefiks + (poslednji ? "  " : "│ ");
                nacrtajStablo(cvor.levi, noviPrefiks, false);
                nacrtajStablo(cvor.desni, noviPrefiks, true);
            }
        }
    }


    public String[] enkoder(LinkedList<String> kTorke, LinkedList<Integer> brojPojavljivanja) {
        kodovi = new String[58];

        for (int i = 0; i < kodovi.length; i++)
            kodovi[i] = "";

        PriorityQueue<Cvor> red = new PriorityQueue<>();
        napraviStablo(kTorke, brojPojavljivanja);
        red.offer(koren);

        while (!red.isEmpty()) {
            Cvor cvor = red.poll();

            if (cvor.simbol != null) {
                int redniBrojSimbola = cvor.simbol.charAt(0) - 65; // Pomeramo za 'A'
                kodovi[redniBrojSimbola] = cvor.kod;
            } else {
                if (cvor.levi != null) {
                    cvor.levi.kod = cvor.kod + "0";
                    red.offer(cvor.levi);
                }
                if (cvor.desni != null) {
                    cvor.desni.kod = cvor.kod + "1";
                    red.offer(cvor.desni);
                }
            }
        }

        // Prikaz generisanih kodova
        System.out.println("--------------------");
        for (int i = 0; i < kodovi.length; i++) {
            if (!kodovi[i].isEmpty()) {
                System.out.println((char) (i + 65) + ": " + kodovi[i]);
            }
        }
        return kodovi;
    }

    public void napraviStablo(LinkedList<String> kTorke, LinkedList<Integer> brojPojavljivanja) {
        Cvor[] cvorovi = new Cvor[kTorke.size()];

        for (int i = 0; i < kTorke.size(); i++) {
            cvorovi[i] = new Cvor(brojPojavljivanja.get(i), kTorke.get(i));
            cvorovi[i].kod = "";
        }

        PriorityQueue<Cvor> red = new PriorityQueue<>();

        for (Cvor cvor : cvorovi) {
            red.offer(cvor);
        }

        while (red.size() > 1) {
            Cvor levi = red.poll();
            Cvor desni = red.poll();

            int zbirPojavljivanja = levi.brojPojavljivanja + desni.brojPojavljivanja;
            Cvor noviCvor = new Cvor(zbirPojavljivanja, null, levi, desni);

            red.offer(noviCvor);
        }

        koren = red.poll();
        nacrtajStablo(koren,"",true);
    }
}

