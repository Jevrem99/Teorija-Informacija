import java.util.LinkedList;
import java.util.PriorityQueue;

public class Cvor implements Comparable<Cvor>{

    int brojPojavljivanja;
    double verovatnoca;
    String simbol;
    Cvor levi;
    Cvor desni;
    Cvor roditelj;
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


    @Override  //Potrebno zbog offer u PriorityQueue
    public int compareTo(Cvor o) {
        if(this.brojPojavljivanja > o.brojPojavljivanja)
            return 1;
        else if(this.brojPojavljivanja < o.brojPojavljivanja)
            return -1;
        return 0;
    }


}
