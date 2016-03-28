package text_editor;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.undo.*;
import javax.swing.text.*;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.*;
import javax.swing.event.*;

class Text extends JFrame implements ActionListener
{
	JEditorPane editorPane;
	JFontChooser jf;
	JColorChooser jc;
	int count =0 ;
	JTabbedPane pane = new JTabbedPane();
	JScrollPane scrollPane;
	File open ;
	JDialog find;
	//JDialog replace;
	JLabel findLab;
	JLabel findAndReplace;
	JButton findBut;
	JButton repBut;
	JOptionPane about;
	JOptionPane textNotFound;
	File file = new File("Untitled.txt");
	 Text()
	 {
		 JMenuBar menubar = new JMenuBar();
		 JMenu file = new JMenu(" File "); // file menu
		 JMenu edit = new JMenu(" Edit "); // Edit menu
		 JMenu format = new JMenu(" Format "); // Tools menu
		 JMenu help = new JMenu(" Help "); // Help menu
		 JMenu color = new JMenu("Color");
		 file.setMnemonic(KeyEvent.VK_F);
		 edit.setMnemonic(KeyEvent.VK_E);
		 format.setMnemonic(KeyEvent.VK_T);
		 help.setMnemonic(KeyEvent.VK_H);
		 JMenuItem FileMenuItems[] = 
		 {
				new JMenuItem("New"),
				new JMenuItem("Open"),
				new JMenuItem("Save"),
				new JMenuItem("Save As"),
				new JMenuItem("Exit")
		 };
		 JMenuItem EditMenuItems[] = 
		 {
				new JMenuItem("Cut"),
				new JMenuItem("Copy"),
				new JMenuItem("Paste")	,
				new JMenuItem("Find")	,			
				new JMenuItem("Clear All"),
				new JMenuItem("Select All")
		 };
		 JMenuItem bg = new JMenuItem("Background");
		 bg.addActionListener(this);
		 
		 JMenuItem text = new JMenuItem("Text");
		 text.addActionListener(this);
		 
		 color.add(bg);
		 color.add(text);
		 
		 JMenuItem FormatMenuItems[] = 
		 {
				new JMenuItem("Font"),
				color
				
		 };

		 
		 JMenuItem HelpMenuItems[] = 
		 {
				new JMenuItem("About JEditor")			
		 };
		//Add file menu items to File menu
		 int len = FileMenuItems.length;
		 //System.out.println(len);
		 for(int i=0; i<len; i++)
		 {
		 file.add(FileMenuItems[i]);
		 FileMenuItems[i].addActionListener(this);
		 }
		 //Add edit menu items to edit menu
		 len = EditMenuItems.length;
		 for(int i=0; i<len; i++)
		 {
		 edit.add(EditMenuItems[i]);
		 EditMenuItems[i].addActionListener(this);
		 }
		 //Add format menu items to format menu
		 len = FormatMenuItems.length;
		 for(int i=0; i<len; i++)
		 {
		 format.add(FormatMenuItems[i]);
		// FormatMenuItems[i].addActionListener(this);
		 }
		 
		 FormatMenuItems[0].addActionListener(this);
		 //Add help menu items to help menu
		 len = HelpMenuItems.length;
		 for(int i=0; i<len; i++)
		 {
		 help.add(HelpMenuItems[i]);
		 HelpMenuItems[i].addActionListener(this);
		 }
		 edit.setMnemonic(KeyEvent.VK_E);
		 menubar.add(file);
		 menubar.add(edit);
		 menubar.add(format);
		 menubar.add(help);
		 setJMenuBar(menubar);
		 setVisible(true);		
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 setExtendedState(JFrame.MAXIMIZED_BOTH);		 		 
		 editorPane = new JEditorPane();
		 editorPane.setFont(new Font("Times New Roman",Font.PLAIN,20));
		 scrollPane = new JScrollPane(editorPane);		
		 //this.add(scrollPane);		 
		 pane.setVisible(true);
		// pane.addTab("New Tab",editorPane);
		 add(pane);
		 System.out.println("Before inserting tab "+pane.getTabCount());	 
		 //pane.insertTab("New Tab", null, scrollPane, null, pane.getTabCount());
		 pane.insertTab("Untitled", null, scrollPane, null,count++);
		 System.out.println("After inserting tab "+pane.getTabCount()+"count is "+count);
		 ///System.out.println("After inserting tab getting index of it "+pane.get);
			
		// add(editorPane); adding once is enough
		
	 }
/*	 public void stateChanged(ChangeEvent e)
	 {
		 
	 } */
	public void actionPerformed(ActionEvent e) 
	{ 
		 String event = (String)e.getActionCommand();
		 if(event == "New")
		 {
			
			 //Text newfile = new Text();
			 //newfile.setSize(500, 500);
			//JTextArea area = new JTextArea();
			//area.setVisible(true);
			 /*newfile.SwingMenu(); */
			 //JTabbedPane tab = new JTabbedPane();
			 //tab.setVisible(true);
			 System.out.println(pane.getTabCount());
			 //pane.addTab("Untitled ", scrollPane);
			Text newFile = new Text();
			 
			//  pane.insertTab("New Tab ", null, scrollPane, "Untitled ",count++);
		 System.out.println("count is "+count);
		 }
		 if(event == "Open")
		 {
			 JFileChooser choose = new JFileChooser();
			 int retValue = choose.showOpenDialog(this); 
			 if(retValue == JFileChooser.APPROVE_OPTION )
			 {				 
				 open = choose.getSelectedFile();
				 System.out.println("Opening tab count "+pane.getTabCount());
				 pane.setTitleAt(pane.getTabCount()-1, open.getName());				 
				 try
				 {
				 BufferedReader br = new BufferedReader(new FileReader(open));
				 StringBuffer sb = new StringBuffer(); 
				 String str = "";
			        while((str = br.readLine()) != null)
			        {			            
			        	sb.append(str).append("\n");
			        	editorPane.setText(sb.toString());
			        }
			        br.close();
				 }
				 catch(IOException io)				 
				 {
					 
				 }
			 }
		 }
		 if(event == "Save")
		 {
			    BufferedWriter writer;	
			 	if(this.pane.getTitleAt(count-1)=="Untitled")
			 	{
			    
				    try {
				        writer = new BufferedWriter(new FileWriter(this.file));
				        writer.write(this.editorPane.getText());
				        writer.close();
				    }
				    catch (IOException ioe) {
				        this.editorPane.setText("Pardon. Can't write file.");
				    } 
			 	}
			 	else
			 	{
				    try {
				        writer = new BufferedWriter(new FileWriter(open));
				        writer.write(this.editorPane.getText());
				        writer.close();
				    }
				    catch (IOException ioe) {
				        this.editorPane.setText("Pardon. Can't write file.");
				    } 
			 	}
			 
		 }
		 if(event == "Save As")
		 {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
				{
					BufferedWriter writer;
					try 
					{
						writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
						writer.write(this.editorPane.getText());
						writer.close();
					}
					catch (IOException ioe) 
					{
						this.editorPane.setText("Pardon. Can't write file.");
					} 
				}	
		}		 
		 if(event == "Exit")
		 {
			 System.exit(0);
		 }

		 /*if(event=="Redo")
		 {
			 undoManager.redo();
		 }*/
		 if(event=="Cut")
		 {
			 this.editorPane.cut();
		 }
		 if(event=="Copy")
		 {
			 this.editorPane.copy();
		 }
		 if(event=="Paste")
		 {
			 this.editorPane.paste();
		 }
		 if(event=="Clear All")
		 {
			 this.editorPane.setText(null);			 
		 }
		 if(event=="Select All")
		 {
			 this.editorPane.selectAll();			 
		 }
		 if(event=="Text")
		 {
			 
			 Color newColor = JColorChooser.showDialog(
                     Text.this,
                     "Choose Text Color",
                     editorPane.getForeground());
			 if (newColor != null) 
			 {
				 editorPane.setForeground(newColor);
			 }		 
		 }
		 if(event=="Background")
		 {
			 
			 Color newColor = JColorChooser.showDialog(
                     Text.this,
                     "Choose Background Color",
                     editorPane.getBackground());
			 if (newColor != null) 
			 {
				 editorPane.setBackground(newColor);
			 }		 
		 }
		 if(event == "Find")
		 {			 			 
			 find = new JDialog();
			 find.setLayout(new FlowLayout());
			 findLab = new JLabel("Search For ");					 
			 final JTextField search_txt = new JTextField(5);
			/* gbc.gridwidth = 2;
			 gbc.gridx = 1;
			 gbc.gridy = 0;
			 gb.setConstraints(search_txt,gbc); */			 			 			 			 
			 findBut = new JButton("Find");			 			 			 			 			 	 			 		
			 find.add(findLab);
			 find.add(search_txt);
			 find.add(findBut);		 			 			
			 find.setTitle("Find");
			 find.setLocation(550,250);
			 find.setSize(300, 200);
			 find.setVisible(true);
			 findLab.setVisible(true);
			 findBut.addActionListener(new ActionListener()
			 {
				 public void actionPerformed(ActionEvent event)
				 {
					 if(event.getActionCommand()=="Find")
					 {
						 try
						 {
							 PlainDocument document = (PlainDocument) editorPane.getDocument();
							 DefaultHighlighter highlighter = (DefaultHighlighter) editorPane.getHighlighter();
							 DefaultHighlightPainter hPainter = new DefaultHighlightPainter(new Color(0xFFAA00));
							 String str = search_txt.getText();
							 String docText = "";
							 docText = document.getText(0, document.getLength());						 
							 int index = 0;
							 int flag = 0;
							 while(  (index = docText.indexOf(str, index) )!=-1)
							 {
									 highlighter.addHighlight(index, str.length()+index, hPainter);
									 flag = 1;
									 index = index + str.length();
							 }
							 
							 if(flag == 0)
							 {
								 textNotFound = new JOptionPane();
								 String message = "Sorry ! Text not found ";
								 textNotFound.showMessageDialog(find, message, "JEditor", textNotFound.WARNING_MESSAGE);
							 }
								 
							 }
						 
						 catch(BadLocationException ex)
						 {
							 
						 }
		
					 } }
					});
		 	}
		/*	 if(event == "Replace")
			 {
				 System.out.println("hello");
				 JDialog replace = new JDialog();
				 replace.setLayout(new FlowLayout());
				 JLabel searchFor = new JLabel("Search For");
				 findAndReplace = new JLabel("Replace With ");				 			 	 				
				 repBut = new JButton("Replace");				 
				 final JTextField findField = new JTextField(5);
				 final JTextField replaceField = new JTextField(5);
				 replace.add(searchFor);
				 replace.add(findField);
				 replace.add(findAndReplace);
				 replace.add(replaceField);
				 replace.add(repBut);
				 replace.setTitle("Replace");
				 replace.setLocation(550,250);
				 replace.setSize(300, 200);
				 replace.setVisible(true);
				 repBut.addActionListener(new ActionListener()
				 {

				 		public void actionPerformed(ActionEvent e)
				 		{
					 		try
					 		{
					 			PlainDocument document = (PlainDocument) editorPane.getDocument();
								String docText = "";
								docText = document.getText(0, document.getLength());
					 			String find = findField.getText();					 			
					 			int index = 0;					 		
					 			while(  (index = docText.indexOf(find, index) )!=-1)
								{
									editorPane.getDocument().remove(index, find.length() );
									editorPane.getDocument().insertString(index, replaceField.getText(), null);					 			
					 				index = index + find.length();
					 				
								} 				 							 								 
					 		}
					 			//System.out.println(editorPane.getText());
					 		catch(BadLocationException exc)
					 		{
					 			
					 		} 
				 		}
			 		
				 
				 });
			 } */
		 if(event == "Font")
		 {
			 jf = new JFontChooser();
			 jf.show();
			 if(jf.getReturnStatus() == jf.RET_OK){
			        editorPane.setFont(jf.getFont());
			 }
			 jf.dispose();
		 }
		 if(event=="About JEditor")
		 {
			 about = new JOptionPane("");
			 String message = "JEditor 1.1 \n\n\n Developed by Samyu, Deepi";
			 about.showMessageDialog(editorPane,message, "About Jeditor",about.INFORMATION_MESSAGE);
		 }
		    

	
	}
  
}
 public class editor
 {
	 public static void main(String[] args)
	 {
		 Text word = new Text();
	 }
 }