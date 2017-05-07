package perceptron;

import java.util.Random;

public class Perceptron {

    int maxEpochs;
    int inputNumber;
    int trainingSetSize;
    double [] weights;
    double bias;
    double biasWeight;
    double eta;
    double errorThreshold;

    public Perceptron(int inputNumber, int trainingSetSize) {
        this.inputNumber = inputNumber;
        this.trainingSetSize = trainingSetSize;
        this.weights = new double[inputNumber];
        this.bias = -1;
        this.eta = 0.3;
        this.maxEpochs = 1000;
        this.errorThreshold = 0;

        Random random = new Random();

        for (int i = 0; i < inputNumber; i++) {
            weights[i] = random.nextDouble();
        }

        biasWeight = random.nextDouble();
    }

    public boolean train(double [][] inputs, int [] outputs) {
        double totalError = Double.POSITIVE_INFINITY;

        for (int k = 0; k < maxEpochs; k++) {
            totalError = 0;

            for (int j = 0; j < trainingSetSize; j++) {
                double productSummation = 0;

                for (int i = 0; i < inputNumber; i++) {
                    productSummation += inputs[j][i] * weights[i];
                }

                productSummation += bias * biasWeight;

                int error = outputs[j] - activationFunction(productSummation);
                totalError += Math.abs(error);

                for (int i = 0; i < inputNumber; i++) {
                    weights[i] += eta * error * inputs[j][i];
                    biasWeight += eta * error * bias;
                }
            }

            if (totalError <= errorThreshold)
                return true;
        }

        if (totalError > errorThreshold)
            return false;

        return true;
    }

    public int test(double [] input) {
        double productSummation = 0;

        for (int i = 0; i < inputNumber; i++) {
            productSummation += input[i] * weights[i];
        }

        productSummation += bias * biasWeight;

        return activationFunction(productSummation);
    }

    private int activationFunction(double value) {
        return (value >= 0) ? 1 : 0;
    }

}
