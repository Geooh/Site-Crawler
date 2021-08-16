
public class CrawlerTest {

	public static void main(String[] args) {
		SiteCrawler crawler = new SiteCrawler();
		crawler.search("http://arstechnica.com/", "computer");
	}
}
