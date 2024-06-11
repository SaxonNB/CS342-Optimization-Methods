import java.math.*;
public class Main {
    public static void main(String[] args) {

        point[] points = new point[8];
        points[0] = new point(0,0);
        points[1] = new point(1,0);
        points[2] = new point(2,0);
        points[3] = new point(3,0);
        points[4] = new point(0,1.1);
        points[5] = new point(1,0.9);
        points[6] = new point(2,0.9);
        points[7] = new point(3,1.1);
        double[][] lowerBound = new double[8][8];
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                lowerBound[i][j] = dis(points[0],points[i]) + dis(points[i],points[j])+ longestPoint(j,points);
            }
        }
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                if(i != j){
                    System.out.print(lowerBound[i][j] + " ");
                }
            }
            System.out.println();
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(i != j){
                    if(lowerBound[i][j] > 10){
                        System.out.print( "("+i + " " + j+")    ");
                    }
                }
            }
        }
    }
    public static double dis(point A,point B){
        return Math.sqrt(Math.pow(A.x - B.x,2) + Math.pow(A.y - B.y,2));
    }

    public static double longestPoint(int index,point[] points){
        double a = 0;
        int recordOfIndex = 0;
        for (int i = 0; i < points.length; i++) {
            double distance = dis(points[index],points[i]);
            if(i == index){
                continue;
            }else{
                if(distance > a){
                    a = distance;
                    recordOfIndex = i;
                }
            }
        }
        return a + dis(points[0],points[recordOfIndex]);
    }
}
class point{
    double x;
    double y;

    public point(double x,double y){
        this.x = x;
        this.y = y;
    }
}