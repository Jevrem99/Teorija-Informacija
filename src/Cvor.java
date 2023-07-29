public class Cvor implements Comparable<Cvor>{

    int brojPojavljivanja;
    double verovatnoca;
    String simbol;
    Cvor levi;
    Cvor desni;


    public Cvor(int brojPojavljivanja, String simbol) {
        this.brojPojavljivanja = brojPojavljivanja;
        this.simbol = simbol;
    }

    public Cvor(int brojPojavljivanja, Cvor levi, Cvor desni) {
        this.brojPojavljivanja = brojPojavljivanja;
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
}
