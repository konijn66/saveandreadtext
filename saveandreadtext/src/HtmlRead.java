import java.net.*;
import java.io.*;
public class HtmlRead {

    public HtmlRead() {

            try {
                System.out.println();
                URL url = new URL("https://en.wikipedia.org/wiki/Barack_Obama");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(url.openStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    //using to find links (href)
                    // indexOf to find lines
                    //substring to extract quotation from line
                    //contains
                    //indexOf(String href);
                    if (line.contains("http")) {
                        int start = line.indexOf("http");
                        int end = line.indexOf("\"", start);
                        int end2 = line.indexOf("\'", start);
                        if (end != -1) {
                            System.out.println(line.substring(start, end));
                        } else {
                            System.out.println(line.substring(start, end2));
                        }
                    }


                }
                reader.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }


        }



}
