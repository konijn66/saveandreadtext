public class Example {
    public static void main(String[] args) {
        String Line="1. The part I want to extract is the following:href = \"http: www.wikipedia.com/Obama/1" ;
        int start=Line.indexOf("http");
        System.out.println(start);

        int end=Line.indexOf("1", start);
        System.out.println(Line.substring(start,end));
    }
}
