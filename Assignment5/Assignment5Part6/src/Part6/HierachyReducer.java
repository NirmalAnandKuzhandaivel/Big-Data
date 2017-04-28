/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part6;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author nirmal
 */
public class HierachyReducer extends Reducer<Text,Text,Text,NullWritable>{
    
    private DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
    private String title=null;
    private ArrayList<String> tags=new ArrayList<String>();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        title=null;
        tags.clear();
        for (Text t:values){
            if(t.charAt(0)=='A'){
                title=t.toString().substring(1).trim();
            }else if(t.charAt(0)=='B'){
                tags.add(t.toString().substring(1).trim());
            }
        }
        
        if(title!=null){
            try {
                String titleWithTagChildren=nestedElements(title,tags);
                context.write(new Text(titleWithTagChildren), NullWritable.get());
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(HierachyReducer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(HierachyReducer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(HierachyReducer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    private String nestedElements(String title, ArrayList<String> tags) throws ParserConfigurationException, SAXException, IOException, TransformerException{
        DocumentBuilder bldr=dbf.newDocumentBuilder();
        Document doc=bldr.newDocument();
        Element titleEl=getXMlElementFromString(title);
        Element toAddTitleEl=doc.createElement("title");
        copyAttributesToElements(titleEl.getAttributes(), toAddTitleEl);
        for(String tag:tags){
            Element tageEl=getXMlElementFromString(tag);
            Element toAddTageEl=doc.createElement("tags");
            copyAttributesToElements(tageEl.getAttributes(), toAddTageEl);
            toAddTitleEl.appendChild(toAddTageEl);
        }
        doc.appendChild(toAddTitleEl);
        return transformDocumentToString(doc);
        
    }
    
    private Element getXMlElementFromString(String xml) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilder dbr=dbf.newDocumentBuilder();
        return dbr.parse(new InputSource(new StringReader(xml))).getDocumentElement();
    }
    
    private void copyAttributesToElements(NamedNodeMap attributes,Element element){
        for(int i=0;i<attributes.getLength();i++){
            Attr tocopy=(Attr)attributes.item(i);
            element.setAttribute(tocopy.getName(), tocopy.getValue());
            
        }
    }
    
    private String transformDocumentToString(Document doc) throws TransformerConfigurationException, TransformerException{
        TransformerFactory tf=TransformerFactory.newInstance();
        Transformer transformer=tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        StringWriter writer=new StringWriter();
        transformer.transform(new DOMSource(doc),new StreamResult(writer));
        return writer.getBuffer().toString().replace("\n|\r","");
        
    }
    
    
    
}
