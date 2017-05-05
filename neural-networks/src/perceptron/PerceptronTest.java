package perceptron;

public class PerceptronTest {

    public static void main(String [] args) {

        double [][] inputs2 = {{0,0}, {0,1}, {1,0}, {1,1}};
        double [][] inputs3 = {{0,0,0}, {0,0,1}, {0,1,0}, {0,1,1}, {1,0,0}, {1,0,1}, {1,1,0}, {1,1,1}};
        int [] outputsAND2 = {0,0,0,1};
        int [] outputsAND3 = {0,0,0,0,0,0,0,1};
        int [] outputsOR2 = {0,1,1,1};
        int [] outputsOR3 = {0,1,1,1,1,1,1,1};
        int [] outputsXOR2 = {0,1,1,0};
        int [] outputsXOR3 = {0,1,1,0,1,0,0,1};

        Perceptron perceptron;

        perceptron = new Perceptron(2, 4);
        System.out.printf("Testing Perceptron with AND function and %d variables\n", 2);
        if (perceptron.train(inputs2, outputsAND2)) {
            System.out.printf("x1\tx2\ts0\n");

            for (int i = 0; i < 4; i++) {
                System.out.printf("%d\t%d\t%d\n", (int)inputs2[i][0], (int)inputs2[i][1],perceptron.test(inputs2[i]));
            }

        } else {
            System.out.println("The Perceptron could not solve for the given inputs");
        }

        System.out.println("");

        perceptron = new Perceptron(2, 4);
        System.out.printf("Testing Perceptron with OR function and %d variables\n", 2);
        if (perceptron.train(inputs2, outputsOR2)) {
            System.out.printf("x1\tx2\ts0\n");

            for (int i = 0; i < 4; i++) {
                System.out.printf("%d\t%d\t%d\n", (int)inputs2[i][0], (int)inputs2[i][1],perceptron.test(inputs2[i]));
            }

        } else {
            System.out.println("The Perceptron could not solve for the given inputs");
        }

        System.out.println("");

        perceptron = new Perceptron(2, 4);
        System.out.printf("Testing Perceptron with XOR function and %d variables\n", 2);
        if (perceptron.train(inputs2, outputsXOR2)) {
            System.out.printf("x1\tx2\ts0\n");

            for (int i = 0; i < 4; i++) {
                System.out.printf("%d\t%d\t%d\n", (int)inputs2[i][0], (int)inputs2[i][1],perceptron.test(inputs2[i]));
            }

        } else {
            System.out.println("The Perceptron could not solve for the given inputs");
        }

        System.out.println("");

        perceptron = new Perceptron(3, 8);
        System.out.println("Testing Perceptron with AND function with 3 variables");
        if (perceptron.train(inputs3, outputsAND3)) {
            System.out.printf("x1\tx2\tx3\ts0\n");

            for (int i = 0; i < 8; i++) {
                System.out.printf("%d\t%d\t%d\t%d\n", (int)inputs3[i][0], (int)inputs3[i][1], (int)inputs3[i][2],perceptron.test(inputs3[i]));
            }

        } else {
            System.out.println("The Perceptron could not solve for the given inputs");
        }

        System.out.println("");

        perceptron = new Perceptron(3, 8);
        System.out.println("Testing Perceptron with OR function with 3 variables");
        if (perceptron.train(inputs3, outputsOR3)) {
            System.out.printf("x1\tx2\tx3\ts0\n");

            for (int i = 0; i < 8; i++) {
                System.out.printf("%d\t%d\t%d\t%d\n", (int)inputs3[i][0], (int)inputs3[i][1], (int)inputs3[i][2],perceptron.test(inputs3[i]));
            }

        } else {
            System.out.println("The Perceptron could not solve for the given inputs");
        }

        System.out.println("");

        perceptron = new Perceptron(3, 8);
        System.out.println("Testing Perceptron with XOR function with 3 variables");
        if (perceptron.train(inputs3, outputsXOR3)) {
            System.out.printf("x1\tx2\tx3\ts0\n");

            for (int i = 0; i < 8; i++) {
                System.out.printf("%d\t%d\t%d\t%d\n", (int)inputs3[i][0], (int)inputs3[i][1], (int)inputs3[i][2],perceptron.test(inputs3[i]));
            }

        } else {
            System.out.println("The Perceptron could not solve for the given inputs");
        }

     }
}
