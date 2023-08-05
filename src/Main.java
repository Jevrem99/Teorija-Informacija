import java.io.*;

public class Main {

    public static void main(String []args) throws IOException {
        String ulazniFajlPutanja;
        String izlazniFajlPutanja;
        String hufmanKodiranTekst;
        String hufmanDekodiranTekst;
        int k;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8")); //Da bi se u konzoli izstampao znak ∈
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.print("Uneti putanju ulaznog fajla sa tekstom: ");
        ulazniFajlPutanja = reader.readLine();
        System.out.print("\nUneti putanju izlaznog fajl: ");
        izlazniFajlPutanja = reader.readLine();
        System.out.print("\nUneti duzinu k-torke za analizu (k∈N): ");
        k = Integer.parseInt(reader.readLine());

        Pretproces p = new Pretproces(ulazniFajlPutanja,izlazniFajlPutanja);
        RaspodelaSimbola raspodelaSimbola = new RaspodelaSimbola(k,p.pretprocesiraj());
        raspodelaSimbola.izbrojiKTorke();
        raspodelaSimbola.odrediVerovatnoceIEntropije();
        raspodelaSimbola.ispisi();

        HuffmanovAlgoritam algoritam = new HuffmanovAlgoritam(raspodelaSimbola.getkTorke(), raspodelaSimbola.getBrojPojavljivanja());
        System.out.println(algoritam.kodiraj(p.getIzlazniTekst(), k));
        /*algoritam.napraviStablo(raspodelaSimbola.getkTorke(), raspodelaSimbola.getBrojPojavljivanja());
        hufmanKodiranTekst = algoritam.kodirajText(p.getUlazniTekst(),raspodelaSimbola.getkTorke(),raspodelaSimbola.getBrojPojavljivanja());
        System.out.println(hufmanKodiranTekst);
        hufmanDekodiranTekst = algoritam.dekodirajTekst(hufmanKodiranTekst);
        System.out.println(hufmanDekodiranTekst);*/
    }
}
