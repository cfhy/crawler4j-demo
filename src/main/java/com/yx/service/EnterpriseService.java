package com.yx.service;

import com.yx.dao.InstrumentDao;
import com.yx.model.Instruments;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: yyb
 * @create: 2017/10/26.
 */
public class EnterpriseService {
    //下载页面
    public Document connect(String href) throws IOException {
        Document doc = Jsoup.connect(href)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0")
                .timeout(10000).get();//取得整个页面内容；

        return doc;
    }

    //获取产品ID
    public Integer getProductId(String url) {
        int result = 0;
        if (StringUtils.isEmpty(url)) return result;

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            result = Integer.parseInt(matcher.group());
        }
        return result;
    }

    //获取公司url地址
    public String getCompanyId(Element elementDiv) {
        String result = "";
        if (elementDiv == null) return result;
        Element aElement = elementDiv.select("dd.h_24.ys_1>a").first();//拿到a标签
        if (aElement != null) {
            String href=aElement.attr("href");
            if(href.startsWith("/")){
                result = "http://ylqx.qgyyzs.net" + aElement.attr("href");
            }else{
                result = "http://ylqx.qgyyzs.net/business/" + aElement.attr("href");
            }
        }
        return result;
    }

    //获取产品名称
    public String getProductName(Element elementDiv) {
        String result = "";
        if (elementDiv == null) return result;
        Element hElement = elementDiv.child(0);//拿到h标签
        if (hElement != null) {
            return hElement.text();
        }
        return result;
    }

    //获取主信息
    public Instruments getMainInfo(Element elementDiv) {
        Instruments instruments=new Instruments();
        instruments.setfArea("");
        instruments.setfCode("");
        if (elementDiv == null) return instruments;
        Elements elements = elementDiv.select(".h_24");//
        if(elements==null ||elements.size()<=0) return instruments;
        for(int i=0;i<elements.size();i++){
            String text= StringUtils.trim(elements.get(i).text());
            if(text.equals("批准文号")){//获取批准文号
               Element element= elements.get(i).nextElementSibling();
               if(element==null)return instruments;
                instruments.setfCode( element.text());
            }else  if(text.equals("招商区域")){//获取招商区域
                Element element= elements.get(i).nextElementSibling();
                if(element==null)return instruments;
                instruments.setfArea( element.text());
            }
        }
        return instruments;
    }

    //获取产品标签
    public String getTag(Element elementDiv) {
        String result = "";
        if (elementDiv == null) return result;
        Elements aElements = elementDiv.select("dd.h_24.ys_2>a");//拿到a标签
        if (aElements != null && aElements.size() > 0) {
            result = aElements.text();
        }
        return result;
    }

    //获取产品卖点
    public String getFeature(Element elementDiv) {
        String result = "";
        if (elementDiv == null) return result;
        Elements elements = elementDiv.select("dd.h_48.ys_3");//拿到dd标签
        if (elements != null && elements.size() > 0) {
            result = elements.get(0).text();
        }
        return result;
    }

    //获取类别
    public Instruments getCatalogy(Element elementDiv) {
        Instruments instruments = new Instruments();
        instruments.setfCategory("");
        instruments.setfFunction("");
        instruments.setfCatelog("");
        instruments.setfCategory2("");
        instruments.setfOtherCategory("");
        if (elementDiv == null) return instruments;
        List<Node> nodes = (elementDiv.select("dd.h_144").get(0)).childNodes();//拿到dd标签
        if (nodes != null && nodes.size() > 0) {
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                if (node instanceof TextNode && StringUtils.contains(((TextNode) node).text(), "一般分类")) {
                    instruments.setfCategory(getCatalog(node));//器械类别
                } else if (node instanceof TextNode && StringUtils.contains(((TextNode) node).text(), "按功能分")) {
                    instruments.setfFunction(getCatalog(node)); //功能
                } else if (node instanceof TextNode && StringUtils.contains(((TextNode) node).text(), "标准目录")) {
                    String str = ((TextNode) node).text().substring(6);
                    instruments.setfCatelog(str);//标准目录
                } else if (node instanceof TextNode && StringUtils.contains(((TextNode) node).text(), "耗材类别")) {
                    instruments.setfCategory2(getCatalog(node));//耗材类别
                } else if (node instanceof TextNode && StringUtils.contains(((TextNode) node).text(), "分类标签")) {
                    instruments.setfOtherCategory(getCatalog(node));//其他类别
                }
            }
        }
        return instruments;
    }

    //获取科室
    public String getDepartment(Element elementDiv) {
        String result = "";
        if (elementDiv == null) return result;
        Element element = elementDiv.select("dt.h_144").first();
        element = element.previousElementSibling();
        if (element == null) return result;
       Elements elements = element.children();
        if (elements != null && elements.size()>0) {
            result = elements.text();
        }
        return result;
    }

    //获取联系信息
    public Instruments getContactInfo(Element elementDiv) {
        Instruments  instruments = new Instruments();
        instruments.setfCompany("");
        instruments.setfCompanyAddr("");
        instruments.setfContact("");
        instruments.setfTel("");
        instruments.setfPhone("");
        instruments.setfQQ("");
        instruments.setfRemark("");
        if (elementDiv == null) return instruments;
        Elements elements = elementDiv.select("div.r_list_view>h2>span");
        if (elements == null || elements.size() <= 0) return instruments;
        Element element = null;
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).text().equals("联系方式")) {
                element = elements.get(i).parent().parent();
                break;
            }
        }
        if (element == null) return instruments;
        //获取dd
        Element ddElement = element.getElementsByTag("dd").first();
        if (ddElement == null) return instruments;
        //获取所有子节点
        List<Node> nodes = ddElement.childNodes();
        if (nodes == null || nodes.size() <= 0) return instruments;
        //获取数据
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i) instanceof TextNode) {
                //获取文本
                String text = StringUtils.trim(((TextNode) nodes.get(i)).text());
                if (StringUtils.contains(text, "单位名称")) {
                    instruments.setfCompany(text.substring(6));//公司名称
                } else if (StringUtils.contains(text, "联系地址")) {
                    instruments.setfCompanyAddr(text.substring(6));//公司地址
                } else if (StringUtils.contains(text, "联 系 人")) {
                    instruments.setfContact(text.substring(7));//联系人
                } else if (StringUtils.contains(text, "联系电话")) {
                    String text1 = StringUtils.trim(((TextNode) nodes.get(i)).text());
                    int index = text1.indexOf('（'); //找到（的位置
                    if (index != -1) {
                        instruments.setfTel(StringUtils.trim(text1.substring(5, index)));//公司电话
                    }
                } else if (StringUtils.contains(text, "联系手机")) {
                    instruments.setfPhone(StringUtils.trim(text.substring(5)));//联系手机
                } else if (StringUtils.contains(text, "联系ＱＱ")) {
                    instruments.setfQQ(StringUtils.trim(text.substring(5)));//联系ＱＱ
                } else if (StringUtils.contains(text, "传　　真")) {
                    instruments.setfRemark(StringUtils.trim(text.substring(5)));//传　　真
                }
            }
        }
        return instruments;
    }

    //获取刷新时间
    public String getUpdateTime(Element elementDiv) {
        String result = "";
        if (elementDiv == null) return result;
        Element span = elementDiv.getElementsByTag("span").first();
        if (span == null) return result;
        Pattern pattern = Pattern.compile("\\d+/\\d+/\\d+ \\d+:\\d+:\\d+");
        Matcher matcher = pattern.matcher(span.text());
        if (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }

    //获取分类，用空格分隔
    private String getCatalog(Node currNode) {
        String str = "";
        Node nextNode = currNode.nextSibling();
        while (nextNode != null) {
            if (nextNode instanceof TextNode) {
                String text = ((TextNode) nextNode).text();
                if (!StringUtils.trim(text).equals("") && !StringUtils.trim(text).equals("/")){
                    break;
                }
            }
            if (nextNode.hasAttr("href")) {
                str += " " + ((Element) nextNode).text();
            }
            nextNode = nextNode.nextSibling();
        }
        return StringUtils.trim(str);
    }

    public boolean insert(Instruments instruments) throws SQLException {
        return  new InstrumentDao().insert(instruments);
    }

    public boolean isExisted(String url) throws SQLException {
        return  new InstrumentDao().isExisted(url);
    }

    public void writeLog(String Instruments,String message,String href){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("D:\\log\\log.txt"), true));
            writer.write("\n" + Instruments+"   "+message + "  " + href);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
