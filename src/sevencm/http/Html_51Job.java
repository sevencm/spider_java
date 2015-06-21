package sevencm.http;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sevencm.utils.HtmlElementUtils;

public class Html_51Job {
	/**
	 * 51job处理
	 * @Title: Handle  
	 * @param sourceURLs
	 * @return void
	 */
	public static void Handle(String sourceURLs){
		Document doc = HtmlElementUtils.getURLDocument(sourceURLs);
		Elements elements = HtmlElementUtils.getElements(doc, "jobname");//获取链接地址
		List<String> list = HtmlElementUtils.getURLList(elements);
		if(list.size()>0){
			for(int i=0,len=list.size();i<len;i++){
				long start = System.currentTimeMillis();
				Document infoDoc = HtmlElementUtils.getURLDocument(list.get(i));
				Elements companyInfoElements = HtmlElementUtils.getElements(infoDoc, "jobs_1");//公司信息
				for(Element e : companyInfoElements){
					String companyText = e.text().replace("查看公司简介>>", "").replace("比比你的竞争力", "");
					break;
				}
				Elements companyContantInfoElements = HtmlElementUtils.getElements(infoDoc, "txt_font1");//公司联系方式
				for(Element e : companyContantInfoElements){
					String contantText = e.text().replace("查看地图", "").replace("发送到手机", "");
				}
				long end = System.currentTimeMillis();

				 
			}
		}
	}
	
	/*public static void main(String[] args) {
		System.out.println(Handle("http://search.51job.com/list/030200%252C00,%2B,0200,%2B,8,%2B,%2B,1,%2B.html?lang=c&stype=2&postchannel=0000&funtype_big=0200&postfrom=030200&btnSltPosition=...&image_x=31&image_y=12&specialarea=00"));
	}*/
}
