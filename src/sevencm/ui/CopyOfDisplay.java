package sevencm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sevencm.utils.Constant;
import sevencm.utils.HtmlElementUtils;

public class CopyOfDisplay extends JFrame {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JTextField textField;
	
	private Set<Integer> pageSet = new HashSet<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CopyOfDisplay frame = new CopyOfDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CopyOfDisplay() {
		setTitle(Constant.TITLE_ZL);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);//绝对布局
		
	    JPanel panel = new JPanel();
	    panel.setBorder(new TitledBorder(null,Constant.BORDER_NAME, 4,2, null, null));
	    panel.setBounds(10, 10, 888, 97);
	    contentPane.add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblhttp = new JLabel(Constant.SOURCE_URL);
	    lblhttp.setBounds(10, 43, 102, 15);
	    panel.add(lblhttp);
	    
	    textField = new JTextField();
	    textField.setBounds(110, 40, 655, 21);
	    panel.add(textField);
	    textField.setColumns(10);
	    
	    final JButton btnNewButton = new JButton(Constant.BUTTON_GET);
	    btnNewButton.setBounds(775, 38, 95, 25);
	    panel.add(btnNewButton);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBorder(new TitledBorder(new EtchedBorder(1, null, null), Constant.TITLE_OUTPUT, 4, 2, null, new Color(0, 0, 0)));
	    panel_1.setBounds(10, 117, 888, 246);
	    contentPane.add(panel_1);
	    panel_1.setLayout(null);
	    
	    final JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(10, 25, 868, 155);
	    panel_1.add(scrollPane);
	    final JTextArea textArea = new JTextArea();
	    scrollPane.setViewportView(textArea);
 
	    
	    JButton btnNewButton_1 = new JButton(Constant.BUTTON_COPY);
	    
	    btnNewButton_1.setBounds(769, 197, 95, 25);
	    panel_1.add(btnNewButton_1);
	    JPanel panel_2 = new JPanel();
	    panel_2.setBorder(new TitledBorder(new EtchedBorder(
	      1, null, null), Constant.TITLE_CAL, 
	      4, 2, null, 
	      new Color(0, 0, 0)));
	    panel_2.setBounds(10, 373, 888, 110);
	    contentPane.add(panel_2);
	    panel_2.setLayout(null);
	    
	    final JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBounds(10, 20, 868, 80);
	    panel_2.add(scrollPane_1);
	    final JTextArea textArea_1 = new JTextArea();
	    textArea_1.setEnabled(false);
	    textArea_1.setEditable(false);
	    textArea_1.setBackground(Color.BLACK);
	    scrollPane_1.setViewportView(textArea_1);
	    
	    btnNewButton_1.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        StringSelection selection = new StringSelection(textArea.getText());
	        clipboard.setContents(selection, null);
	        textArea_1.append(Constant.TIP_COYP);
	      }
	    });
	    btnNewButton.addActionListener(new ActionListener()
	    {
	      @SuppressWarnings("rawtypes")
		public void actionPerformed(ActionEvent e)
	      {
	        String getUrl = textField.getText();
	       
	        	Document doc = HtmlElementUtils.getURLDocument(getUrl);
	 	        
	        	Elements pageElement = doc.getElementsByClass("pagesDown");
	 	        Elements currentPageElement = doc.getElementsByClass("current");
	 	        int currentPageLength=0;
	 	        int currentPage = 0;
	 	        for (Element link : currentPageElement) {
	 	        	if(!link.text().equals("全文")){
		 	        	currentPageLength = link.text().length();
		 	        	pageSet.add(Integer.parseInt(link.text()));
		 	        	currentPage = Integer.parseInt(link.text()) +1;
	 	        	}
	 			}
	 	        
	 			for (Element link : pageElement) {
	 				for(Element ee : link.getElementsByTag("a")){
	 					String linkHref = ee.attr("href");
		 				if(linkHref.indexOf("sou")>0){
		 					getUrl = linkHref.substring(0,linkHref.length()-currentPageLength) + currentPage ;
		 					textField.setText(getUrl);
		 					break;
		 				}
	 				}
	 				
	 			}
	 			if(pageSet.contains(currentPage)){
	 				btnNewButton.setEnabled(false);
	 			}
	 			
	 	        
	 			Elements elements = HtmlElementUtils.getElements(doc, "newlist_list_content");//获取链接地址
	 			List<String> list = HtmlElementUtils.getURLList_zhilian(elements);
	 			
	 				for (final String url : list) {
	 					new SwingWorker() {
	 						StringBuilder txt = new StringBuilder();
	 						StringBuilder time = new StringBuilder();
	 						protected Void doInBackground() throws Exception {
	 							long start = System.currentTimeMillis();
	 							Document infoDoc = HtmlElementUtils.getURLDocument(url);
	 							Elements companyInfoElements = HtmlElementUtils.getElements(infoDoc, "company-box");//公司信息
	 							for(Element e : companyInfoElements){
	 								txt.append((e.getElementsByClass("company-name-t").get(0).text()) + "\n");
	 								for(Element ee: e.getElementsByTag("li")){
	 									txt.append(ee.text() + "\n");
	 								}
	 							}
	 							
	 							txt.append("\n");
	 							txt.append("===========================================");
	 							txt.append("\n");
	 							long end = System.currentTimeMillis();
	 							time.append( url + " 用时： " + (end -start) +"ms \n");
	 							return null;
	 						}

	 						protected void done() {
	 							textArea.append(txt.toString());
	 							textArea_1.append(time.toString());
	 						}

	 					}.execute();
	 				}
	        }
	       
	      
	    });
	    
	}

}
