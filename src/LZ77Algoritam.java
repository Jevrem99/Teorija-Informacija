import java.util.ArrayList;

public class LZ77Algoritam {

    String text;
    ArrayList<Integer> udaljenost;
    ArrayList<Integer> brojKaraktera;
    ArrayList<String> karakter;

    public LZ77Algoritam(String text) {
        this.text = text;
    }

    public void kompresujLZ77(int prozor, int maxBuffer)
    {
        udaljenost = new ArrayList<>();
        brojKaraktera = new ArrayList<>();
        karakter = new ArrayList<>();

        int maxSearch = prozor - maxBuffer;
        int index;
        String buffer; //Buffer deo
        String search; //Search deo
        String b; //Deo buffer-a
        int x = 0; //index za liste
        int y = 0;
        boolean nadjen;
        System.out.println("-----------------------------------------------------------");

        index = 0; //Index za prolazak kroz text
        while(index < text.length())
        {
            System.out.println("index = " + index);
            if(index + maxBuffer > text.length())
                buffer = text.substring(index);
            else
                buffer = text.substring(index,maxBuffer + index);
            System.out.println("buffer =" + buffer);
            if(index - maxSearch < 0)
                search = text.substring(0,index);
            else
                search = text.substring(index - maxSearch,index);


            udaljenost.add(0);
            brojKaraktera.add(0);
            karakter.add(null);

            System.out.println("search = " + search);

            for(int i = 1;i <= buffer.length();i++)
            {
                b = buffer.substring(0,i);
                System.out.println("b = " + b);
                y = search.lastIndexOf(b);

                if(y != -1) //Nadjen
                {
                    udaljenost.set(x,search.length()-y);
                    brojKaraktera.set(x,b.length());
                    System.out.println("Nadjen " + b + " u " + search + " na " + y);
                    System.out.println("Dodato <"+ (search.length()-y) + "," + b.length() + ",null>");
                    if(b.length() == buffer.length()) {
                        if(index+buffer.length() > text.length())
                            index = text.length();
                        else
                            index += buffer.length();
                    }
                }
                else //Nije nadjen, dodaje se novi
                {
                    if(b.length() == 1) {
                        System.out.println("Dodato <0,0,"+b+">");
                        karakter.set(x, b);
                        index++;
                    }
                    else
                    {

                        index += b.length() - 1;
                        System.out.println("Index je sad " + index);
                    }
                    break;
                }
            }
            x++;
        }
    }

    public void dekompresujLZ77()
    {
        String desifrovanText = "";
        int j = 0; //Index za tekst
        for(int i=0;i<udaljenost.size();i++)
        {
            if(udaljenost.get(i) == 0) {
                desifrovanText += karakter.get(i);
                j++;
            }
            else
            {
                desifrovanText += desifrovanText.substring(j-udaljenost.get(i),j-udaljenost.get(i) + brojKaraktera.get(i));
                j += brojKaraktera.get(i);
            }
        }

        System.out.println(desifrovanText);
    }

    public void ispisi()
    {
        for(int i = 0;i < udaljenost.size();i++)
        {
            System.out.println("<" + udaljenost.get(i) + "," + brojKaraktera.get(i) + ","+karakter.get(i)+">");
        }
    }
}