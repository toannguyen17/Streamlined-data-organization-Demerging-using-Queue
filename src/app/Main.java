package app;

import java.io.File;
import java.util.Comparator;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Main {
	public static String maleList_xml     = "maleList.xml";
	public static String femaleList_xml   = "femaleList.xml";
	public static String listPesonnel_xml = "Pesonnel.xml";

	public static void main(String[] args) {
		MyQueue<Pesonnel> maleList   = new MyQueue<Pesonnel>();
		MyQueue<Pesonnel> femaleList = new MyQueue<Pesonnel>();

		MyQueue<Pesonnel> listPesonnel = new MyQueue<Pesonnel>();

		Pesonnel male1 = new Pesonnel("Toan",   "male", 17, 2, 1998);
		Pesonnel male2 = new Pesonnel("Nguyen", "male", 8,  8, 1995);
		Pesonnel male3 = new Pesonnel("Linh",   "male", 4,  5, 2001);

		Pesonnel female1 = new Pesonnel("Huong",  "female", 3,  3,  1999);
		Pesonnel female2 = new Pesonnel("An",     "female", 5,  8,  2001);
		Pesonnel female3 = new Pesonnel("Phuong", "female", 23, 10, 2001);

		Pesonnel[] list = {male1, male2, male3, female1, female2, female3};

		for (int i = 0; i < list.length; i++){
			Pesonnel pesonnel = list[i];
			listPesonnel.enqueue(pesonnel);
			if(pesonnel.getSex().equals("male")){
				maleList.enqueue(pesonnel);
			}else{
				femaleList.enqueue(pesonnel);
			}
		}

		saveXML(listPesonnel, listPesonnel_xml);

		// Sort by birt day
		Comparator<Pesonnel> comparator = new Comparator<Pesonnel>() {
			@Override
			public int compare(Pesonnel o1, Pesonnel o2) {
				return o1.getBirthDayTime() - o2.getBirthDayTime();
			}
		};

		if (!maleList.isQueueEmpty()){
			// Sort by birt day
			maleList.sort(comparator);
			saveXML(maleList, maleList_xml);
		}

		if (!femaleList.isQueueEmpty()){
			// Sort by birt day
			femaleList.sort(comparator);
			saveXML(femaleList, femaleList_xml);
		}
	}

	public static void setAttribute(Document doc, Element e, String key, String value){
		Attr attr = doc.createAttribute(key);
		attr.setValue(value);
		e.setAttributeNode(attr);
	};

	public static void saveXML(MyQueue<Pesonnel> list, String file){
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder         = dbFactory.newDocumentBuilder();
			Document doc                     = dBuilder.newDocument();

			// Create root XML
			Element rootElement = doc.createElement("PesonnelList");
			doc.appendChild(rootElement);

			// write male list
			list.forEach((pesonnel)->{
				Element pesonnelEl = doc.createElement("Pesonnel");
				rootElement.appendChild(pesonnelEl);

				// Name
				setAttribute(doc, pesonnelEl, "name", pesonnel.getName());

				// Sex
				setAttribute(doc, pesonnelEl, "sex", pesonnel.getSex());

				// birth day
				setAttribute(doc, pesonnelEl, "birthDay", pesonnel.getBirthBay());

				// birth month
				setAttribute(doc, pesonnelEl, "birthMonth", pesonnel.getBirthMonth());

				// birth year
				setAttribute(doc, pesonnelEl, "birthYear", pesonnel.getBirthYear());
			});

			// write file XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer        transformer        = transformerFactory.newTransformer();
			DOMSource          source             = new DOMSource(doc);
			StreamResult       result             = new StreamResult(new File(file));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
}
