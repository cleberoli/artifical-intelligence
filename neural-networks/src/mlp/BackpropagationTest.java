package mlp;

public class BackpropagationTest {

    public static void main(String[] args) {
        double[][] input = {{0,0},{0,1},{1,0},{1,1}};
        int[] output = {0,0,0,1};
        int[] layers = {3,2,1};
        Backpropagation backpropagation = new Backpropagation(2, 4, layers);

        backpropagation.train(input,output);
    }
}
