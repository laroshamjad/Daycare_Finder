/*
 * Tuesday, January 18, 2022
 * Description: This program will allow user to locate and find information about a certain childcare center based on their preferences. 
 */ 
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 


class Main implements ActionListener {
  
  //declare variables
  JFrame frame, frameTypes, frameInformation, frameAbout, frameLocation, frameCentreType, frameGenLocation;
  JPanel contentPane;
  JButton specific, general, specificLoc, specificType, generalLoc, generalType;

  //Menu Labels
  JLabel menu, prompt;

  //Specific location labels
  JLabel location;

  //Specific location dropbox
  JComboBox dropBox;

  //Specific type labels
  JLabel centreType;

  //Specific type dropbox
  JComboBox dropBoxType;

  //General location labels
  JLabel genLocation;

  //General location dropbox
  JComboBox dropBoxGen;

  //General/Specific location arrays and variables
  String[] city;
  String[] centre;
  String mun = "";
  String cen = "";
  String locationName = "";

  //Type of center Labels
  JLabel promptType;

  //type of center dropbox
  JComboBox centerNamesType;

  //Drop Down Main Menu Buttons
  JButton specificButton, generalButton, aboutUs;

  //About us Label
  JTextArea about;
  
  //Back label
  JLabel backLabel;

  //Return to Main Menu button
  JButton returnMain;

  //Image Labels
  JLabel peelImage, childImage, image;

  //Global Array
  static String lineOfText;
  static String[][] data = new String [548][18];
  static String stringTypes = "";
  static String[] listOfTypes;
  static int r=0;
  static int i=0;

  //variables declared for filteredDropDownMenu() method
  JFrame frameList;
  JComboBox resultList;
  JLabel filteredNum;
	JLabel listPrompt;
  String[] centerNamesFiltered = {""};
  int counter=0;
  static String namesList="";
  static String listOfTypes2[];

  //variables declared for displayInformation();
  JFrame frameInfo;
  JLabel centerName, phoneNumber, address, website;

  int action=1;

