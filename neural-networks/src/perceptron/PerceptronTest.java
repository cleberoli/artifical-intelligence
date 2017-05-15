package perceptron;


public class PerceptronTest {

    private static double[][] generateTruthTable(int size) {
        int rows = (int)Math.pow(2, size);
        double[][] table = new double[rows][size];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < size ; j++) {
                table[i][size - j - 1] = (i/(int) Math.pow(2, j)) % 2;
            }
        }

        return table;
    }

    private static int[] generateTruthTableOutput(int size, char operation) {
        int rows = (int)Math.pow(2, size);
        double[][] table = generateTruthTable(size);
        int[] outputTable = new int[rows];
        boolean[][] booleanTable = new boolean[rows][size];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < size ; j++) {
                booleanTable[i][j] = (table[i][j] == 0) ? false : true;
            }
        }

        switch (operation) {
            case 'a' :
                for (int i = 0; i < rows; i++) {
                    boolean answer = booleanTable[i][0] & booleanTable[i][1];

                    for (int j = 2; j < size; j++) {
                        answer = answer & booleanTable[i][j];
                    }

                    outputTable[i] = (answer) ? 1 : 0;
                }
                break;

            case 'o' :
                for (int i = 0; i < rows; i++) {
                    boolean answer = booleanTable[i][0] | booleanTable[i][1];

                    for (int j = 2; j < size; j++) {
                        answer = answer | booleanTable[i][j];
                    }

                    outputTable[i] = (answer) ? 1 : 0;
                }
                break;

            case 'x' :
                for (int i = 0; i < rows; i++) {
                    boolean answer = booleanTable[i][0] ^ booleanTable[i][1];

                    for (int j = 2; j < size; j++) {
                        answer = answer ^ booleanTable[i][j];
                    }

                    outputTable[i] = (answer) ? 1 : 0;
                }
                break;
        }


        return outputTable;
    }

    private static void printPerceptronTestOutputs(int maxVariables) {
        if (maxVariables >= 2) {
            Perceptron perceptron;

            for (int i = 2; i <= maxVariables; i++) {
                int trainingSetSize = (int)Math.pow(2, i);
                perceptron = new Perceptron(i, trainingSetSize);
                double[][] input = generateTruthTable(i);

                System.out.printf("\nTesting Perceptron with AND function and %d variables\n", i);
                if (perceptron.train(input, generateTruthTableOutput(i, 'a'))) {
                    for (int j = 0; j < i; j++) {
                        System.out.print("x" + j + "\t");
                    }
                    System.out.println("s0");

                    for (int j = 0; j < trainingSetSize; j++) {
                        for (int k = 0; k < i; k++) {
                            System.out.print((int)input[j][k] + "\t");
                        }
                        System.out.println(perceptron.test(input[j]));
                    }
                } else
                    System.out.println("The Perceptron could not solve for the given inputs");

                System.out.printf("\nTesting Perceptron with OR function and %d variables\n", i);
                if (perceptron.train(input, generateTruthTableOutput(i, 'o'))) {
                    for (int j = 0; j < i; j++) {
                        System.out.print("x" + j + "\t");
                    }
                    System.out.println("s0");

                    for (int j = 0; j < trainingSetSize; j++) {
                        for (int k = 0; k < i; k++) {
                            System.out.print((int)input[j][k] + "\t");
                        }
                        System.out.println(perceptron.test(input[j]));
                    }
                } else
                    System.out.println("The Perceptron could not solve for the given inputs");

                System.out.printf("\nTesting Perceptron with XOR function and %d variables\n", i);
                if (perceptron.train(input, generateTruthTableOutput(i, 'x'))) {
                    for (int j = 0; j < i; j++) {
                        System.out.print("x" + j + "\t");
                    }
                    System.out.println("s0");

                    for (int j = 0; j < trainingSetSize; j++) {
                        for (int k = 0; k < i; k++) {
                            System.out.print((int)input[j][k] + "\t");
                        }
                        System.out.println(perceptron.test(input[j]));
                    }
                } else
                    System.out.println("The Perceptron could not solve for the given inputs");
            }
        }
    }

    public static void main(String [] args) {
        printPerceptronTestOutputs(3);
    }
}
