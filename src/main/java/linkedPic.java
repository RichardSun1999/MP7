import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
	public class linkedPic implements ActionListener { 
	JFrame mainFrame; //main frame
	Container thisContainer; 
	JPanel centerPanel,southPanel,northPanel; //child frame 
	JButton diamondsButton[][] = new JButton[6][5];//Picture array in game 
	JButton exitButton,resetButton,newlyButton; //functional button
	JLabel fractionLable=new JLabel("0"); //score 
	JButton firstButton,secondButton; //to record which two pictures selected
	int grid[][] = new int[8][7];//store the button position in game
	static boolean pressInformation=false; //determine whether the button is selected
	int x0=0,y0=0,x=0,y=0,fristMsg=0,secondMsg=0,validateLV; //position of the button  
	int i,j,k,n;//eliminate the method control 
	public void init(){ 
	mainFrame=new JFrame("linkedPic"); 
	thisContainer = mainFrame.getContentPane(); 
	thisContainer.setLayout(new BorderLayout()); 
	centerPanel=new JPanel(); 
	southPanel=new JPanel(); 
	northPanel=new JPanel(); 
	thisContainer.add(centerPanel,"Center"); 
	thisContainer.add(southPanel,"South"); 
	thisContainer.add(northPanel,"North"); 
	centerPanel.setLayout(new GridLayout(6,5)); 
	for(int cols = 0;cols < 6;cols++){ 
	     for(int rows = 0;rows < 5;rows++ ){ 
	        diamondsButton[cols][rows]=new JButton(String.valueOf(grid[cols+1][rows+1])); 
	        diamondsButton[cols][rows].addActionListener(this); 
	        centerPanel.add(diamondsButton[cols][rows]); 
	     } 
	} 
	exitButton=new JButton("Exit"); // define exit button 
	exitButton.addActionListener(this); 
	resetButton=new JButton("Rearrange"); //define rearrange button when there are no available pairs
	resetButton.addActionListener(this); 
	newlyButton=new JButton("Restart"); // define restart button 
	newlyButton.addActionListener(this); 
	southPanel.add(exitButton); 
	southPanel.add(resetButton); 
	southPanel.add(newlyButton); 
	fractionLable.setText(String.valueOf(Integer.parseInt(fractionLable.getText()))); 
	northPanel.add(fractionLable); 
	mainFrame.setBounds(280,100,500,450); 
	mainFrame.setVisible(true); 
	} 
	public void randomBuild() { 
	       int randoms,cols,rows; 
	       for(int twins=1;twins<=15;twins++) { 
	            randoms=(int)(Math.random()*25+1); 
	            for(int alike=1;alike<=2;alike++) { 
	                cols=(int)(Math.random()*6+1); 
	                rows=(int)(Math.random()*5+1); 
	                      while(grid[cols][rows]!=0) { 
	                           cols=(int)(Math.random()*6+1); 
	                           rows=(int)(Math.random()*5+1); 
	                           } 
	             this.grid[cols][rows]=randoms; 
	             } 
	        } 
	} 
	public void fraction(){ 
	fractionLable.setText(String.valueOf(Integer.parseInt(fractionLable.getText())+100)); 
	} 
	public void reload() { 
	    int save[] = new int[30]; 
	    int n=0,cols,rows; 
	    int grid[][]= new int[8][7]; 
	    for(int i=0;i<=6;i++) { 
	        for(int j=0;j<=5;j++) { 
	           if(this.grid[i][j]!=0) { 
	               save[n]=this.grid[i][j]; 
	               n++; 
	            } 
	         } 
	      } 
	     n=n-1; 
	     this.grid=grid; 
	     while(n>=0) { 
	          cols=(int)(Math.random()*6+1); 
	          rows=(int)(Math.random()*5+1); 
	          while(grid[cols][rows]!=0) { 
	               cols=(int)(Math.random()*6+1); 
	               rows=(int)(Math.random()*5+1); 
	          } 
	         this.grid[cols][rows]=save[n]; 
	         n--; 
	      } 
	mainFrame.setVisible(false); 
	pressInformation=false; //setting clicking information to the default 
	init(); 
	     for(int i = 0;i < 6;i++){ 
	         for(int j = 0;j < 5;j++ ){ 
	             if(grid[i+1][j+1]==0) 
	             diamondsButton[i][j].setVisible(false); 
	          } 
	      } 
	} 
	public void estimateEven(int placeX,int placeY,JButton bz) { 
	       if(pressInformation==false) { 
	             x=placeX; 
	             y=placeY; 
	             secondMsg=grid[x][y]; 
	             secondButton=bz; 
	             pressInformation=true; 
	        } else { 
	             x0=x; 
	             y0=y; 
	             fristMsg=secondMsg; 
	             firstButton=secondButton; 
	             x=placeX; 
	             y=placeY; 
	             secondMsg=grid[x][y]; 
	             secondButton=bz; 
	        if(fristMsg==secondMsg && secondButton!=firstButton){ 
	             erase(); 
	             } 
	         } 
	} 
	public void erase() { //when in the same situation, analyzing can it be erased
	       if((x0==x &&(y0==y+1||y0==y-1)) || ((x0==x+1||x0==x-1)&&(y0==y))){ //determine if they are adjacent 
	             remove(); 
	            } else { 
	                 for (j=0;j<7;j++ ) { 
	                        if (grid[x0][j]==0){ //determine button in the same line with the first button is empty
	   if (y>j) { 
           //if y-coordinate of second button is larger than that of empty button indicating that first button is on the left of second button
	   for (i=y-1;i>=j;i-- ){ //determine if there is button between left of second button to first button 
	   if (grid[x][i]!=0) { 
	   k=0; 
	   break; 
	        } else { k=1; } //k = 1 indicating k passes the test
	} 
	if (k==1) { 
	   linePassOne(); 
	   } 
	} 
	if (y<j){ //if y-coordinate of second button is smaller than that of empty button indicating that first button is on the right of second button 
	   for (i=y+1;i<=j ;i++ ){ //determine if there is button between right of second button to first button 
	      if (grid[x][i]!=0){ 
	        k=0; 
	        break; 
	} else { k=1; } 
	} 
	if (k==1){ 
	   linePassOne(); 
	   } 
	} 
	if (y==j ) { 
	   linePassOne(); 
	    } 
	} 
	if (k==2) { 
	   if (x0==x) { 
	     remove(); 
	} 
	if (x0<x) { 
	   for (n=x0;n<=x-1;n++ ) { 
	   if (grid[n][j]!=0) { 
	      k=0; 
	      break; 
	} 
	if(grid[n][j]==0 && n==x-1) { 
	    remove(); 
	     } 
	   } 
	} 
	if (x0>x) { 
	    for (n=x0;n>=x+1 ;n-- ) { 
	       if (grid[n][j]!=0) { 
	          k=0; 
	          break; 
	} 
	if(grid[n][j]==0 && n==x+1) { 
	   remove(); 
	   } 
	} 
	} 
	} 
	} 
	for (i=0;i<8;i++ ) { //column
	    if (grid[i][y0]==0) { 
	        if (x>i) { 
	           for (j=x-1;j>=i ;j-- ) { 
	                if (grid[j][y]!=0) { 
	                    k=0; 
	                    break; 
	                   } else { k=1; } 
	             } 
	          if (k==1) { 
	              rowPassOne(); 
	              } 
	           } 
	          if (x<i) { 
	             for (j=x+1;j<=i;j++ ) { 
	                  if (grid[j][y]!=0) { 
	                     k=0; 
	                     break; 
	                  } else { k=1; } 
	               } 
	           if (k==1) { 
	              rowPassOne(); 
	              } 
	           } 
	           if (x==i) { 
	               rowPassOne(); 
	           } 
	       } 
	           if (k==2){ 
	               if (y0==y) { 
	                  remove(); 
	                } 
	               if (y0<y) { 
	                  for (n=y0;n<=y-1 ;n++ ) { 
	                     if (grid[i][n]!=0) { 
	                        k=0; 
	                        break; 
	                      } 
	                     if(grid[i][n]==0 && n==y-1) { 
	                       remove(); 
	                      } 
	                   } 
	               } 
	            if (y0>y) { 
	               for (n=y0;n>=y+1 ;n--) { 
	                   if (grid[i][n]!=0) { 
	                       k=0; 
	                       break; 
	                       } 
	           if(grid[i][n]==0 && n==y+1) { 
	          remove(); 
	            } 
	         } 
	       } 
	     } 
	   } 
	  }  
	} 
	public void linePassOne(){ 
	if (y0>j){ //第一按钮同行空按钮在左边 
	for (i=y0-1;i>=j ;i-- ){ //判断第一按钮同左侧空按钮之间有没按钮 
	if (grid[x0][i]!=0) { 
	k=0; 
	break; 
	} 
	else { k=2; } //K=2说明通过了第二次验证 
	} 
	} 
	if (y0<j){ //第一按钮同行空按钮在与第二按钮之间 
	for (i=y0+1;i<=j ;i++){ 
	if (grid[x0][i]!=0) { 
	k=0; 
	break; 
	} 
	else{ k=2; } 
	} 
	} 
	} 
	public void rowPassOne(){ 
	if (x0>i) { 
	for (j=x0-1;j>=i ;j-- ) { 
	if (grid[j][y0]!=0) { 
	k=0; 
	break; 
	} 
	else { k=2; } 
	} 
	} 
	if (x0<i) { 
	for (j=x0+1;j<=i ;j++ ) { 
	if (grid[j][y0]!=0) { 
	k=0; 
	break; 
	} 
	else { k=2; } 
	} 
	} 
	} 
	public void remove(){ 
	firstButton.setVisible(false); 
	secondButton.setVisible(false); 
	fraction(); 
	pressInformation=false; 
	k=0; 
	grid[x0][y0]=0; 
	grid[x][y]=0; 
	} 
	public void actionPerformed(ActionEvent e) { 
	if(e.getSource()==newlyButton){ 
	int grid[][] = new int[8][7]; 
	this.grid = grid; 
	randomBuild(); 
	mainFrame.setVisible(false); 
	pressInformation=false; 
	init(); 
	} 
	if(e.getSource()==exitButton) 
	System.exit(0); 
	if(e.getSource()==resetButton) 
	reload(); 
	for(int cols = 0;cols < 6;cols++){ 
	for(int rows = 0;rows < 5;rows++ ){ 
	if(e.getSource()==diamondsButton[cols][rows]) 
	estimateEven(cols+1,rows+1,diamondsButton[cols][rows]); 
	} 
	} 
	} 
	public static void main(String[] args) { 
	linkedPic llk = new linkedPic(); 
	llk.randomBuild(); 
	llk.init(); 
	} 
	} 
