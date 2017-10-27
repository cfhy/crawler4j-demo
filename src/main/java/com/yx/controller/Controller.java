package com.yx.controller;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "D:/data/crawl/root";
        int numberOfCrawlers = 7;
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        //Be polite: Make sure that we don't send more than 1 request per second (1000 milliseconds between requests).
        config.setPolitenessDelay(1000);

        //You can set the maximum crawl depth here. The default value is -1 for unlimited depth
        config.setMaxDepthOfCrawling(1);

        // You can set the maximum number of pages to crawl. The default value is -1 for unlimited number of pages
        // config.setMaxPagesToFetch(1000);

        //Instantiate the controller for this crawl.
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

    /*
     * For each crawl, you need to add some seed urls. These are the first
     * URLs that are fetched and then the crawler starts following links
     * which are found in these pages
     */
        //http://ylqx.qgyyzs.net/zs/list_4_0_0_929.htm 尾页
        for (int i = 0; i < 930; i++) {
            String seed = String.format("http://ylqx.qgyyzs.net/zs/list_4_0_0_%s.htm", String.valueOf(i));
            controller.addSeed(seed);
        }
    /*
     * Start the crawl. This is a blocking operation, meaning that your code
     * will reach the line after this only when crawling is finished.
     */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }
}
