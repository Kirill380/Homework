import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;

public class FileList extends Frame implements ActionListener, ItemListener {
	private List list; // For showing content of direction
	private TextField details;// For showing detailed information of file
	private Panel pan;
	private Button up, close;
	private File currentDir;
	private FilenameFilter filter;
	private String[] files;
	private DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
	
	/**
	* Constructor: create GUI and view the beginning directory
	*/
	
	public FileList(String directory, FilenameFilter filter) {
		super("FileList");
		this.filter = filter;

		//???
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});

		list = new List(12, false);
		list.setFont(new Font("MonoSpased", Font.PLAIN, 14));
		list.addActionListener(this);
		list.addItemListener(this);

		details = new TextField();
		details.setFont(new Font("MonoSpased", Font.PLAIN, 12));
		details.setEditable(false);

		pan = new Panel();
		pan.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 5));
		pan.setFont(new Font("MonoSpased", Font.BOLD, 14));

		up = new Button("Up a Directory");
		close = new Button("Close");
		up.addActionListener(this);
		close.addActionListener(this);

		pan.add(up);
		pan.add(close);

		this.add(list, "Center");
		this.add(details, "North");
		this.add(pan, "South");
		this.setSize(500, 350);

		listDirectory(directory);
	}

	/**
	* The method shows all elements of the directory in the List
	*/
	public void listDirectory(String directory) {
		File dir = new File(directory);
		if(!dir.isDirectory()) {
			throw new IllegalArgumentException("FileList: There is not the direction.");
		}
		files = dir.list(filter);
		java.util.Arrays.sort(files);
		list.removeAll();
		list.add("[Up to Parent Directory]");
		for(int i = 0; i < files.length; i++)
			list.add(files[i]);

		this.setTitle(directory);
		details.setText(directory);

		currentDir = dir;
	}
	/**
	* This method of the interface ItemListener  
	*/
	public void itemStateChanged(ItemEvent e) {
		int i = list.getSelectedIndex() - 1;
		if(i < 0) return;
		String filename = files[i];
		File f = new File(currentDir, filename);
		if(!f.exists())
			throw new IllegalArgumentException("FileList: There is neither the direction nor the file.");
		String info = filename;
		if(f.isDirectory()) info += File.separator;
		info += " "+ f.length() + " bytes ";
		info += dateFormatter.format(new java.util.Date(f.lastModified()));
		if(f.canRead()) info += " for reading";
		if(f.canWrite()) info += " for writing";
		
		details.setText(info);
	}

	/**
	* The method of the interface ActionListener is called when 
	* an user do a double click on an element in the List or 
	* click on one of the buttons 
	*/
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == close) this.dispose();
		else if(e.getSource() == up) { up(); }
		else if(e.getSource() == list) {
			int i = list.getSelectedIndex();
			if(i == 0) up();
			else {
				String name = files[i-1];
				File f = new File(currentDir, name);
				String fullname = f.getAbsolutePath();
				if(f.isDirectory()) listDirectory(fullname);
				else new FileViewer(fullname).show(); 
			}
		}
	}

	protected void up() {
		String parent = currentDir.getParent();
		if(parent == null) return;
		listDirectory(parent);
	}

	private static void usage() {
		System.out.println("");
		System.exit(0);
	}

	public static void main(String args[]) throws IOException {
		FileList f;
		FilenameFilter filter = null;
		String directory = null;
		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("-e")) {
				if(++i >= args.length) usage();
				final String suffix = args[i];

				filter = new FilenameFilter() {
					public boolean accept(File dir, String name) {
						if(name.endsWith(suffix)) return true;
						else return (new File(dir, name)).isDirectory();
					}
				};
			}
			else {
				if(directory != null) usage();
				else directory = args[i];
			}
		}
		if(directory == null) directory = System.getProperty("user.dir");
		f = new FileList(directory, filter);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) { System.exit(0); }
		});

		f.show();
	}
}