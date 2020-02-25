import java.util.*;
class calculator
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Program to solve complex equation of 2 & 3 variable\n-by Rahul Nikam\n\n");
        int choice = 0;
        boolean choic;
        do
        {
            int variable;
            do
            {
                System.out.print("No.of unknown(2 or 3)=");
                variable = in.nextInt();
                if(variable != 2 && variable != 3)
                    System.out.println("Wrong Entry.....!!!!!!");
            }while(variable != 2 && variable != 3);
            int row = 2,colomn = 6;
            if(variable == 3)
            {
                row = 3;
                colomn = 8;
            }
            float [][] a = new float[3][8];
            for(int i = 0; i < row; i++)
            {
                System.out.println("For equation no.("+(i+1)+")");
                for(int j = 0; j < colomn; j++)
                {
                    if(j % 2 == 0)
                    {
                        if(j == 0)
                            System.out.println("Enter the coef of 'X'");
                        else if(j == 2)
                            System.out.println("Enter the coef of 'Y'");
                        else if(j == 4)
                        {
                            if(variable == 2)
                                System.out.println("Enter the constant");
                            else
                                System.out.println("Enter the coef of 'Z'");
                        }
                        else
                            System.out.println("Enter the constant");
                        System.out.print("Real part=");
                    }
                    else
                        System.out.print("Img part=");
                    a[i][j] = in.nextFloat();
                }
            }
            float a1r = a[0][0],a1i = a[0][1],b1r = a[0][2],b1i = a[0][3],c1r = a[0][4],c1i = a[0][5],d1r = a[0][6],d1i = a[0][7];
            float a2r = a[1][0],a2i = a[1][1],b2r = a[1][2],b2i = a[1][3],c2r = a[1][4],c2i = a[1][5],d2r = a[1][6],d2i = a[1][7];
            float a3r = a[2][0],a3i = a[2][1],b3r = a[2][2],b3i = a[2][3],c3r = a[2][4],c3i = a[2][5],d3r = a[2][6],d3i = a[2][7];
            if(variable == 2)
            {
                d1r = c1r;d1i = c1i;
                d2r = c2r;d2i = c2i;
            }
            calculateDitterminantOfMatrics cdm = new calculateDitterminantOfMatrics();
            float delr = cdm.calculateMat(choic = true,variable,a1r,a1i,b1r,b1i,c1r,c1i,a2r,a2i,b2r,b2i,c2r,c2i,a3r,a3i,b3r,b3i,c3r,c3i);
            float deli = cdm.calculateMat(choic = false,variable,a1r,a1i,b1r,b1i,c1r,c1i,a2r,a2i,b2r,b2i,c2r,c2i,a3r,a3i,b3r,b3i,c3r,c3i);
            float delxr = cdm.calculateMat(choic = true,variable,d1r,d1i,b1r,b1i,c1r,c1i,d2r,d2i,b2r,b2i,c2r,c2i,d3r,d3i,b3r,b3i,c3r,c3i);
            float delxi = cdm.calculateMat(choic = false,variable,d1r,d1i,b1r,b1i,c1r,c1i,d2r,d2i,b2r,b2i,c2r,c2i,d3r,d3i,b3r,b3i,c3r,c3i);
            float delyr = cdm.calculateMat(choic = true,variable,a1r,a1i,d1r,d1i,c1r,c1i,a2r,a2i,d2r,d2i,c2r,c2i,a3r,a3i,d3r,d3i,c3r,c3i);
            float delyi = cdm.calculateMat(choic = false,variable,a1r,a1i,d1r,d1i,c1r,c1i,a2r,a2i,d2r,d2i,c2r,c2i,a3r,a3i,d3r,d3i,c3r,c3i);
            float delzr = 0,delzi = 0;
            if(variable == 3)
            {
                delzr = cdm.calculateMat(choic = true,variable,a1r,a1i,b1r,b1i,d1r,d1i,a2r,a2i,b2r,b2i,d2r,d2i,a3r,a3i,b3r,b3i,d3r,d3i);
                delzi = cdm.calculateMat(choic = false,variable,a1r,a1i,b1r,b1i,d1r,d1i,a2r,a2i,b2r,b2i,d2r,d2i,a3r,a3i,b3r,b3i,d3r,d3i);
            }
            simpleCalculator sc = new simpleCalculator();
            float xr = sc.division(choic = true,delxr,delxi,delr,deli);
            float xi = sc.division(choic = false,delxr,delxi,delr,deli);
            float yr = sc.division(choic = true,delyr,delyi,delr,deli);
            float yi = sc.division(choic = false,delyr,delyi,delr,deli);
            float zr = 0,zi = 0;
            if(variable == 3)
            {
                zr = sc.division(choic = true,delzr,delzi,delr,deli);
                zi = sc.division(choic = false,delzr,delzi,delr,deli);
            }
            displayResult dr = new displayResult();
            System.out.print("\n\n\t*****Answer***** \nValue of X = ");
            dr.display(xr,xi);
            System.out.print("Value of Y = ");
            dr.display(yr,yi);
            if(variable == 3)
            {
                System.out.print("Value of Z = ");
                dr.display(zr,zi);
            }
            System.out.println("\nDo you want to continue then press '1'");
            choice = in.nextInt();
        }while(choice == 1);
    }
}
class displayResult
{
    public void display(float realAnswer,float imgAnswer)
    {
        if(imgAnswer < 0)
        {
            float i = (0 - imgAnswer);
            System.out.println(realAnswer+" - j"+i);
        }
        else
            System.out.println(realAnswer+" + j"+imgAnswer);
    }
}
class calculateDitterminantOfMatrics
{
    private float real,img;
    public float calculateMat(boolean choic,int variable,float p1r,float p1i,
                              float q1r,float q1i,float r1r,float r1i,float p2r,float p2i,float q2r,
                              float q2i,float r2r,float r2i,float p3r,float p3i,float q3r,float q3i,float r3r,float r3i)
    {
        float x1r,x1i,y1r,y1i,z1r,z1i;
        boolean cho;
        if(variable == 2)
        {
            if(choic == true)
            {
                x1r = ((p1r * q2r) - (p1i * q2i));
                y1r = ((q1r * p2r) - (q1i * p2i));
                real = (x1r - y1r);
                return(real);
            }
            else
            {
                x1i = ((p1r * q2i) + (q2r * p1i));
                y1i = ((q1r * p2i) + (p2r * q1i));
                img = (x1i - y1i);
                return(img);
            }
        }
        else
        {
            if(choic == true)
            {
                matricsCalculation mcn = new matricsCalculation();
                x1r = mcn.complexCalculator(cho = true,p1r,p1i,q2r,q2i,r3r,r3i,r2r,r2i,q3r,q3i);
                x1i = mcn.complexCalculator(cho = false,p1r,p1i,q2r,q2i,r3r,r3i,r2r,r2i,q3r,q3i);
                y1r = mcn.complexCalculator(cho = true,q1r,q1i,p2r,p2i,r3r,r3i,r2r,r2i,p3r,p3i);
                y1i = mcn.complexCalculator(cho = false,q1r,q1i,p2r,p2i,r3r,r3i,r2r,r2i,p3r,p3i);
                z1r = mcn.complexCalculator(cho = true,r1r,r1i,p2r,p2i,q3r,q3i,q2r,q2i,p3r,p3i);
                z1i = mcn.complexCalculator(cho = false,r1r,r1i,p2r,p2i,q3r,q3i,q2r,q2i,p3r,p3i);
                img = ((x1i + z1i) - y1i);
                real = ((x1r + z1r) - y1r);
                return(real);
            }
            else  
                return(img);
        }
    }
}
class matricsCalculation
{
    private float p11r,p11i,q11r,q11i,p12r,p12i,p22r,p22i;
    public float complexCalculator(boolean cho,float j1r,float j1i,float k1r,float k1i,float l1r,
                                   float l1i,float m1r,float m1i,float n1r,float n1i)
    {
        if(cho == true)
        {
            p11r = ((k1r * l1r) - (k1i * l1i));
            p11i = ((k1r * l1i) + (k1i * l1r));
            q11r = ((m1r * n1r) - (m1i * n1i));
            q11i = ((m1r * n1i) + (n1r * m1i));
            p12r = (p11r - q11r);
            p12i = (p11i - q11i);
            p22r = ((j1r * p12r) - (j1i * p12i));
            p22i = ((j1r * p12i) + (j1i * p12r));
            return(p22r);
        }
        else
            return(p22i);
    }
}
class simpleCalculator
{
    public float division(boolean choic,float a,float b,float c,float d)
    {
        if(choic == true)
            a = (((a * c) + (b * d)) / ((c * c) + (d * d)));
        else
            a = (((c * b) - (a * d)) / ((c * c) + (d * d)));
        return(a);
    }
}
