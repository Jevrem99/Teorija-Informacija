import java.util.LinkedList;
import java.util.PriorityQueue;

public class Cvor implements Comparable<Cvor>{

    int brojPojavljivanja;
    double verovatnoca;
    String simbol;
    Cvor levi;
    Cvor desni;
    String kod = "";


    public Cvor(int brojPojavljivanja, String simbol) {
        this.brojPojavljivanja = brojPojavljivanja;
        this.simbol = simbol;
    }

    public Cvor(int brojPojavljivanja, Cvor levi, Cvor desni) {
        this.brojPojavljivanja = brojPojavljivanja;
        this.levi = levi;
        this.desni = desni;
    }

    public Cvor(int brojPojavljivanja,String simbol ,Cvor levi, Cvor desni) {
        this.brojPojavljivanja = brojPojavljivanja;
        this.simbol = simbol;
        this.levi = levi;
        this.desni = desni;
    }

    public boolean daLiJeList()
    {
        if(levi == null && desni == null)
            return true;
        return false;
    }


    @Override
    public int compareTo(Cvor o) {
        if(this.brojPojavljivanja > o.brojPojavljivanja)
            return 1;
        else if(this.brojPojavljivanja < o.brojPojavljivanja)
            return -1;
        return 0;
    }

    public void napraviStablo(LinkedList<String> kTorke, LinkedList<Integer> brojPojavljivanja)
    {
        Cvor[] cvorovi = new Cvor[kTorke.size()];

        for (int i = 0; i < kTorke.size(); i++) {
            cvorovi[i] = new Cvor(brojPojavljivanja.get(i), kTorke.get(i));
            cvorovi[i].kod = "";
        }

        PriorityQueue<Cvor> red = new PriorityQueue<>();

        for(Cvor cvor: cvorovi){
            red.offer(cvor);
        }

        while(red.size() > 1)
        {
            Cvor levi = red.poll();
            Cvor desni = red.poll();

            int zbirPojavljivanja = levi.brojPojavljivanja + desni.brojPojavljivanja;
            Cvor noviCvor = new Cvor(zbirPojavljivanja,levi,desni);

            red.offer(noviCvor);
        }

        red.poll();
    }
}
