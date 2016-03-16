package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class KittyTag extends SimpleTagSupport {

	private String message;
	private int width;
	private int height;

	// StringWriter sw = new StringWriter();

	@Override
	public void doTag() throws JspException, IOException {
		if (message != null) {
			/* Use message from attribute */
			JspWriter out = getJspContext().getOut();
			out.println(message);
		} else {
			/* use message from the body */
			// getJspBody().invoke(sw);
			String url = String.format("http://lorempixel.com/%d/%d/cats", height, width);
			String titleText = String.format("Kitty of size %d X %d", height, width);
			String tagBody = String.format("<img src=\"%s\" height=\"%d\" width=\"%d\" title=\"%s\">", url, height,
					width, titleText);

			getJspContext().getOut().println(tagBody);
		}
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}