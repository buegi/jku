package swe2.ss19.ue03.html;

public class Page {
	private final String title;
	private final Element[] body;

	protected Page(String title, Element[] body) {
		this.title = title;
		this.body = body;
	}

	public String render() {
		StringBuilder html = new StringBuilder();

		html.append("<html>\n  <head>\n    <title>" + this.title + "</title>\n  </head>\n<body>\n");

		for (Element e : body) {
			html.append(Globals.indentLines(e.render()));
		}

		html.append("</body>\n</html>");
		return html.toString();
	}
}