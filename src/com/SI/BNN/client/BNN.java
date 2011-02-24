package com.SI.BNN.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widgetideas.graphics.client.Color;
import com.google.gwt.widgetideas.graphics.client.GWTCanvas;
import com.google.gwt.widgetideas.graphics.client.ImageLoader;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BNN implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	boolean flag = false;
	Label chatHeader = new Label("BNN Chat");
	Label chatHeader1 = new Label("BNN Employ Data");
	AbsolutePanel absolutePanel = new AbsolutePanel();
	// Grid data
	final VerticalPanel gridPanel = new VerticalPanel();
	AbsolutePanel panel = new AbsolutePanel();
	ListBox demoGrid = new ListBox(true);
	final AbsolutePanel minimize_gridPanel = new AbsolutePanel();
	// Chat data
	VerticalPanel verticalPanel = new VerticalPanel();
	HorizontalPanel hp = new HorizontalPanel();
	ListBox lbox = new ListBox(true);
	AbsolutePanel panel1 = new AbsolutePanel();
	final AbsolutePanel minimize_chatPanel = new AbsolutePanel();
	VerticalPanel myVpanel_1 = new VerticalPanel();
	VerticalPanel myVpanel_2 = new VerticalPanel();
	VerticalPanel myVpanel_3 = new VerticalPanel();
	Image img1 = new Image("images/waterfall.jpg");
	Image img2 = new Image("images/fantasy.jpg");
	Image img3 = new Image("images/green.jpg");
	int x = 0, y = 0, top = 0, left = 0;
	final Canvas canvas = new Canvas(400, 400);
	boolean start = false, rotate= false;
	int xCo = 0, yCo = 0;

	public void onModuleLoad() {
		showIncidentsWindow();
	}

	public void showIncidentsWindow() {
		DockPanel dock = new DockPanel();
		dock.setStyleName("cw-DockPanel");
		dock.setSpacing(3);
		dock.add(getHead(), DockPanel.NORTH);
		dock.add(getCenter(), DockPanel.CENTER);
		dock.add(new HTML(""), DockPanel.SOUTH);

		RootPanel.get().add(dock);

	}

	public HorizontalPanel getHead() {
		final HorizontalPanel headerPanel = new HorizontalPanel();
		HorizontalPanel p = new HorizontalPanel();
		headerPanel.setWidth(String.valueOf(Window.getClientWidth() - 80)+ "px");
		headerPanel.setStyleName("hp-headerPanel");
		PushButton lb = new PushButton();
		lb.setText("");
		lb.setSize("70px", "60px");
		lb.setStyleName("bnn-headLabel");
		lb.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				minimize_gridPanel.clear();
				gridPanel.add(panel);
				gridPanel.add(demoGrid);
			}
		});
		PushButton lb1 = new PushButton();
		lb1.setSize("70px", "60px");
		lb1.setStyleName("bnn-headLabelChat");
		lb1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				minimize_chatPanel.clear();
				verticalPanel.add(panel1);
				verticalPanel.add(lbox);
				verticalPanel.add(hp);
			}
		});

		img1.setSize("70px", "50px");
		PushButton btnImg1 = new PushButton(img1);
		btnImg1.setSize("70px", "50px");
		btnImg1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				absolutePanel.add(getImageData1(), 10, 10);
			}
		});

		img2.setSize("70px", "50px");
		PushButton btnImg2 = new PushButton(img2);
		btnImg2.setSize("70px", "50px");
		btnImg2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				absolutePanel.add(getImageData2(), 10, 100);
			}
		});

		img3.setSize("70px", "50px");
		PushButton btnImg3 = new PushButton(img3);
		btnImg3.setSize("70px", "50px");
		btnImg3.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				absolutePanel.add(getImageData3(), 200, 10);
			}
		});

		PushButton btnImg4 = new PushButton("Arrow");
		btnImg4.setSize("70px", "50px");
		btnImg4.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				start = true;
				DOM.setStyleAttribute(canvas.getElement(), "cursor", "pointer");
			}
		});
		
		PushButton btnImg5 = new PushButton("Rotate");
		btnImg5.setSize("70px", "50px");
		btnImg5.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				rotate = true;
			}
		});

		// p.add(lb1);
		// p.add(lb);
		p.add(btnImg1);
		p.add(btnImg2);
		p.add(btnImg3);
		p.add(btnImg4);
		p.add(btnImg5);
		headerPanel.add(p);
		return headerPanel;
	}

	public AbsolutePanel getCenter() {
		absolutePanel.setWidth(String.valueOf(Window.getClientWidth() - 80)
				+ "px");
		absolutePanel.setHeight(String.valueOf(Window.getClientHeight() - 150)
				+ "px");
		absolutePanel.setStyleName("cw-Center");

		// absolutePanel.add(getChatData(), 10, 10);
		// absolutePanel.add(getGridData(), 450, 250);
		absolutePanel.add(getDecoratorPanel(), 10, 10);
		return absolutePanel;
	}
	int imgX=10,imgY=10,arrowX=0,arrowY=0;
	final String[] urls = new String[] {"../images/green.jpg", "../images/arrow.png"};
	public Canvas getDecoratorPanel() {
		canvas.setBackgroundColor(Color.ALPHA_RED);
		ImageLoader.loadImages(urls, new ImageLoader.CallBack() {
			public void onImagesLoaded(ImageElement[] imageElements) {
				ImageElement img = imageElements[0];
				canvas.drawImage(img, imgX, imgY, 150, 150);
			}
		});
		
		canvas.setListener(new MouseListener() {
			public void onMouseUp(Widget sender,final int x,final int y) {
				flag = false;
				if (start) {
					start = false;
					arrowX = x;
					arrowY = y;
					arrowImage();
				}
				DOM.setStyleAttribute(canvas.getElement(), "cursor","Default");
			}

			public void onMouseMove(Widget sender, final int x, final int y) {
				if(x < imgX+150 && x > imgX && y < imgY+150 && y > imgY){
					DOM.setStyleAttribute(canvas.getElement(), "cursor","Move");
					if(flag){
						imgX = x - 100;
						imgY = y - 100;
						canvas.clear();
						mainImage();
						arrowImage();
					}
				}
				else if(x < arrowX+50 && x > arrowX && y < arrowY+50 && y > arrowY){
					DOM.setStyleAttribute(canvas.getElement(), "cursor","Move");
					if(flag){
						arrowX = x - 20;
						arrowY = y - 20;
						canvas.clear();
						mainImage();
						arrowImage();
					}
				}
				else if(rotate){
					canvas.rotate(90);
				}
				else
					DOM.setStyleAttribute(canvas.getElement(), "cursor","Default");
			}

			public void onMouseLeave(Widget sender) {
				flag = false;
			}

			public void onMouseEnter(Widget sender) {
				
			}

			public void onMouseDown(Widget sender, int x, int y) {
				flag = true;
			}
		});
		return canvas;
	}
	public void arrowImage(){
		ImageLoader.loadImages(urls, new ImageLoader.CallBack() {
			public void onImagesLoaded(ImageElement[] imageElements) {
				ImageElement img = imageElements[1];
				canvas.drawImage(img, arrowX, arrowY, 50, 50);
			}
		});
	}
	public void mainImage(){
		ImageLoader.loadImages(urls, new ImageLoader.CallBack() {
			public void onImagesLoaded(ImageElement[] imageElements) {
				ImageElement img = imageElements[0];
				canvas.drawImage(img, imgX, imgY, 150, 150);
			}
		});
	}
	public VerticalPanel getImageData1() {
		final Image img = new Image("images/waterfall.jpg");
		final VerticalPanel mainPanel = new VerticalPanel();
		final DecoratorPanel decPanel = new DecoratorPanel();
		final Label head = new Label("");
		head.setStyleName("panelHead_Drag");
		head.setSize("400px", "20px");
		decPanel.setSize("400px", "300px");
		img.setSize("400px", "300px");
		mainPanel.setSize("400px", "300px");
		mainPanel.setStyleName("mainPanel");
		img.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				flag = true;
			}
		});
		img.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				flag = false;
			}
		});
		img.addMouseOutHandler(new MouseOutHandler() {
			public void onMouseOut(MouseOutEvent event) {
				flag = false;
			}
		});
		img.addMouseMoveHandler(new MouseMoveHandler() {
			public void onMouseMove(MouseMoveEvent event) {
				left = event.getRelativeX(img.getElement());
				top = event.getRelativeY(img.getElement());
				if ((left + 10) > img.getWidth()
						&& (top + 10) > img.getHeight())
					DOM.setStyleAttribute(img.getElement(), "cursor",
							"se-resize");
				else
					DOM
							.setStyleAttribute(img.getElement(), "cursor",
									"Default");
				x = img.getAbsoluteTop();
				y = img.getAbsoluteLeft();
				if (flag) {
					img.setSize(event.getScreenX() - y + 20 + "px", event
							.getScreenY()
							- x - 90 + "px");
					decPanel.setSize(event.getScreenX() - y + 20 + "px", event
							.getScreenY()
							- x - 90 + "px");
					mainPanel.setSize(event.getScreenX() - y + 20 + "px", event
							.getScreenY()
							- x - 90 + "px");
					head.setSize(event.getScreenX() - y + 20 + "px", "20px");
				}
			}
		});
		PickupDragController dragController = new PickupDragController(
				absolutePanel, true);
		dragController.makeDraggable(mainPanel, head);
		decPanel.add(img);
		mainPanel.add(head);
		mainPanel.add(decPanel);
		return mainPanel;
	}

	public VerticalPanel getImageData2() {
		final Image img = new Image("images/fantasy.jpg");
		final VerticalPanel mainPanel = new VerticalPanel();
		final DecoratorPanel decPanel = new DecoratorPanel();
		final Label head = new Label("");
		head.setStyleName("panelHead_Drag");
		head.setSize("400px", "20px");
		decPanel.setSize("400px", "300px");
		img.setSize("400px", "300px");
		mainPanel.setSize("400px", "300px");
		mainPanel.setStyleName("mainPanel");
		img.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				flag = true;
			}
		});
		img.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				flag = false;
			}
		});
		img.addMouseOutHandler(new MouseOutHandler() {
			public void onMouseOut(MouseOutEvent event) {
				flag = false;
			}
		});
		img.addMouseMoveHandler(new MouseMoveHandler() {
			public void onMouseMove(MouseMoveEvent event) {
				left = event.getRelativeX(img.getElement());
				top = event.getRelativeY(img.getElement());
				if ((left + 10) > img.getWidth()
						&& (top + 10) > img.getHeight())
					DOM.setStyleAttribute(img.getElement(), "cursor",
							"se-resize");
				else
					DOM
							.setStyleAttribute(img.getElement(), "cursor",
									"Default");
				x = img.getAbsoluteTop();
				y = img.getAbsoluteLeft();
				if (flag) {
					img.setSize(event.getScreenX() - y + 20 + "px", event
							.getScreenY()
							- x - 90 + "px");
					decPanel.setSize(event.getScreenX() - y + 20 + "px", event
							.getScreenY()
							- x - 90 + "px");
					mainPanel.setSize(event.getScreenX() - y + 20 + "px", event
							.getScreenY()
							- x - 90 + "px");
					head.setSize(event.getScreenX() - y + 20 + "px", "20px");
				}
			}
		});
		PickupDragController dragController = new PickupDragController(
				absolutePanel, true);
		dragController.makeDraggable(mainPanel, head);
		decPanel.add(img);
		mainPanel.add(head);
		mainPanel.add(decPanel);
		return mainPanel;
	}

	public VerticalPanel getImageData3() {
		final Image img = new Image("images/green.jpg");
		final VerticalPanel mainPanel = new VerticalPanel();
		final DecoratorPanel decPanel = new DecoratorPanel();
		final Label head = new Label("");
		head.setStyleName("panelHead_Drag");
		head.setSize("400px", "20px");
		decPanel.setSize("400px", "300px");
		img.setSize("400px", "300px");
		mainPanel.setSize("400px", "300px");
		mainPanel.setStyleName("mainPanel");
		img.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				flag = true;
			}
		});
		img.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				flag = false;
			}
		});
		img.addMouseOutHandler(new MouseOutHandler() {
			public void onMouseOut(MouseOutEvent event) {
				flag = false;
			}
		});
		img.addMouseMoveHandler(new MouseMoveHandler() {
			public void onMouseMove(MouseMoveEvent event) {
				left = event.getRelativeX(img.getElement());
				top = event.getRelativeY(img.getElement());
				if ((left + 10) > img.getWidth()
						&& (top + 10) > img.getHeight())
					DOM.setStyleAttribute(img.getElement(), "cursor",
							"se-resize");
				else
					DOM
							.setStyleAttribute(img.getElement(), "cursor",
									"Default");
				x = img.getAbsoluteTop();
				y = img.getAbsoluteLeft();
				if (flag) {
					img.setSize(event.getScreenX() - y + 20 + "px", event
							.getScreenY()
							- x - 90 + "px");
					decPanel.setSize(event.getScreenX() - y + 20 + "px", event
							.getScreenY()
							- x - 90 + "px");
					mainPanel.setSize(event.getScreenX() - y + 20 + "px", event
							.getScreenY()
							- x - 90 + "px");
					head.setSize(event.getScreenX() - y + 20 + "px", "20px");
				}
			}
		});
		PickupDragController dragController = new PickupDragController(
				absolutePanel, true);
		dragController.makeDraggable(mainPanel, head);
		decPanel.add(img);
		mainPanel.add(head);
		mainPanel.add(decPanel);
		return mainPanel;
	}

	public VerticalPanel getChatData() {
		final Label chatHeader_mini = new Label("BNN Chat");
		Button sendButton = new Button("Send");
		PushButton normalPushButton1 = new PushButton();
		PushButton minimize_chat = new PushButton();
		final PushButton close_chatData = new PushButton();
		final PushButton maximize_chat = new PushButton();
		final TextBox txt = new TextBox();

		verticalPanel.setStyleName("cn-chatdata");
		lbox.addItem("Helloo!!!!!!!!");
		lbox.addItem("Hi!!!!!!!!!!!!");
		lbox.setSize("430px", "200px");
		lbox.setStyleName("listBox");
		txt.setWidth("370px");
		chatHeader.setSize("430px", "17px");
		chatHeader.setStyleName("panelHead");
		chatHeader_mini.setSize("300px", "15px");
		chatHeader_mini.setStyleName("panelHead");

		normalPushButton1.setSize("15px", "15px");
		normalPushButton1.setStyleName("pushButton");
		minimize_chat.setSize("15px", "15px");
		minimize_chat.setStyleName("pushButtonMinimize");
		maximize_chat.setSize("15px", "15px");
		maximize_chat.setStyleName("pushButtonMaximize");
		close_chatData.setSize("15px", "15px");
		close_chatData.setStyleName("pushButton");

		hp.add(txt);
		hp.add(sendButton);

		panel1.add(chatHeader);
		panel1.add(minimize_chat, 395, 1);
		panel1.add(normalPushButton1, 412, 1);

		verticalPanel.add(panel1);
		verticalPanel.add(lbox);
		verticalPanel.add(hp);

		PickupDragController dragController = new PickupDragController(
				absolutePanel, true);
		dragController.makeDraggable(verticalPanel, chatHeader);
		sendButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.err.println(txt.getText());
				lbox.addItem(txt.getText());
				txt.setText("");
				txt.setFocus(true);
			}
		});
		normalPushButton1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				verticalPanel.clear();
			}
		});
		maximize_chat.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				minimize_chatPanel.clear();
				verticalPanel.add(panel1);
				verticalPanel.add(lbox);
				verticalPanel.add(hp);
			}
		});
		close_chatData.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				minimize_chatPanel.clear();
			}
		});
		minimize_chat.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				verticalPanel.clear();
				minimize_chatPanel.add(maximize_chat, 260, 1);
				minimize_chatPanel.add(close_chatData, 280, 1);
				minimize_chatPanel.add(chatHeader_mini);
				RootPanel.get().add(minimize_chatPanel, 50,
						Window.getClientHeight() - 35);
			}
		});
		return verticalPanel;
	}

	public VerticalPanel getGridData() {
		String incident[] = {
				"4/29/10  8:12 NY| Bronx| Bronx| *Mva / Traffic Alert*| E/b Bruckner Expy At Bronx River Ave| Hwy unit o/s has req'ed tow to the scene. Right lane taken out.| NJC012 #300295",
				" 4/29/10  8:11 NY| Kings| Brooklyn| *Evidence Search*| 340 Myrtle Ave| Esu has been req'ed for the search in regard to a past dispute.| NJC012 #300294",
				" 4/29/10  8:07 U/D| NY| Westchester| Cortlandt| *Mva / Multiple Injuries*| U.s. 202 & Arlo Lane| 3 victims going to be transported to hudson valley. Situation is u/c. [lake mohegan fire dist]| NJC012/NYH141/NYH130 #300290",
				" 4/29/10  8:05 NJ| Monmouth| Keyport| *Mva / Entrapment*| New Jersey 36 & Broad St| Pd o/s reqst fd for a door pop.| NJC691/NJC4612/NJC633 #300293",
				" 4/29/10  8:02 MA| Middlesex| Lexington| *Traffic Alert*| Route-2 West Bound Near Pleasant Streeet| Expect delays due to a motor vehicle accident blocking the right lane| MAE383 #300292",
				" 4/29/10  7:58 NY| Suffolk| Bellport| *Working Fire*| 759 Bayview Ave| Fire in a 1 story vacant pd. Hagerman and brookhaven for m/a.| NJC012/NYL341 #300291",
				" 4/29/10  7:33 NY| Rockland| New City| *Overturned Auto*| New York 304 & Congers Rd| Fd has also been req'ed for the extrication.| NJC012 #300289",
				" 4/29/10  7:00 U/D| NY| Bronx| Bronx| *Mva / Traffic Alert*| 66-8897| Cross Bronx Expy W/b At Jerome Ave| Pd rptg all w/b lanes closed at jerome av while vehicle involved in mva is reomoved from the guard rail.| *NJN790 #300287",
				" 4/29/10  6:57 U/D| NY| Queens| Queens| *Evidence Search*| 113 Pct| Sutphin Blvd & 110 Ave| Esu requested for the evidence search regards to a confirmed shooting.| *NJN790 #300286",
				" 4/29/10  5:43 NJ| Statewide| All| *Weather Alert*| Statewide| The nws has issued a red flag warning for all of new jersey from noon through 8 pm this evening. A combination of strong winds...Low relative humidity...And warm temperatures will create explosive fire growth potential.| NJC691 #300285" };
		String data[] = { "Name", "Email id", "Salary" };
		String email[] = { "cmadigan@server.com", "rbrogan@server.com",
				"bsambhus@server.com", "brosenbaum@server.com",
				"fbergeron@server.com" };
		String name[] = { "Charles Madigen", "Ralph Brogan", "Bhushan Sambhus",
				"Betsy Rosenbaum", "Francine Bergeron" };
		String Salary[] = { "26200.00", "13700.00", "8300.00", "7450.00",
				"5350.00" };
		final Label gridHeader_mini = new Label("BNN Employ Data");
		PushButton normalPushButton = new PushButton();
		PushButton minimize_grid = new PushButton();
		final PushButton close_grid = new PushButton();
		final PushButton maximize_grid = new PushButton();

		demoGrid.setStyleName("gridStyle");
		demoGrid.setHeight("180px");
		demoGrid.setWidth("430px");
		for (int i = 0; i < incident.length; i++) {
			demoGrid.addItem(incident[i]);
		}

		chatHeader1.setSize("430px", "17px");
		chatHeader1.setStyleName("panelHead");
		gridHeader_mini.setSize("300px", "15px");
		gridHeader_mini.setStyleName("panelHead");

		normalPushButton.setSize("15px", "15px");
		normalPushButton.setStyleName("pushButton");
		minimize_grid.setSize("15px", "15px");
		minimize_grid.setStyleName("pushButtonMinimize");
		maximize_grid.setSize("15px", "15px");
		maximize_grid.setStyleName("pushButtonMaximize");
		close_grid.setSize("15px", "15px");
		close_grid.setStyleName("pushButton");

		panel.add(chatHeader1);
		panel.add(minimize_grid, 398, 1);
		panel.add(normalPushButton, 414, 1);

		gridPanel.add(panel);
		gridPanel.add(demoGrid);
		final AbsolutePanel pop = new AbsolutePanel();
		final Label h = new Label("Incidents Data");
		final PushButton b = new PushButton();
		final TextArea ta = new TextArea();
		demoGrid.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				pop.clear();
				b.setSize("15px", "15px");
				b.setStyleName("pushButton");
				h.setSize("188px", "15px");
				h.setStyleName("panelHead");

				ta.setText(demoGrid.getItemText(demoGrid.getSelectedIndex()));
				ta.setReadOnly(true);
				pop.setHeight("200px");
				pop.setWidth("200px");

				b.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						pop.clear();
					}
				});
				pop.add(b, 172, 0);
				pop.add(h);
				pop.add(ta);
				absolutePanel.add(pop, gridPanel.getAbsoluteLeft(), 200);
			}
		});
		PickupDragController dragController = new PickupDragController(
				absolutePanel, true);
		dragController.makeDraggable(gridPanel, chatHeader1);

		normalPushButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				gridPanel.clear();
				pop.clear();
			}
		});
		minimize_grid.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				gridPanel.clear();
				minimize_gridPanel.add(gridHeader_mini);
				minimize_gridPanel.add(close_grid, 283, 0);
				minimize_gridPanel.add(maximize_grid, 266, 0);
				RootPanel.get().add(minimize_gridPanel, 400,
						Window.getClientHeight() - 35);
			}
		});
		close_grid.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				minimize_gridPanel.clear();
			}
		});
		maximize_grid.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				minimize_gridPanel.clear();
				gridPanel.add(panel);
				gridPanel.add(demoGrid);
			}
		});
		return gridPanel;
	}

	public class DragPanel extends AbsolutePanel {
		private boolean drag_drop = false;
		private boolean move = false;
		private Element movingPanelElement;

		public void setMovingPanelElement(Element movingPanelElement) {
			this.movingPanelElement = movingPanelElement;
		}

		public DragPanel() {
			super();
			DOM.sinkEvents(this.getElement(), Event.ONMOUSEDOWN
					| Event.ONMOUSEMOVE | Event.ONMOUSEUP | Event.ONMOUSEOVER);

		}

		@Override
		public void onBrowserEvent(Event event) {

			final int eventType = DOM.eventGetType(event);
			if (Event.ONMOUSEOVER == eventType) {

				if (isCursorResize(event)) {
					DOM.setStyleAttribute(this.getElement(), "cursor",
							"s-resize");
				} else if (isCursorMove(event)) {
					DOM.setStyleAttribute(this.getElement(), "cursor", "move");
				} else {
					DOM.setStyleAttribute(this.getElement(), "cursor",
							"default");
				}

			}
			if (Event.ONMOUSEDOWN == eventType) {
				if (isCursorResize(event)) {
					if (drag_drop == false) {
						drag_drop = true;
						DOM.setCapture(this.getElement());
					}
				} else if (isCursorMove(event)) {
					DOM.setCapture(this.getElement());
					move = true;
				}

			} else if (Event.ONMOUSEMOVE == eventType) {
				if (!isCursorResize(event) && !isCursorMove(event)) {
					DOM.setStyleAttribute(this.getElement(), "cursor",
							"default");
				}
				if (drag_drop == true) {
					int absY = DOM.eventGetClientY(event);
					int originalY = DOM.getAbsoluteTop(this.getElement());
					if (absY > originalY) {
						Integer height = absY - originalY;
						this.setHeight(height + "px");
					}
				} else if (move == true) {
					RootPanel.get().setWidgetPosition(this,
							DOM.eventGetClientX(event),
							DOM.eventGetClientY(event));
				}
			} else if (Event.ONMOUSEUP == eventType) {
				if (move == true) {
					move = false;
					DOM.releaseCapture(this.getElement());
				}
				if (drag_drop == true) {
					drag_drop = false;
					DOM.releaseCapture(this.getElement());

				}

			}
		}

		protected boolean isCursorResize(Event event) {
			int cursor = DOM.eventGetClientY(event);
			int initial = this.getAbsoluteTop();
			int height = this.getOffsetHeight();
			if (initial + height - 20 < cursor && cursor <= initial + height)
				return true;
			else
				return false;
		}

		protected boolean isCursorMove(Event event) {
			int cursor = DOM.eventGetClientY(event);
			int initial = movingPanelElement.getAbsoluteTop();
			int height = movingPanelElement.getOffsetHeight();
			if (initial <= cursor && cursor <= initial + height)
				return true;
			else
				return false;
		}
	}

	public final class Canvas extends GWTCanvas {

		private MouseListener listener;

		public Canvas(int x,int y) {
			super(x, y);
			sinkEvents(Event.MOUSEEVENTS);
		}

		@Override
		public void onBrowserEvent(Event event) {
			super.onBrowserEvent(event);
			if (listener != null) {
				int x = event.getClientX() - getAbsoluteLeft();
				int y = event.getClientY() - getAbsoluteTop();
				switch (event.getTypeInt()) {
				case Event.ONMOUSEDOWN:
					listener.onMouseDown(this, x, y);
					break;
				case Event.ONMOUSEMOVE:
					listener.onMouseMove(this, x, y);
					break;
				case Event.ONMOUSEUP:
					listener.onMouseUp(this, x, y);
					break;
				}
			}
		}

		public void setListener(MouseListener listener) {
			this.listener = listener;
		}
	}
}