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
    Graph robot;    //The robot
    ArrayList<Node> nodeList;
    double[] scaling;   //x,y,z
    double angle;

    public GUI(int width, int height){
        this.width = width;
        this.height = height;
        this.nodeList = new ArrayList<>();
        this.scaling = new double[]{1,1,1};
        this.angle = 0;
    }

    public void sendData(Graph robot, ArrayList<Node> nodeList){
        this.robot = robot;
        this.nodeList = robot.nodes;
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);   //background
        g2.fillRect(0, 0, this.width, this.height);

        g2.setColor(Color.YELLOW);

        Iterator<Node> iterator = this.nodeList.iterator();
        for(int k=0; k<this.nodeList.size(); k++){
            Node n = this.nodeList.get(k);

            for(int i=0; i<robot.nodes.get(k).endPoints.size();i++){
                
                String label = robot.nodes.get(k).endPoints.get(i).label;   //Gets label of endpoint
                Node n2 = null;
                for(int j=0; j<this.nodeList.size();j++){   //Finds a match in the recomputed points from transformation
                    if(label.equals(this.nodeList.get(j).label)){
                        n2 = this.nodeList.get(j);
                    }
                }

                g2.drawLine(
                        W + (int) n.X*coef,
                        H - (int) n.Y*coef,
                        W + (int) n2.X*coef,
                        H - (int) n2.Y*coef
                );
            }
            double z = (n.Z*0.5+1); //Default value is zero, *0.5 is an order of magnitude for 3D

            g2.fillOval(
                    (int) ((W + n.X*coef-10*z) * scaling[0]),
                    (int) ((H - n.Y*coef-10*z)* scaling[1]),
                    (int)(20*z),
                    (int)(20*z)
            );
        }



    }

    public void addScale(double[] scale){
        scaling[0] += scale[0];
        scaling[1] += scale[1];
        scaling[2] += scale[2];
    }
    public void rotateX(double angle){
        ArrayList<Node> newList = new ArrayList<>();
        for(int i=0; i<nodeList.size();i++){
            double y = nodeList.get(i).Y * Math.cos(angle);
            y += nodeList.get(i).Z * Math.sin(angle);

            double z = nodeList.get(i).Y * -Math.sin(angle);
            z += nodeList.get(i).Z * Math.cos(angle);

            newList.add(new Node(nodeList.get(i).label, nodeList.get(i).X, y, z));
        }
        this.nodeList = newList;
    }

    public void rotateY(double angle){
        ArrayList<Node> newList = new ArrayList<>();
        for(int i=0; i<nodeList.size();i++){
            double x = nodeList.get(i).X * Math.cos(angle);
            x += nodeList.get(i).Z * Math.sin(angle);

            double z = nodeList.get(i).X * -Math.sin(angle);
            z += nodeList.get(i).Z * Math.cos(angle);

            newList.add(new Node(nodeList.get(i).label, x, nodeList.get(i).Y, z));
        }
        this.nodeList = newList;
    }

    public void rotateZ(double angle){
        ArrayList<Node> newList = new ArrayList<>();
        for(int i=0; i<nodeList.size();i++){
            double x = nodeList.get(i).X * Math.cos(angle);
            x += nodeList.get(i).Y * Math.sin(angle);

            double y = nodeList.get(i).X * -Math.sin(angle);
            y += nodeList.get(i).Y * Math.cos(angle);

            newList.add(new Node(nodeList.get(i).label, x, y, nodeList.get(i).Z));
        }
        this.nodeList = newList;
    }

}
