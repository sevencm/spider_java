package sevencm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlUtils {
	/**
	 * 获取网页内容
	 * 
	 * @Title: getHtml
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param url
	 * @param encode
	 * @return
	 * @return String
	 * @throws IOException
	 */
	public static String getHtml(String url, String encode) throws IOException {
		URL dataUrl = new URL(url);
		HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();
		con.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		InputStream is = con.getInputStream();
		StringBuffer stringBuffer = new StringBuffer();
		Reader reader = new InputStreamReader(is, encode);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = null;
		while((line=bufferedReader.readLine())!=null){
			System.out.println(line);
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		/*try {
			getHtml("http://www.imlianai.com", "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/**
		 * 解析51job 搜索地址
		 */
		/*long as = System.currentTimeMillis();
		Document doc = Jsoup.connect("http://search.51job.com/list/030200%252C00,%2B,0200,%2B,8,%2B,%2B,1,%2B.html?lang=c&stype=2&postchannel=0000&funtype_big=0200&postfrom=030200&btnSltPosition=...&image_x=31&image_y=12&specialarea=00").userAgent("Mozilla").get();
		Elements element = doc.getElementsByClass("jobname");
		//System.out.println(element);
		for (Element link : element) {
			String linkHref = link.attr("href");
			
			System.out.println("-------------------------------------");
			Document doc1 = Jsoup.connect(linkHref).userAgent("Mozilla").get();
			Elements element1 = doc1.getElementsByClass("jobs_1");
			for(Element e : element1){
				//System.out.println(e);
				String resText = e.text().replace("查看公司简介>>", "").replace("比比你的竞争力", "");
				System.out.println(resText);
				break;
			}
			
			Elements element2 = doc1.getElementsByClass("txt_font1");
			for(Element e : element2){
				//System.out.println(e);
				String resText = e.text().replace("查看地图", "").replace("发送到手机", "");
				System.out.println(resText);
			}
			//System.out.println("-------------------------------------");
		}
		long ae = System.currentTimeMillis();
		System.out.println((ae-as)/1000);
		
		//翻页
		Elements pageElement = doc.getElementsByClass("orange1");
		for (Element link : pageElement) {
			String linkHref = link.attr("href");
			System.out.println(linkHref);
		}*/
		
		//获取信息
	/*	Document doc1 = Jsoup.connect("http://search.51job.com/job/67985785,c.html").userAgent("Mozilla").get();
		Elements element1 = doc1.getElementsByClass("jobs_1");
		for(Element e : element1){
			System.out.println(e);
			String resText = e.text().replace("查看公司简介>>", "").replace("比比你的竞争力", "");
			//System.out.println(resText);
			
			for(Element ee: e.getElementsByTag("a")){
				System.out.println(ee.text());
				break;
			}
			
			break;
		}
		
		Elements element2 = doc1.getElementsByClass("txt_font1");
		for(Element e : element2){
		//	System.out.println(e);
			String resText = e.text().replace("查看地图", "").replace("发送到手机", "");
		//	System.out.println(resText);
		}
		*/
		
		
		Document doc = Jsoup.connect("http://sou.zhaopin.com/jobs/searchresult.ashx?bj=4010200&sj=008&jl=763&sm=0&p=1").userAgent("Mozilla").get();
		Elements element = doc.getElementsByClass("newlist_list_content");
		for(Element e : element){
			Elements ele = e.getElementsByTag("a");
			for(Element ee : ele){
				String url = ee.attr("href");
				if(url.indexOf("jobs")>0){
					System.out.println(url);
				}
			
			}
		}
		Elements currentPageElement = doc.getElementsByClass("current");
		System.out.println(currentPageElement);
		Document doc1 = Jsoup.connect("http://jobs.zhaopin.com/478344114250138.htm?ssidkey=y&ss=201&ff=03").userAgent("Mozilla").get();
		Elements element1 = doc1.getElementsByClass("company-box");
		for(Element e : element1){
			System.out.println(e.getElementsByClass("company-name-t").get(0).text());
			for(Element ee: e.getElementsByTag("li")){
				System.out.println(ee.text());
			}
		}
	}
}
