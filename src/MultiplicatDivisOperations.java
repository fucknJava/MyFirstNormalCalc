public class MultiplicatDivisOperations { // throws NumberFormatException t

    public void MiddleOperation(Object[] readyExample, int i) {

        // выполняет смещение всех символов с концов в середину
        // помечая лишние ячейки после смещения "n"

        for (int j = i - 1; j >= 1; j--) {

            readyExample[j] = readyExample[j - 1];
            readyExample[j - 1] = "n";
        }
        for (int l = i + 1; l < readyExample.length - 1; l++) {

            readyExample[l] = readyExample[l + 1];
            readyExample[l + 1] = "n";
        }
    }

    public void MultipDivisInStartEndOperations(Object[] readyExample) {

        /* умножает/делит первые и последние 2 числа в массиве если это нужно */

        if (readyExample[1].equals("*")) {

            readyExample[2] = Double.parseDouble(readyExample[0].toString()) *
                    Double.parseDouble(readyExample[2].toString());

            readyExample[0] = "n"; // замена null, тк если середина найдет null проге пезда
            readyExample[1] = "n";

        } else if (readyExample[1].equals("/")) {

            readyExample[2] = Double.parseDouble(readyExample[0].toString()) /
                    Double.parseDouble(readyExample[2].toString());

            readyExample[0] = "n";
            readyExample[1] = "n";
        }

        if (readyExample[readyExample.length - 2].equals("*")) {

            readyExample[readyExample.length - 3] = Double.parseDouble(readyExample[readyExample.length - 3].toString()) *
                    Double.parseDouble(readyExample[readyExample.length - 1].toString());


            readyExample[readyExample.length - 2] = "n";
            readyExample[readyExample.length - 1] = "n";

        } else if (readyExample[readyExample.length - 2].equals("/")) {

            readyExample[readyExample.length - 3] = Double.parseDouble(readyExample[readyExample.length - 3].toString()) /
                    Double.parseDouble(readyExample[readyExample.length - 1].toString());

            readyExample[readyExample.length - 2] = "n";
            readyExample[readyExample.length - 1] = "n";

        }
    }

    public void MultipDivisOperation(Object[] readyExample, int i) {
        if (readyExample[i].equals("*")) {

            readyExample[i] = Double.parseDouble(readyExample[i - 1].toString()) *
                    Double.parseDouble(readyExample[i + 1].toString());

            MiddleOperation(readyExample, i); // метод, который выполняет смещение символов

        } else if (readyExample[i].equals("/")) {

            readyExample[i] = Double.parseDouble(readyExample[i - 1].toString()) /
                    Double.parseDouble(readyExample[i + 1].toString());

            MiddleOperation(readyExample, i); // метод, который выполняет смещение символов
        }
    }
}
