import java.util.HashMap;
import java.util.Map;

public class LZWAlgoritam {

    Map<String,Integer> recnikKodiranje = new HashMap<>();
    Map<Integer,String> recnikDekodiranje = new HashMap<>();

    public LZWAlgoritam() {
        for(int i = 0;i<255;i++) {
            recnikKodiranje.put("" + (char) i, i);
            recnikDekodiranje.put(i,""+(char)i);
        }
    }

    public String enkodiraj(String text)
    {
        String kodiranTekst = "";
        int index = 0;
        String karakter = "";
        int maxValue = 255;
        String pom = "";
        boolean nadjen = true;

        while(index < text.length())
        {
            System.out.println("*********************************");
            System.out.println("index= " + index);
            nadjen = true;
            for(int i = 1;i < text.length();i++)
            {
                System.out.println("-----------------");
                System.out.println("i = " + i);
                if(index+i < text.length())
                    karakter = text.substring(index,index+i);
                else {
                    karakter = text.substring(index);
                    i = text.length();
                }
                System.out.println("karakter = " + karakter);
                if(!recnikKodiranje.containsKey(karakter)) {
                    nadjen = false;
                    break;
                }

                pom = karakter;
                System.out.println("pom = " + pom);
            }

            kodiranTekst += "<"+recnikKodiranje.get(pom)+">";
            if(!nadjen) {
                recnikKodiranje.put(karakter, ++maxValue);
                System.out.println(karakter + " kodirano kao " + (maxValue));
            }
            index += pom.length();
        }
        System.out.println(kodiranTekst);
        return kodiranTekst;
    }

    public void dekodiraj(String text)
    {
        text = text.substring(1,text.length()-1);
        String []sifreStr = text.split("><");
        int[] sifre = new int[sifreStr.length];

        for(int i = 0;i < sifre.length;i++)
        {
            sifre[i] = Integer.parseInt(sifreStr[i]);
        }

        int maxValue = 255;
        String dekodiranText = recnikDekodiranje.get(sifre[0]);
        String kod;
        String prethodniKod;


        for(int i = 1;i < sifre.length;i++) {

            prethodniKod = recnikDekodiranje.get(sifre[i - 1]);
            System.out.println("prethodni kod = " + prethodniKod);

            if (!recnikDekodiranje.containsKey(sifre[i])) {
                recnikDekodiranje.put(++maxValue, prethodniKod + prethodniKod.substring(0, 1));
                System.out.println("U recnik dodato <"+maxValue+","+prethodniKod+prethodniKod.substring(0,1)+">");
                dekodiranText+= recnikDekodiranje.get(sifre[i]);
            }
            else {
                kod = recnikDekodiranje.get(sifre[i]);
                dekodiranText+=kod;
                System.out.println("kod = " + kod);
                if (kod.length() > 1){
                    recnikDekodiranje.put(++maxValue, prethodniKod + kod.substring(0, kod.length() - 1));

                    System.out.println("U recnik dodato <"+maxValue+","+prethodniKod+kod.substring(0,kod.length()-1)+">");
                }
                else {
                    recnikDekodiranje.put(++maxValue, prethodniKod + kod);

                    System.out.println("U recnik dodato <"+maxValue+","+prethodniKod+kod+">");
                }
            }
        }

        System.out.println(dekodiranText);
    }

}
