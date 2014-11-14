package org;



import java.util.List;
import java.util.Map;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;


public class HtmlParserSamples{
	private final static String HTML = 
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
            "<head>\n" + 
            "  <title>This is a test page</title>\n" + 
            "</head>\n" +
            "<body>\n" + 
            "  <h1>This is a simple Html page to test:</h1>\n" + 
            "  <table>\n" + 
            "    <tr>\n" + 
            "      <td class='we' name='manoj'>Hello</td>\n" + 
            "      <td>World!</td>\n" + 
            "    </tr>\n" + 
            "  </table>\n" + 
            "</body>\n" + 
            "</html>";
     
    public static void main(String[] args) throws Exception {
       
        parseWithHtmlCleaner();
    }
	
	
private static void parseWithHtmlCleaner() throws XPatherException {
    System.out.println("*** HTMLCLEANER ***");
    HtmlCleaner cleaner = new HtmlCleaner();
    TagNode node = cleaner.clean(HTML);
   
   // System.out.println("Title: " + ((TagNode)(node.evaluateXPath("//title")[0])).getText());
   // System.out.println("H1: " + ((TagNode)(node.evaluateXPath("//h1")[0])).getText());
  
    for (Object o : node.evaluateXPath("//tr")) {
       // System.out.println("TR: " + ((TagNode)(o)).getText());          
    }
    for (Object o : node.evaluateXPath("//td")) {
       // System.out.println("Xpath://td[text()='" + ((TagNode)(o)).getText()+"']");    
       // System.out.println("TD:"+((TagNode)(o)).getAttributes());
        Map<String, String> map = ((TagNode)(o)).getAttributes();
        for (Map.Entry<String, String> entry : map.entrySet()) {
          //  System.out.println("Xpath:"+"//td[@" + entry.getKey() + "='" + entry.getValue()+"']");
        }
       
       // System.out.println(((TagNode)(o)).getParent());
    }
    //System.out.println();
    
    List op=node.getAllElementsList(true);
    for (Object df:op){
    	String tagName=df.toString();
    	 for (Object o : node.evaluateXPath("//"+tagName)) {
    		 System.out.println("-------------Parent---------"+((TagNode)(o)).getParent()+"/Current tagName"+((TagNode)(o)).getName());
    	        System.out.println("Xpath://"+tagName+"[text()='" + ((TagNode)(o)).getText()+"']");    
    	       // System.out.println("TD:"+((TagNode)(o)).getAttributes());
    	        Map<String, String> map = ((TagNode)(o)).getAttributes();
    	        for (Map.Entry<String, String> entry : map.entrySet()) {
    	            System.out.println("Xpath:"+"//"+tagName+"[@" + entry.getKey() + "='" + entry.getValue()+"']");
    	        }
    	       
    	       
    	    }
    	
    }
    
}
}