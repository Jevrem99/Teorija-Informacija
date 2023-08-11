import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
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

        /*System.out.print("Uneti putanju ulaznog fajla sa tekstom: ");
        ulazniFajlPutanja = reader.readLine();
        System.out.print("\nUneti putanju izlaznog fajl: ");
        izlazniFajlPutanja = reader.readLine();
        System.out.print("\nUneti duzinu k-torke za analizu (k∈N): ");
        k = Integer.parseInt(reader.readLine());*/
        ulazniFajlPutanja = "C:\\Users\\jevre\\Desktop\\Tests\\Test.txt";
        izlazniFajlPutanja = "C:\\Users\\jevre\\Desktop\\Tests\\Test1.txt";
        k = 3;
        Pretproces p = new Pretproces(ulazniFajlPutanja, izlazniFajlPutanja);
        RaspodelaSimbola raspodelaSimbola = new RaspodelaSimbola(k, p.pretprocesiraj());
        raspodelaSimbola.izbrojiKTorke();
        raspodelaSimbola.odrediVerovatnoceIEntropije();
        raspodelaSimbola.ispisi();

        HuffmanovAlgoritam algoritam = new HuffmanovAlgoritam(raspodelaSimbola.getkTorke(), raspodelaSimbola.getBrojPojavljivanja());
        hufmanKodiranTekst = algoritam.kodiraj(p.getIzlazniTekst(),k);
        System.out.println(hufmanKodiranTekst);
        hufmanDekodiranTekst = algoritam.dekodiraj(hufmanKodiranTekst);
        System.out.println(hufmanDekodiranTekst);

        LZ77Algoritam al = new LZ77Algoritam(hufmanKodiranTekst);
        al.kompresujLZ77(15,9);
        al.ispisi();
        al.dekompresujLZ77();
    }
}
