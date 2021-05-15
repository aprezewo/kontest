package graphic;

import general.Config;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import tech.Sign;

public class SignData {
	
	public static tech.Sign[] signsTech;
	/** Set initialized to false (after it was true) to invalidate current Initialization state. Any class using signData will return and only
	 * work until after create() was called again. */
	public static boolean initialized = false; 
	
	public static void create() {
		
		//initiate SignData
		signsTech = new Sign[Config.getSignVariations()];
		for(int t1 = 0; t1 < signsTech.length; t1++) {
			signsTech[t1] = new Sign(t1);
		}

		initialized = true;
	}
	
	public static int getTypeForId(int id) {
		
		switch(id) {
			case(0):{
				return 0;
			}
			case(1):{
				return 0;
			}
			case(2):{
				return 0;
			}
			case(3):{
				return 0;
			}
			case(4):{
				return 1;
			}
			case(5):{
				return 1;
			}
			case(6):{
				return 1;
			}
			case(7):{
				return 1;
			}
			case(8):{
				break;
			}
			case(9):{
				break;
			}
		}
		return -1;
	}
	
	/**get Shape of Sign depending on id
	 * 
	 * @param sign
	 * @return
	 */
	public static graphic.Sign getGraphicSignById(int id){
		
		String style = "-fx-fill: transparent; -fx-stroke: white; -fx-stroke-width: " + Config.getStrokeWidth();
		
		double diameter = Config.getExtentGridObjects();
		
		// edge = a = sqrt( (d²)/2 ) - from a²+a² = d² ... the length of the edge of the inner rectangle
		// distance from center to edge would be rectEdge/2 - innerRadius
		double rectEdge = Math.sqrt(Math.pow(diameter, 2)/2); 
		
		// vector XY - no need for a vector, as x and y are equal
		// vectorXY has a length of half the innerRadius
		double vecXY = Math.sqrt(Math.pow(rectEdge/4, 2)/2);
		
		
		graphic.Sign sign = new graphic.Sign(id);
		
		Circle c = new Circle(diameter/2);
		c.setStyle(style);
		sign.getChildren().add(c);
				
		switch(id) {
			case(0):{
				Rectangle r = new Rectangle(-rectEdge/2,-rectEdge/2,rectEdge,rectEdge);
				r.setStyle(style);
				sign.getChildren().add(r);
				
				Circle c1 = new Circle(-vecXY,-vecXY,Config.getStrokeWidth()/2);
				c1.setStyle(style);
				sign.getChildren().add(c1);
				
				Circle c2 = new Circle(vecXY,-vecXY,Config.getStrokeWidth()/2);
				c2.setStyle(style);
				sign.getChildren().add(c2);
				
				Circle c3 = new Circle(0,vecXY,Config.getStrokeWidth()/2);
				c3.setStyle(style);
				sign.getChildren().add(c3);
				break;
			}
			case(1):{
				Rectangle r = new Rectangle(-rectEdge/2,-rectEdge/2,rectEdge,rectEdge);
				r.setStyle(style);
				sign.getChildren().add(r);
				
				Circle c1 = new Circle(0,-vecXY,Config.getStrokeWidth()/2);
				c1.setStyle(style);
				sign.getChildren().add(c1);
				
				Circle c2 = new Circle(-vecXY,vecXY,Config.getStrokeWidth()/2);
				c2.setStyle(style);
				sign.getChildren().add(c2);
				
				Circle c3 = new Circle(vecXY,vecXY,Config.getStrokeWidth()/2);
				c3.setStyle(style);
				sign.getChildren().add(c3);
				break;
			}
			case(2):{
				Circle cI = new Circle(rectEdge/2);
				cI.setStyle(style);
				sign.getChildren().add(cI);
				
				Circle c1 = new Circle(-vecXY,-vecXY,Config.getStrokeWidth()/2);
				c1.setStyle(style);
				sign.getChildren().add(c1);
				
				Circle c2 = new Circle(vecXY,vecXY,Config.getStrokeWidth()/2);
				c2.setStyle(style);
				sign.getChildren().add(c2);
				break;
			}
			case(3):{
				Circle cI = new Circle(rectEdge/2);
				cI.setStyle(style);
				sign.getChildren().add(cI);
				
				Circle c1 = new Circle(vecXY,-vecXY,Config.getStrokeWidth()/2);
				c1.setStyle(style);
				sign.getChildren().add(c1);
				
				Circle c2 = new Circle(-vecXY,vecXY,Config.getStrokeWidth()/2);
				c2.setStyle(style);
				sign.getChildren().add(c2);
				break;
			}
			case(4):{
				Rectangle r = new Rectangle(-rectEdge/2,-rectEdge/2,rectEdge,rectEdge);
				r.setStyle(style);
				sign.getChildren().add(r);
				
				Circle c1 = new Circle(vecXY,-vecXY,Config.getStrokeWidth()/2);
				c1.setStyle(style);
				sign.getChildren().add(c1);
				
				Circle c2 = new Circle(-vecXY,vecXY,Config.getStrokeWidth()/2);
				c2.setStyle(style);
				sign.getChildren().add(c2);
				break;
			}
			case(5):{
				Rectangle r = new Rectangle(-rectEdge/2,-rectEdge/2,rectEdge,rectEdge);
				r.setStyle(style);
				sign.getChildren().add(r);
				
				Circle c1 = new Circle(-vecXY,vecXY,Config.getStrokeWidth()/2);
				c1.setStyle(style);
				sign.getChildren().add(c1);
				
				Circle c2 = new Circle(vecXY,-vecXY,Config.getStrokeWidth()/2);
				c2.setStyle(style);
				sign.getChildren().add(c2);
				break;
			}
			case(6):{
				Circle cI = new Circle(rectEdge/2);
				cI.setStyle(style);
				sign.getChildren().add(cI);
				
				Circle c1 = new Circle(-vecXY,-vecXY,Config.getStrokeWidth()/2);
				c1.setStyle(style);
				sign.getChildren().add(c1);
				
				Circle c2 = new Circle(vecXY,-vecXY,Config.getStrokeWidth()/2);
				c2.setStyle(style);
				sign.getChildren().add(c2);
				
				Circle c3 = new Circle(0,vecXY,Config.getStrokeWidth()/2);
				c3.setStyle(style);
				sign.getChildren().add(c3);
				break;
			}
			case(7):{
				Circle cI = new Circle(rectEdge/2);
				cI.setStyle(style);
				sign.getChildren().add(cI);
				
				Circle c1 = new Circle(0,-vecXY,Config.getStrokeWidth()/2);
				c1.setStyle(style);
				sign.getChildren().add(c1);
				
				Circle c2 = new Circle(-vecXY,vecXY,Config.getStrokeWidth()/2);
				c2.setStyle(style);
				sign.getChildren().add(c2);
				
				Circle c3 = new Circle(vecXY,vecXY,Config.getStrokeWidth()/2);
				c3.setStyle(style);
				sign.getChildren().add(c3);
				break;
			}
			case(8):{
				break;
			}
			case(9):{
				break;
			}
			default:{
				
				break;
			}
		}
		return sign;
	}
	
	public static void printSignArray(graphic.Sign[] sArray) {
		//debug-----------------------
		for(graphic.Sign s : sArray) {
			System.out.print(s.getSignId() + "," + s.getSignType() + " : "); 
		}
		System.out.println();
		//-----------------------------
	}
	
	public static void printSignArray(tech.Sign[] sArray) {
		//debug-----------------------
		for(tech.Sign s : sArray) {
			System.out.print( s.getSignId() + "," + s.getSignType() + " : "); 
		}
		System.out.println();
		//-----------------------------
	}

}
