package com.yx.crawler;

import com.yx.model.Instruments;
import com.yx.service.EnterpriseService;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class EnterpriseCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(htm|html))$");
    private final static String PREFIX_URL_DETAIL = "http://ylqx.qgyyzs.net/business/zs";
    EnterpriseService crawService = new EnterpriseService();
    static List<String> list = null;

    static {
        list = new ArrayList<String>();
        list.add("http://ylqx.qgyyzs.net/business/zs210934.htm");
        list.add("http://ylqx.qgyyzs.net/business/zs210930.htm");
        list.add("http://ylqx.qgyyzs.net/business/zs210928.htm");
        list.add("http://ylqx.qgyyzs.net/business/zs210920.htm");
        list.add("http://ylqx.qgyyzs.net/business/zs210919.htm");
        list.add("http://ylqx.qgyyzs.net/business/zs210918.htm");
        list.add("http://ylqx.qgyyzs.net/business/zs210916.htm");
        list.add("http://ylqx.qgyyzs.net/business/zs210911.htm");
        list.add("http://ylqx.qgyyzs.net/business/zs210910.htm");
        list.add("http://ylqx.qgyyzs.net/business/zs210907.htm");
    }

    /**
     * 这个方法有两个参数。第一个参数是我们发现的新的URL的页面并且第二个参数是新的URL。
     * 　你应该实现这个方法去指定这个被给的URL是不是应该去爬取。在这个例子中，我们指导
     * 爬虫去忽视有CSS，JS，git等的URL并且知识获得了以“http://www.ics.uci.edu/”
     * 开头的URL。在这种情况下，我们不需要用参考页面这个参数来做决定。
     * <p>
     * 这个方法决定了要抓取的URL及其内容，
     * 例子中只允许抓取“www.ics.uci.edu”这个域的页面，不允许.css、.js和多媒体等文件。
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();//根据链接爬取的页面
        // 上述格式的文件不爬取
        if (!FILTERS.matcher(href).matches() || !href.startsWith(PREFIX_URL_DETAIL)) {
            return false;
        }
        if (list.contains(href)) {
            return false;
        }
        Instruments instruments =null;
        try {
           /* if(crawService.isExisted(href)){
                return false;
            }*/
            System.out.println(href);
            instruments = crawlerStart(href);
            //写入数据库
           if(!crawService.insert(instruments)) {
              crawService.writeLog(instruments.toString(),"",href);
           }
        } catch (Exception e) {
            crawService.writeLog(instruments.toString(),e.getMessage(),href);
        }

        return true;
    }

    /**
     * 这个功能是抓取准备被你的项目处理的页面
     * 当URL下载完成会调用这个方法。你可以轻松获取下载页面的url, 文本, 链接, html,和唯一id等内容。
     */
    @Override
    public void visit(Page page) {//controller中指定的页面
    }

    //抓取处理
    private Instruments crawlerStart(String href) {
        Document doc = null;
        try {
            doc = crawService.connect(href);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Instruments instrument = new Instruments();
        Elements contents = doc.select("div.r_mina");
        if (contents == null || contents.size() <= 0) {
            return null;
        }
        Element elementDiv = contents.get(0);
        instrument.setfCompanyId(crawService.getCompanyId(elementDiv));//公司url地址
        instrument.setfProductId(crawService.getProductId(href)); //产品ID （url后的数字）
        instrument.setfName(crawService.getProductName(elementDiv));//产品名称

        Instruments ins_main = crawService.getMainInfo(elementDiv);
        if (ins_main != null) {
            instrument.setfCode(ins_main.getfCode());//批准文号
            instrument.setfArea(ins_main.getfArea());//招商区域
        }

        instrument.setfTag(crawService.getTag(elementDiv));//产品标签
        instrument.setfFeature(crawService.getFeature(elementDiv));//产品卖点
        instrument.setfDepartment(crawService.getDepartment(elementDiv));//科室
        instrument.setfUpdateDate(crawService.getUpdateTime(elementDiv));//刷新时间

        Instruments ins_catalog = crawService.getCatalogy(elementDiv);
        if (ins_catalog != null) {
            instrument.setfCategory(ins_catalog.getfCategory());    //器械类别
            instrument.setfCategory2(ins_catalog.getfCategory2());//耗材类别
            instrument.setfFunction(ins_catalog.getfFunction());// 功能
            instrument.setfCatelog(ins_catalog.getfCatelog());//标准目录
            instrument.setfOtherCategory(ins_catalog.getfOtherCategory());//其他类别
        }
        //联系信息
        Instruments ins_contact = crawService.getContactInfo(doc);
        if (ins_contact != null) {
            instrument.setfCompany(ins_contact.getfCompany());    //单位名称
            instrument.setfCompanyAddr(ins_contact.getfCompanyAddr());//公司地址
            instrument.setfContact(ins_contact.getfContact());// 联系人
            instrument.setfTel(ins_contact.getfTel());//公司电话
            instrument.setfPhone(ins_contact.getfPhone());//联系手机
            instrument.setfQQ(ins_contact.getfQQ());// 联系ＱＱ
            instrument.setfRemark(ins_contact.getfRemark());//传　　真
        }
        instrument.setfUseForFamily("");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sdf.format(new Date());
        instrument.setfDate(date);//创建时间
        instrument.setfUrl(href);//创建时间
        instrument.setfSource("qgyyzs");//创建时间

        return instrument;
    }


}
