import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GUI extends JComponent {

    int height;
    int width;
    final int H = 500;
    final int W = 200;
    final int coef = 100;
    Graph robot;
    ArrayList<Node> nodeList;

    public GUI(int width, int height){
        this.width = width;
        this.height = height;
        this.nodeList = new ArrayList<>();
    }

    public void sendData(Graph robot, ArrayList<Node> nodeList){
        this.robot = robot;
        this.nodeList = nodeList;
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);   //background
        g2.fillRect(0, 0, this.width, this.height);

        g2.setColor(Color.YELLOW);

        Iterator<Node> iterator = this.robot.nodes.iterator();
        while(iterator.hasNext()){
            Node n = iterator.next();

            for(int i=0; i<n.endPoints.size();i++){
                g2.drawLine(
                        W + (int)n.X*coef,
                        H - (int) n.Y*coef,
                        W + (int) n.endPoints.get(i).X*coef,
                        H - (int) n.endPoints.get(i).Y*coef
                );
            }
            double z = (n.Z*0.5+1); //Default value is zero, *0.5 is an order of magnitude for 3D

            g2.fillOval((int) (W + n.X*coef-10*z),(int) (H - n.Y*coef-10*z), (int)(20*z),(int)(20*z));
        }



    }

}
