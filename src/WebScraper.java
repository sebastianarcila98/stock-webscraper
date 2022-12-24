import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScraper {

    public static void main(String[] args) {

        final String url = "https://www.slickcharts.com/sp500";

        try {
            final Document document = Jsoup.connect(url).get();

            for (Element row : document.select("table.table.table-hover.table-borderless.table-sm tr")) {
                if (!row.select("td:nth-of-type(5)").text().isEmpty()) {
                    final String ticker = row.select(
                            "td:nth-of-type(3)").text();
                    final String name = row.select(
                            "td:nth-of-type(2)").text();
                    final String tempPrice = row.select(
                            "td.text-nowrap:nth-of-type(5)").text();
                    final String formattedTempPrice = tempPrice.replace(",", "");
                    final double price = Double.parseDouble(formattedTempPrice);

                    System.out.println(ticker + " ---- " + name + " ---- " + price);
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }



    }
}
