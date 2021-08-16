import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SiteCrawler {

	private static final int MAX_PAGES_TO_SEARCH = 10; // limit the amount of pages
	private Set<String> pagesVisited = new HashSet<String>(); // uses a set so no duplicate for pages
	private List<String> pagesToVisit = new LinkedList<String>(); // store URLs to visit (breadth-first approach)

	// Main launching point for SiteCrawler functionality. Internally creates
	// extensions that
	// make an HTTP request and parse the response (the web url).

	private String nextURL() {
		// gets first entry from pagesToVisit to make sure the URL hasn't been visited
		// already,
		// then return it. It will loop the list of pagesToVisit and return the next
		// URL.
		String nextURL;
		do {
			nextURL = this.pagesToVisit.remove(0);
		} while (this.pagesVisited.contains(nextURL));
		this.pagesVisited.add(nextURL);
		return nextURL;
	}

	public void search(String url, String searchWord) {

		while (this.pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
			String currentUrl;
			SiteCrawlerExtension crawl = new SiteCrawlerExtension();
			if (this.pagesToVisit.isEmpty()) {
				currentUrl = url;
				this.pagesVisited.add(url);
			} else {
				currentUrl = this.nextURL();
			}
			crawl.extension(currentUrl);
			boolean success = crawl.searchForWord(searchWord);
			if (success) {
				System.out.println(String.format("**Success** Word %s", searchWord, currentUrl));
				break;
			}
			this.pagesToVisit.addAll(crawl.getLinks());
		}
		System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
	}

	private String nextUrl() {
		// Returns the next URL to visit (in order they were found). Also do a check to
		// make sure the method doesn't return
		// a URL that has already been visited.
		String nextUrl;
		do {
			nextUrl = this.pagesToVisit.remove(0);
		} while (this.pagesVisited.contains(nextUrl));
		this.pagesVisited.add(nextUrl);
		return nextUrl;
	}

}
