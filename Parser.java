public static String getXPathValue(String xml, String XpathString)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		NodeList result = getXPathList(xml, XpathString);

		if (result.item(0) == null) {
			throw new XPathExpressionException("Xpath not found");
		} else {
			return result.item(0).getTextContent();
		}
	}

	public String getXPathAttribute(String xml, String attribute, String XpathString)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		NodeList result = getXPathList(xml, XpathString);

		if (result.item(0) == null) {
			throw new XPathExpressionException("Xpath not found");
		} else {
			return result.item(0).getAttributes().getNamedItem(attribute).getTextContent();
		}
	}

	public static NodeList getXPathList(String xml, String XpathString)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(XpathString);
		return (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
	}
