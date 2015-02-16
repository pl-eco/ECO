package org.dacapo.xalan;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

//count the frequency of each tag
public class ParseAndCount {
	private Map<String, Integer> map = new HashMap<String, Integer>();
	private List<String> workList = new LinkedList<String>();

	public ParseAndCount(String dir) {
		File dirFile = new File(dir);
		for (String fileName : dirFile.list()) {
			if (fileName.endsWith("xml"))
				workList.add(dir + fileName);
		}
	}

	private void treeWalk(Document document) {
		treeWalk(document.getRootElement());
	}

	private void treeWalk(Element element) {
		String name = element.getName();
		if (!map.containsKey(name)) {
			map.put(name, 1);
		} else {
			int count = map.get(name);
			map.put(name, ++count);
		}

		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				treeWalk((Element) node);
			} else if (node instanceof Text) {
			} else {
				// System.err.println("unknown Node: " + node.getClass() + " " +
				// node);
				// System.exit(1);
			}
		}
	}

	public void run() throws FileNotFoundException, DocumentException {
		// read in files

		for (String file : workList) {
			SAXReader reader = new SAXReader();
			reader.setEntityResolver(new IgnoreDTDEntityResolver());
			Document document = reader.read(file);
			treeWalk(document);
		}

		for (String tag : map.keySet()) {
			System.out.println(tag + "," + map.get(tag));
		}
	}

	public static void main(String[] args) throws FileNotFoundException,
			DocumentException {
		new ParseAndCount("./scratch/xalan/").run();
	}
}

class IgnoreDTDEntityResolver implements EntityResolver {
	@Override
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		return new InputSource(new ByteArrayInputStream(
				"<?xml version='1.0' encoding='UTF-8'?>".getBytes()));
	}
}
