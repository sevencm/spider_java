package sevencm.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlElementUtils {

	/**
	 * @Description: 获取Document  
	 * @param url
	 * @return Document
	 */
	public static Document getURLDocument(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent(Constant.AGENT).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	/**
	 * 获取Elements
	 * @param doc
	 * @param className
	 * @return Elements
	 */
	public static Elements getElements(Document doc,String className){
		return doc.getElementsByClass(className);
	}
	
	/**
	 * 获取页面链接
	 * @param element
	 * @return
	 * @return List<String>
	 */
	public static List<String> getURLList(Elements element){
		List<String> list = new ArrayList<String>();
		for (Element link : element) {
			String linkHref = link.attr("href");
			list.add(linkHref);
		}
		return list;
	}
	
	
	public static List<String> getURLList_zhilian(Elements element){
		List<String> list = new ArrayList<String>();
		for(Element e : element){
			Elements ele = e.getElementsByTag("a");
			for(Element ee : ele){
				String url = ee.attr("href");
				if(url.indexOf("jobs")>0){
					list.add(url);
				}
			}
		}
		return list;
	}
	
	
}