  /**
  * pre: none
  * post: Creates the Main Menu
  */
  public void mainMenu(){
    /* Create and set up the frame */
		frame = new JFrame("Childcare Centre Search");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		/* Create a content pane with a BoxLayout and empty borders */
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    contentPane.setBackground(new Color(134, 195, 242)); //light blue colour

    /* Create and a label that will dsplay Region of Child Care centre logo*/
    childImage = new JLabel(new ImageIcon("CCC.gif"));
    //CCC Image: https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Fillustrations%2Fchild-care-centre&psig=AOvVaw3HTxCcrW7XbLAtz-DjKcbc&ust=1642783517369000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCPiNmuHjwPUCFQAAAAAdAAAAABAJ
    childImage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    childImage.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
    contentPane.add(childImage);

    /* Create and add label for the Main Menu */
		menu = new JLabel("Main Menu");
    Font boldFont = new Font("Serif", Font.BOLD, 20);
    menu.setFont(boldFont);
    menu.setForeground(new Color(12, 59, 95)); // very dark blue
		menu.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    menu.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 100));
		contentPane.add(menu);

		/* Create and add label to direct the user */
		prompt = new JLabel("Select a filter: ");
    Font monospaced = new Font("MONOSPACED", Font.BOLD, 15);
    prompt.setFont(monospaced);
		prompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    prompt.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		contentPane.add(prompt);

		/* Create and add a button that will display the specific pathway*/
    specificButton = new JButton("Specific");
    specificButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    specificButton.setActionCommand("Specific");
    specificButton.addActionListener(this);
    contentPane.add(specificButton);

    /* Create and add a button that will display the general pathway*/
    generalButton = new JButton("General");
    generalButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    generalButton.setActionCommand("General");
    generalButton.addActionListener(this);
    contentPane.add(generalButton);

    /* Create and add a button that will display the about us information*/
    aboutUs = new JButton("About Us");
    aboutUs.setAlignmentX(JButton.CENTER_ALIGNMENT);
    aboutUs.setActionCommand("About Us");
    aboutUs.addActionListener(this);
    contentPane.add(aboutUs);

    /* Create a label that notifies the user to return to the menu */
		backLabel = new JLabel("Return to Main Menu");
		backLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    Font monospacedSmall = new Font("MONOSPACED", Font.BOLD, 10);
    backLabel.setFont(monospacedSmall);
    backLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		contentPane.add(backLabel);

    /* Create and add a button that will display the general pathway*/
    returnMain = new JButton(new ImageIcon("return.gif"));
    returnMain.setAlignmentX(JButton.CENTER_ALIGNMENT);
    //back.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    returnMain.setActionCommand("Back");
    returnMain.addActionListener(this);
    contentPane.add(returnMain);

    /* Create and a label that will dsplay Region of Peel logo*/
    peelImage = new JLabel(new ImageIcon("RofP.gif"));
    //peelImage: https://www.google.com/url?sa=i url=https%3A%2F%2Frocketreach.co%2Fregion-of-peel-email-format_b5c65db3f42e0cad&psig=AOvVaw1bsBVSo5Gpu7jDqwdzSuVl&ust=1642782788202000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCPjEt_bgwPUCFQAAAAAdAAAAABAI
    peelImage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    peelImage.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
    contentPane.add(peelImage);
  }

  /**
  * pre: none
  * post: Displays the About Us Section
  */
  public void aboutUs(){
    /* Create and set up the frame */
		frameAbout = new JFrame("About Us");
		frameAbout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		/* Create a content pane with a BoxLayout and empty borders */
		contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    contentPane.setBackground(new Color(134, 195, 242)); //light blue colour

    /* Create and add labels for the about us description */
		about = new JTextArea(5, 20);
    String line1 = "We provide you with all the Child Care Centers in Peel Region";
    String line2 = "based on location and type of child care center.";
    String line3 = "Pick your preferred child care center and recieve the address, phone number and website";
    String line4 = "of the selected child care center.";
    about.append("\t   " + line1 + "\n\t\t  " + line2 + "\n" + line3 + "\n\t\t\t " + line4);
    Font monospaced = new Font("MONOSPACED", Font.BOLD, 12);
    about.setFont(monospaced);
		about.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
    about.setEditable(false);
		contentPane.add(about);

    /* Create and a label that will dsplay Region of Child Care centre logo*/
    childImage = new JLabel(new ImageIcon("CCC.gif"));
    //CCC Image: https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Fillustrations%2Fchild-care-centre&psig=AOvVaw3HTxCcrW7XbLAtz-DjKcbc&ust=1642783517369000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCPiNmuHjwPUCFQAAAAAdAAAAABAJ
    childImage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    childImage.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
    contentPane.add(childImage);

    /* Create a label that notifies the user to return to the menu */
		backLabel = new JLabel("Return to Main Menu");
		backLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    Font monospacedSmall = new Font("MONOSPACED", Font.BOLD, 10);
    backLabel.setFont(monospacedSmall);
    backLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		contentPane.add(backLabel);

    /* Create and add a button that will display the general pathway*/
    returnMain = new JButton(new ImageIcon("return.gif"));
    returnMain.setAlignmentX(JButton.CENTER_ALIGNMENT);
    //back.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    returnMain.setActionCommand("Back");
    returnMain.addActionListener(this);
    contentPane.add(returnMain);

    /* Create and a label that will dsplay child care center images*/
    image = new JLabel(new ImageIcon("img.gif"));
    //CCC Image:
    image.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    image.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
    contentPane.add(image);

    /* Add content pane to frame */
		frameAbout.setContentPane(contentPane);

    /* Size and then display the frame. */
		frameAbout.pack();
		frameAbout.setVisible(false);
  }

  /**
  * pre: none
  * post: General: displays combo box that contains the types of childcare centers
  */
  public void typeOfCenterMenu()
  {
		/* Create and set up the frame */	
		frameTypes = new JFrame("Type of centers");frameTypes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		/* Create a content pane */
		contentPane = new JPanel();
    contentPane.setBackground(new Color(134, 195, 242)); //light blue colour

    /* Create a combo box and a descriptive label*/ 
    promptType = new JLabel("Select a center: ");
    Font monospaced = new Font("MONOSPACED", Font.BOLD, 17);
    promptType.setAlignmentX(JLabel.LEFT_ALIGNMENT);
    contentPane.add(promptType);
    String [] names = {"","Community-Based For-Profit Centre", "School-Based Centre", "Community-Based Non-Profit Centre"};  
    centerNamesType = new JComboBox(names);
    centerNamesType.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
    centerNamesType.setSelectedIndex(0);
    centerNamesType.addActionListener(this);
    contentPane.add(centerNamesType);
  }

  /**
  * pre: none
  * post: General and Specific: Displays the filtered list of names as per the type of center user specified
  */
  public void filteredDropDownMenu()
  {
    /* Create and set up the frame */	
    frameList = new JFrame("Type of centers");
    frameList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /* Create a content pane */
    contentPane = new JPanel();
    contentPane.setBackground(new Color(134, 195, 242)); //light blue colour

    /* Create an empty combo box*/
    listPrompt = new JLabel("Select a center: ");
    Font monospaced = new Font("MONOSPACED", Font.BOLD, 17);
    listPrompt.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
    contentPane.add(listPrompt); 
    resultList = new JComboBox(centerNamesFiltered);
		resultList.setSelectedIndex(0);
		resultList.addActionListener(this);
		contentPane.add(resultList);

    /* Create and add a label that will show the amount of centers once filtered*/
    filteredNum = new JLabel("");
    filteredNum.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    filteredNum.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    contentPane.add(filteredNum);
  }

  /**
  * pre: none
  * post: General and Specific: displays all contact information of the center user chose
  */
  public void displayInformation()
  {
    /* Create and set up the frame */	
    frameInfo = new JFrame("Contact information");
    frameInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    /* Create a content pane */
    contentPane = new JPanel();
    contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    contentPane.setBackground(new Color(134, 195, 242)); //light blue colour

    /* Create and add a label that will show the center's name*/
    centerName = new JLabel("");
    centerName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    centerName.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    contentPane.add(centerName);

    /* Create and add a label that will show the center's phone number*/
    phoneNumber = new JLabel("");
    phoneNumber.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    phoneNumber.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    contentPane.add(phoneNumber);

    /* Create and add a label that will show the center's address*/
    address = new JLabel("");
    address.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    address.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    contentPane.add(address);

    /* Create and add a label that will show the center's website*/
    website = new JLabel("");
    website.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    website.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    contentPane.add(website);
  }

  /**
  * pre: none
  * post: Filters out child centers that are not in the selected area
  */
  public void specificLocation(){
    //Create the frame
    frameLocation = new JFrame("Childcare Search");
    frameLocation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create content panel
    contentPane = new JPanel();
    contentPane.setBackground(new Color(134, 195, 242)); //light blue colour

    //Create array for combo box containing cities
    String [] centresLoc = {"", "Brampton", "Mississauga", "Caledon"};

    //Add label that asks user where they would like their center to be
    location = new JLabel("Where would you like to look?");
    contentPane.add(location);

    //Add combo box to select cities
    dropBox = new JComboBox(centresLoc);
    dropBox.setSelectedIndex(0);
    dropBox.addActionListener(this);
    contentPane.add(dropBox);
  }

  /**
  * pre: none
  * post: Filters out child centers that are not the specified type
  */
  public void specificType(){
    //Create the frame
    frameCentreType = new JFrame("Childcare Search");
    frameCentreType.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create content panel
    contentPane = new JPanel();
    contentPane.setBackground(new Color(134, 195, 242)); //light blue colour

    //Create array for combo box containing types of centers
    String [] types = {"", "Community-Based For-Profit Centre ", "School-Based Centre ", "Community-Based Non-Profit Centre "};

    //Add label asking the user what type of center they would like to look for
    centreType = new JLabel("What type of centre are you looking for?");
    contentPane.add(centreType);

    //Add combo box to select types
    dropBoxType = new JComboBox(types);
    dropBoxType.setSelectedIndex(0);
    dropBoxType.addActionListener(this);
    contentPane.add(dropBoxType);
  }

  /**
  * pre: none
  * post: Displays childcare centers based on location
  */
  public void generalLocation(){
    //Create the frame
    frameGenLocation = new JFrame("Location of Centers");
    frameGenLocation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create content panel
    contentPane = new JPanel();
    contentPane.setBackground(new Color(134, 195, 242)); //light blue colour

    //Create array for combo box containing cities
    String [] cities = {"", "Brampton ", "Mississauga ", "Caledon "};

    //Add label that asks user where they would like their center to be
    genLocation = new JLabel("Where would you like to look?");
    contentPane.add(genLocation);

    //Add combo box to select cities
    dropBoxGen = new JComboBox(cities);
    dropBoxGen.setSelectedIndex(0);
    dropBoxGen.addActionListener(this);
    contentPane.add(dropBoxGen);
  }
  
  public Main(){
    //Display the About Us Description 
    aboutUs();

    //Displays childcare centers based on location
    generalLocation();
    frameGenLocation.setContentPane(contentPane);
    frameGenLocation.pack();
    frameGenLocation.setVisible(false);

    //displays the types of childcare centers
    typeOfCenterMenu();
    frameTypes.setContentPane(contentPane);
    frameTypes.pack();
		frameTypes.setVisible(false);

    //Filters out child centers that are not the specified type
    specificType();
    frameCentreType.setContentPane(contentPane);
    frameCentreType.pack();
    frameCentreType.setVisible(false);
    
    //Filters out child centers that are not in the selected area
    specificLocation();
    frameLocation.setContentPane(contentPane);
    frameLocation.pack();
		frameLocation.setVisible(false);

    //Display the Main Menu
    mainMenu();
    frame.setContentPane(contentPane);
    frame.setContentPane(contentPane);
    frame.pack();
		frame.setVisible(true);

    //displays an list of names as per specified preference
    filteredDropDownMenu();
    frameList.setContentPane(contentPane);
    frameList.pack();
    frameList.setVisible(false);

    //displays contact information
    displayInformation();
    frameInfo.setContentPane(contentPane);
    frameInfo.pack();
    frameInfo.setVisible(false);
    
  }
  
  public void actionPerformed(ActionEvent event)
  {
    //buttons
    if(action==1)
    {
      //action
      String eventName = event.getActionCommand();

      //General Sub-Menu
      if (eventName.equals("General"))
      {
        //Rewrite specific button text
        specificButton.setText("Municipality");
        specificButton.setActionCommand("MunicipalityAction");
        //Rewrite general button text
        generalButton.setText("Type of Child Care Centre");
        generalButton.setActionCommand("Type of Child Care Centre");
        aboutUs.setVisible(false);
      }
      //Start to search for a specific type of center in a specific place
      if (eventName.equals("Specific"))
      {
        frame.setVisible(false);
        frameLocation.setVisible(true);
        action = 2;
      }
      //Back to Main Menu
      if (eventName.equals("Back"))
      {
        frameAbout.setVisible(false);
        frame.setVisible(true);
        specificButton.setVisible(true);
        specificButton.setText("Specific");
        generalButton.setVisible(true);
        generalButton.setText("General");
        aboutUs.setVisible(true);
      }
      if (eventName.equals("Type of Child Care Centre"))
      {
       // main menu form becomes invisible
        frame.setVisible(false);
      
        // search form becomes visible
        frameTypes.setVisible(true);
        action=2;
      }
      else if (eventName.equals("MunicipalityAction"))
      {
        frame.setVisible(false);
        frameGenLocation.setVisible(true);
        action = 2;
      }
      //About Us
      if (eventName.equals("About Us"))
      {
        frame.setVisible(false);
        frameAbout.setVisible(true);
      }
    }
      //combo boxes
      else if(action==2)
      {
        //action for combobox
        JComboBox comboBox = (JComboBox)event.getSource();
        String centerNames = (String)comboBox.getSelectedItem();
        //Filters the location that the center is located at and stores the data in an array
        if (centerNames.equals("Brampton"))
        {
          for (int r = 0; r < data.length; r++)
          {
            //Look for data containing "Brampton"
            int index = data[r][10].indexOf("Brampton");
            
            //If "Brampton" is found, add it to the array
            if(index != -1)
            {
              //Storing all coloumns/details of the center in a string
              for (int c2 = 0; c2 < data[0].length; c2++)
              {
                mun = mun + data[r][c2];
              }
              //Seperate the data with a "="
              mun = mun + "=";
              //Turn the string into an array via splitting method
              city = mun.split("=");

              //When the array is done, close the window and open a new one
              if (r == data.length - 1)
              {
                frameLocation.setVisible(false);
                frameCentreType.setVisible(true);
                //Reset the string
                mun = "";
              }
            }
          }
        }

        else if (centerNames.equals("Mississauga"))
        {
          for (int r = 0; r < data.length; r++)
          {
            //Look for data containing "Mississauga"
            int index = data[r][10].indexOf("Mississauga");
            //If "Mississauga" is found, add it to the array
            if(index != -1)
            {
              //Storing all coloumns/details of the center in a string
              for (int c2 = 0; c2 < data[0].length; c2++)
              {
                mun = mun + data[r][c2];
              }
              //Seperate the data with a "="
              mun = mun + "=";
              //Turn the string into an array via splitting method
              city = mun.split("=");
            }

            //When the array is done, close the window and open a new one
            else if (r == data.length - 1)
            {
              frameLocation.setVisible(false);
              frameCentreType.setVisible(true);
              //Reset the string
              mun = "";
            }
          }
        }

        else if (centerNames.equals("Caledon"))
        {
          for (int r = 0; r < data.length; r++)
          {
            //Look for data containing "Caledon"
            int index = data[r][10].indexOf("Caledon");

            //If "Caledon" is found, add it to the array
            if(index != -1)
            {
              //Storing all coloumns/details of the center in a string
              for (int c2 = 0; c2 < data[0].length; c2++)
              {
                mun = mun + data[r][c2];
              }
              //Seperate the data with a "="
              mun = mun + "=";
              //Turn the string into an array via splitting method
              city = mun.split("=");
            }
            
            //When the array is done, close the window and open a new one
            else if (r == data.length - 1)
              {
                frameLocation.setVisible(false);
                frameCentreType.setVisible(true);
                //Reset the string
                mun = "";
              }
          }
        }

        if (centerNames.equals("Community-Based For-Profit Centre "))
        {
          //Find any data containing "Community-Based For-Profit Centre" within the array and store it
          for (int r = 0; r < city.length; r++)
          {
            //Declare variables
            int indexType = city[r].indexOf("Community-Based For-Profit Centre");
            int start = city[r].indexOf("CC-");
            int end = city[r].indexOf("Social Services");
            
            if (indexType != -1 && start != -1 && end != -1)
            {
              //Store the data where the name of the center would be in a string
              cen = cen + city[r].substring(start + 10, end);
              //Seperate the data using "="
              cen = cen + "=";
              //Turn the data into an array via splitting
              centre = cen.split("=");
            }
            //When the loop is over, reset the string
            if (r == city.length - 1)
            {
              for (int i = 0; i < centre.length; i++)
              {
                resultList.addItem(centre[i]);
              }
              frameCentreType.setVisible(false);
              frameList.setVisible(true);
              cen = "";
            }
          }
        }

        else if (centerNames.equals("School-Based Centre "))
        {
          //Find any data containing "School-Based Centre" within the array and store it
          for (int r = 0; r < city.length; r++)
          {
            //Declare variables
            int indexType = city[r].indexOf("School-Based Centre");
            int start = city[r].indexOf("CC-");
            int checkA = city[r].lastIndexOf("a", start + 10);
            int checkB = city[r].lastIndexOf("b", start + 10);
            int end = city[r].indexOf("Social Services");
            if (indexType != -1 && start != -1 && end != -1)
            {
              //Since there are some centers with an "a" version and a "b" version, these checks are used to adjust the starting point of the substring
              if (checkA != -1 || checkB != -1)
              {
                //Store the data where the name of the center would be in a string
                cen = cen + city[r].substring(start + 11, end);
              }
              else
              {
                //Store the data where the name of the center would be in a string
                cen = cen + city[r].substring(start + 10, end);
              }
              //Seperate the data using "="
              cen = cen + "=";
              
              //Turn the data into an array via splitting
              centre = cen.split("=");
            }
            //When the loop is over, reset the string
            if (r == city.length - 1)
            {
              for (int i = 0; i < centre.length; i++)
              {
                resultList.addItem(centre[i]);
              }
              frameCentreType.setVisible(false);
              frameList.setVisible(true);
              cen = "";
            }
          }
        }

        else if (centerNames.equals("Community-Based Non-Profit Centre "))
        {
          //Find any data containing "Community-Based Non-Profit Centre" within the array and store it
          for (int r = 0; r < city.length; r++)
          {
            //Declare variables
            int indexType = city[r].indexOf("Community-Based Non-Profit Centre");
            int start = city[r].indexOf("CC-");
            int end = city[r].indexOf("Social Services");
            if (indexType != -1)
            {
              //Store the data where the name of the center would be in a string
              cen = cen + city[r].substring(start + 10, end);

              //Seperate the data using "="
              cen = cen + "=";
            
              //Turn the data into an array via splitting
              centre = cen.split("=");
            }
            //When the loop is over, reset the string
            if (r == city.length - 1)
            {
              for (int i = 0; i < centre.length; i++)
              {
                resultList.addItem(centre[i]);
              }
              frameCentreType.setVisible(false);
              frameList.setVisible(true);
              cen = "";
            }
          }
        }

        if (centerNames.equals("Brampton "))
        {
          for (int r = 0; r < data.length; r++)
          {
            //Look for data containing "Brampton"
            int index = data[r][10].indexOf("Brampton");
            //If "Brampton" is found, add it to the array
            if(index != -1)
            {
              //Increase counter
              counter++;
              //Add all center names found in this coloumn (4) to a string
              mun = mun + data[r][4];
              //Seperate data with "="
              mun = mun + "=";
              //Turn the string into an array via splitting method
              city = mun.split("=");

              //When the array is done, close the window
              if (r == data.length - 1)
              {
                for (int i = 0; i < city.length; i++)
                {
                  filteredNum.setText("Amount of centers: " +counter);
                  resultList.addItem(city[i]);
                }
                frameGenLocation.setVisible(false);
                frameList.setVisible(true);
                //Reset the string
                mun = "";
              }
            }
          }
        }

        else if (centerNames.equals("Mississauga "))
        {
          for (int r = 0; r < data.length; r++)
          {
            //Look for data containing "Mississauga"
            int index = data[r][10].indexOf("Mississauga");
            //If "Mississauga" is found, add it to the array
            if(index != -1)
            {
              //Increase counter
              counter++;
              //Add all center names found in this coloumn (4) to a string
              mun = mun + data[r][4];
              //Seperate data with "="
              mun = mun + "=";
              //Turn the string into an array via splitting method
              city = mun.split("=");
            }

            //When the array is done, close the window
            if (r == data.length - 1)
            {
              for (int i = 0; i < city.length; i++)
              {
                filteredNum.setText("Amount of centers: " +counter);
                resultList.addItem(city[i]);
              }
              frameGenLocation.setVisible(false);
              frameList.setVisible(true);
              //Reset the string
              mun = "";
            }
          }
        }

        else if (centerNames.equals("Caledon "))
        {
          for (int r = 0; r < data.length; r++)
          {
            //Look for data containing "Caledon"
            int index = data[r][10].indexOf("Caledon");
            //If "Caledon" is found, add it to the array
            if(index != -1)
            {
              //Increase counter
              counter++;
              //Add all center names found in this coloumn (4) to a string
              mun = mun + data[r][4];
              //Seperate the data with "="
              mun = mun + "=";
              //Turn the string into an array via splitting method
              city = mun.split("=");
              
            }
            
            //When the array is done, close the window
            if (r == data.length - 1)
            {
              for (int i = 0; i < city.length; i++)
              {
                filteredNum.setText("Amount of centers: " +counter);
                resultList.addItem(city[i]);
              }
              frameGenLocation.setVisible(false);
              frameList.setVisible(true);
              //Reset the string
              mun = "";
            }
          }
        }

        //filtering and creating an array based on user's preference
        //user prefers community based for profit center
        if (centerNames.equals("Community-Based For-Profit Centre"))
        {
           //finding the row with the preference
          for (r = 0; r < data.length; r++)
          {
            int indexOfTypes = data[r][15].indexOf("Community-Based For-Profit Centre");
        
            //if index if found, proceed
            if(indexOfTypes == 0)
            {
             //counter to show user how many options they have
              counter++;
              //finding the names of the childcare centers
              stringTypes = stringTypes + data[r][4];
          
             //turn string into an array
             stringTypes = stringTypes + "/";
             listOfTypes = stringTypes.split("/");         
            }

            if (r == data.length - 1)
            {
            for (i = 0; i < listOfTypes.length; i++)
            {
              //showing the proper frame
              frameList.setVisible(true);
              frameTypes.setVisible(false);

              //showing amount of names found per user's preference
              filteredNum.setText("Amount of centers: " +counter);

              //adding the list of names into empty drop box
              resultList.addItem(listOfTypes[i]);    
            }
            }
          }
      
        } 
         //user prefers community based non profit center
        else if (centerNames.equals("Community-Based Non-Profit Centre"))
        {
        for (r = 0; r < data.length; r++)
        {
          //if index if found, proceed
          int indexOfTypes = data[r][15].indexOf("Community-Based Non-Profit Centre");

          if(indexOfTypes == 0)
          {
           //counter to show user how many options they have
           counter++;
          
            //finding the names of the childcare centers      
            stringTypes = stringTypes + data[r][4];

           //turn string to array
            stringTypes = stringTypes + ",";
            listOfTypes = stringTypes.split(",");

           if (r == data.length - 1)
           {
              for (i = 0; i < listOfTypes.length; i++)
              {
                //change to another frame
                frameList.setVisible(true);
                frameTypes.setVisible(false);

                //displaying the counter
                filteredNum.setText("Amount of centers: " +counter);

                //adding the list of names into drop box menu
                resultList.addItem(listOfTypes[i]);
            }    
          }
        }

        else if (r == data.length - 1)
        {
          for (i = 0; i < listOfTypes.length; i++)
          {
            //change to another frame
            frameList.setVisible(true);
            frameTypes.setVisible(false);

            //displaying the counter
            filteredNum.setText("Amount of centers: " +counter);

            //adding the list of names into drop box menu
            resultList.addItem(listOfTypes[i]);
          }
            
          }
        }
      }
      
    
      //user prefers school based center
      else if (centerNames.equals("School-Based Centre"))
      {  
        for (r = 0; r < data.length; r++)
        {
         int indexOfTypes = data[r][15].indexOf("School-Based Centre");
        
         //if index found, proceed
         if(indexOfTypes == 0)
          {
           //counter to show user how many options they have
           counter++;
           //finding the names of the childcare centers
           stringTypes = stringTypes + data[r][4];
          
            //turn string to array
            stringTypes = stringTypes + "/";
            listOfTypes = stringTypes.split("/");

            if (r == data.length - 1)
            {
             for (i = 0; i < listOfTypes.length; i++)
              {
                //change to another frame
                frameList.setVisible(true);
               frameTypes.setVisible(false);

                //displaying the counter
                filteredNum.setText("Amount of centers: " +counter);

               //adding the list of names into drop box menu
                resultList.addItem(listOfTypes[i]);
              }
            }
            }

          else if (r == data.length - 1)
          {
           for (int i = 0; i < listOfTypes.length; i++)
            {
             //change to another frame
             frameList.setVisible(true);
             frameTypes.setVisible(false);

             //displaying the counter
             filteredNum.setText("Amount of centers: " +counter);

             //adding the list of names into drop box menu
             resultList.addItem(listOfTypes[i]);
            } 
          }
        }
      
    }

      //loop needed to access array with all the names
      for (r=0;r<listOfTypes2.length;r++)
      {
        //if what user chose equals one of the names, proceed
        if(centerNames.equals(listOfTypes2[r]))
        {
          //making a string of what user selected
          String userAnswer=new String();
          userAnswer= (String)resultList.getSelectedItem();      

          //finding the index of user's choice
          int indexOfTypes = data[r][4].indexOf(userAnswer);

          //displaying all needed information when user's choice is found
          if(indexOfTypes == 0)
          {
            //show frame
            frameInfo.setVisible(true);
            frameList.setVisible(false);

            centerName.setText("Name: " + data[r][4]);
            phoneNumber.setText("\nPhone number: " + data[r][13]);
            
            if(data[r][9].equals(""))
            {
              address.setText("\nAddress: " + data[r][8] + " " + data[r][10] + " " + data[r][11] + " " + data[r][12]);
            }
            else
            {
              address.setText("\nAddress: " + data[r][8] + " " + data[r][9] + " " + data[r][10] + " " + data[r][11] + " " + data[r][12]);
            }

            website.setText("\nWebsite: " + data[r][14]);
          }

          }
        }
      }
    }

  private static void runGui()
  {
    JFrame.setDefaultLookAndFeelDecorated(true);
    Main search = new Main();
  }

  //MMsnowflake
  public static void main(String[] args) {
    //Create the 2D Array
    try
    {
      //Setup Files
      File textFile = new File("data.txt");
      FileReader in = new FileReader (textFile);
      BufferedReader readFile = new BufferedReader (in);

      int numLines = 0; //Count number of lines entered

			while ((lineOfText = readFile.readLine()) != null ) 
			{
        //Split line of text by the commas ","
				String[] lineOfData = lineOfText.split(",");

        //For every position in the column 
        //Fill it with the corresponding line from the 1D array
        for (int c = 0; c < data[0].length; c++)
        {
          data[numLines][c] = lineOfData[c]; 
        }
        numLines++; //increment the number of lines by 1 to go to the next line   
      }

      //make a list with all the names for displayInformation() (LAmjad)
      for (r=0; r <data.length; r++)
      {
       namesList = namesList + data[r][4];
          
       //turn string into an array
       namesList = namesList + "/";
       listOfTypes2 = namesList.split("/");
      }

      //Close files
			readFile.close();
			in.close();
      

      javax.swing.SwingUtilities.invokeLater(new Runnable()
      {
        public void run()
        {
          runGui();
        }
      });
    }

    catch (IOException e)
    {
      System.out.println("Was not able to extraect data from file");
      System.err.println("IOException: " + e.getMessage());
    }   
  }
}