package mlp;

import java.util.Arrays;
import java.util.Random;

public class Backpropagation {

    int maxEpochs;
    int inputNumber;
    int outputNumber;
    int trainingSetSize;
    int[] layers;
    int[][] neurons;
    double bias;
    double[] biasWeights;
    double[] neuronValues;
    double[] neuronErros;
    double[][] weightsInput;
    double[][] weightsNeuron;

    public Backpropagation(int inputNumber, int trainingSetSize, int[] layers) {
        this.inputNumber = inputNumber;
        this.outputNumber = layers[layers.length-1];
        this.trainingSetSize = trainingSetSize;
        this.layers = layers;
        this.bias = -1;
        this.maxEpochs = 1000;

        neurons = new int[2][layers.length];

        neurons[0][0] = 1;
        neurons[1][0] = layers[0];

        for (int i = 1; i < layers.length; i++) {
            neurons[0][i] = neurons[1][i-1] + 1;
            neurons[1][i] = neurons[0][i] + layers[i] - 1;
        }

        int neuronEdges = inputNumber*layers[0];

        for (int i = 1; i < layers.length; i++)
            neuronEdges += layers[i-1]*layers[i];

        weightsInput = new double[inputNumber][layers[0]];
        weightsNeuron = new double[neuronEdges][neuronEdges];
        neuronValues = new double[neurons[1][layers.length-1]];
        neuronErros = new double[neurons[1][layers.length-1]];
        biasWeights = new double[neurons[1][layers.length-1]];

        Random random = new Random();

        for (int i = 0; i < inputNumber; i++) {
            for (int j = 0; j < layers[0]; j++) {
                weightsInput[i][j] = random.nextDouble();
            }
        }

        for (int i = 0; i < neuronEdges; i++) {
            for (int j = 0; j < neuronEdges; j++) {
                weightsNeuron[i][j] = random.nextDouble();
            }
        }

        for (int i = 0; i < biasWeights.length; i++) {
            biasWeights[i] = random.nextDouble();
        }

        System.out.println(Arrays.toString(biasWeights));
    }

    public boolean train(double[][] inputs, int[] outputs) {
        for (int s = 0; s < 1; s++) {
            // feed-forward
            for (int l = 0; l < layers.length; l++) {
                System.out.println("Layer " + l);
                for (int j = neurons[0][l]; j <= neurons[1][l]; j++) {
                    System.out.println("Neuron " + j);
                    if (l == 0)
                        for (int i = 1; i <= inputNumber; i++) {
                            System.out.printf("Input %f Weigh %f\n", inputs[s][i - 1], weightInputNeuron(i, j));
                            neuronValues[j - 1] += inputs[s][i - 1] * weightInputNeuron(i, j);
                        }
                    else
                        for (int i = neurons[0][l-1]; i <= neurons[1][l-1]; i++) {
                            neuronValues[j - 1] += neuronValues[i - 1] * weightNeuronNeuron(i, j);
                            System.out.printf("Input %f Weigh %f\n", neuronValues[i - 1], weightNeuronNeuron(i, j));
                        }

                    neuronValues[j - 1] += bias * biasWeights[j - 1];
                    neuronValues[j - 1] = sigmoidFunction(neuronValues[j-1]);
                    System.out.println(neuronValues[j-1]);
                }
            }
        }

        return true;
    }

    public double weightInputNeuron(int input, int neuron) {
        return weightsInput[input-1][neuron-1];
    }

    public double weightNeuronNeuron(int neuron1, int neuron2) {
        return weightsNeuron[neuron1-1][neuron2-1];
    }

    private double sigmoidFunction(double x) {
        return (1.0 / (1.0 + Math.exp(-x)));
    }

    private double sigmoidDerivative(double x) {
        return (1.0 - sigmoidFunction(x) * sigmoidFunction(x));
    }
}
