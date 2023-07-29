import java.io.*;

public class Pretproces {

    private String putanjaUlaz;
    private String putanjaIzlaz;
    private String ulazniTekst;
    private String izlazniTekst;

    public Pretproces(String putanjaUlaz,String putanjaIzlaz) {
        this.putanjaUlaz = putanjaUlaz;
        this.putanjaIzlaz = putanjaIzlaz;
    }

    public String getIzlazniTekst() {
        return izlazniTekst;
    }

    public void setPutanjaIzlaz(String putanjaIzlaz) {
        this.putanjaIzlaz = putanjaIzlaz;
    }

    public void ucitajTekst()
    {
        this.ulazniTekst = "";
        try{
            File fajl = new File(putanjaUlaz);
            BufferedReader reader = new BufferedReader(new FileReader(fajl));

            String line;
            while((line = reader.readLine()) != null)
            {
                this.ulazniTekst+=line;
            }
            reader.close();
        }
        catch (Exception ex)
        {
            System.out.println("Dati fajl ne postoji");
        }
    }

    public void ispisiText()
    {
        try{
            FileWriter writer = new FileWriter(putanjaIzlaz);
            writer.write(izlazniTekst);
            writer.close();
        }catch (Exception ex)
        {
            System.out.println("Greska prilikom ispisa");
        }
    }

    public String pretprocesiraj()
    {
        ucitajTekst();
        izlazniTekst = "";
        for(int i = 0;i < ulazniTekst.length();i++)
        {
            if(daLiJeSlovo(ulazniTekst.charAt(i)))
            {
                izlazniTekst+= ulazniTekst.charAt(i);
            }
        }
        ispisiText();

        return izlazniTekst;
    }

    public boolean daLiJeSlovo(char c)
    {
        if((c >= 65 && c <= 90) | (c >= 97 && c <= 121))
            return true;
        return false;
    }

}
