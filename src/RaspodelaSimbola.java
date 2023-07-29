import java.util.LinkedList;

public class RaspodelaSimbola {

    private int k; //Velicina k-torke koja se analizira
    private String text; //text koji se analizira
    private LinkedList<String> kTorke;
    private LinkedList<Integer> brojPojavljivanja;
    private LinkedList<Double> verovatnoce;
    private double entropija = 0.0;

    public RaspodelaSimbola(int k, String text) {
        this.k = k;
        this.text = text;
    }

    public void izbrojiKTorke()
    {
        kTorke = new LinkedList<>();
        brojPojavljivanja = new LinkedList<>();
        String kTorka;
        int index;

        for(int i = 0;i < text.length() - k;i++)
        {

            kTorka = text.substring(i,i+k);

            if((index = kTorke.indexOf(kTorka)) != -1) //K-torka postoji u listi
            {
                brojPojavljivanja.set(index,brojPojavljivanja.get(index)+1);
            }
            else //Nadjena nova kTorka
            {
                kTorke.add(kTorka);
                brojPojavljivanja.add(1);
            }
        }
    }

    public void odrediVerovatnoceIEntropije()
    {
        verovatnoce = new LinkedList<>();

        double verovatnoca;

        for (int i = 0; i < kTorke.size();i++)
        {
            verovatnoca = (double)brojPojavljivanja.get(i)/(double)kTorke.size();
            entropija -= verovatnoca * (Math.log(verovatnoca) / Math.log(2));
            verovatnoce.add(verovatnoca);
        }
    }

    public void ispisi()
    {
        for(int i = 0;i < kTorke.size();i++)
        {
            System.out.println(kTorke.get(i) + " (" + brojPojavljivanja.get(i) + ") (" + verovatnoce.get(i) + ") ");
        }
        System.out.println("Entropija: "+ entropija);
    }
}
