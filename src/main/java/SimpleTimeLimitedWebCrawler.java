import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTimeLimitedWebCrawler {
    private static final int MAX_THREADS = 10;
    private static final int CRAWLING_DURATION_MINS = 1;
    private static final Pattern LINK_PATTERN = Pattern.compile("href=\"(https?://[^\"]+)\"");

    private final Set<String> visitedUrls = ConcurrentHashMap.newKeySet();
    // The Queue Buffer for discovered URLs
    private final BlockingQueue<String> urlQueue = new LinkedBlockingQueue<>();

    private final ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    public void start(List<String> seedUrls) {
        urlQueue.addAll(seedUrls);

        // Schedule shutdown
        scheduler.schedule(this::shutdown, CRAWLING_DURATION_MINS, TimeUnit.MINUTES);
        System.out.printf("Crawler started. Terminates in %d min or at %d URLs...%n",
                CRAWLING_DURATION_MINS, visitedUrls.size());

        // Start worker threads (Consumers)
        for (int i = 0; i < MAX_THREADS; i++) {
            executor.execute(this::workerLoop);
        }
    }

    private void workerLoop() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                // Poll with timeout to allow graceful exit if queue stays empty
                String url = urlQueue.poll(1, TimeUnit.SECONDS);
                if (url != null && visitedUrls.add(url)) {
                    System.out.println("Crawling (" + visitedUrls.size() + "): " + url);
                    fetchAndParse(url);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void fetchAndParse(String urlString) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .header("User-Agent", "Mozilla/5.0 (2026 Crawler)")
                    .GET().build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HttpURLConnection.HTTP_OK) {
                Matcher matcher = LINK_PATTERN.matcher(response.body());
                while (matcher.find()) {
                    String foundUrl = matcher.group(1);
                    // Add to buffer if not already visited
                    if (!visitedUrls.contains(foundUrl)) {
                        urlQueue.offer(foundUrl);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Failed " + urlString + ": " + e.getMessage());
        }
    }

    private void shutdown() {
        System.out.println("\nTime limit reached. Total URLs: " + visitedUrls.size());
        executor.shutdownNow();
        scheduler.shutdown();
    }

    public static void main(String[] args) {
        new SimpleTimeLimitedWebCrawler().start(List.of("https://www.bloomberg.com", "https://en.wikipedia.org", "https://www.yahoo.com", "https://www.bbc.com/persian"));
    }
}
