import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String StartExample;
        String[] Startsymbols;
        String example;
        String[] symbols;

        System.out.println("\nПриветствуем тебя в улучшенном калькуляторе! Введи пример");
        while (true) {

            StringBuilder sb = new StringBuilder();

            MultiplicatDivisOperations go = new MultiplicatDivisOperations();

            PlusMinusOperations start = new PlusMinusOperations();

            System.out.println();

            StartExample = scanner.nextLine();

            Startsymbols = StartExample.split(" "); // убираем пробелы

            for (String str : Startsymbols) {
                sb.append(str);
            }

            example = sb.toString(); // пример без пробелов

            symbols = example.split(""); // отделяем каждый символ

            Object[] symAndNums = new Object[symbols.length];


            int counterSAN = 0;
            int counterSym = 0;
            int counterTry = 0; // счётчик для подсчета выполнений блока try
            int counterCatch = 0; // счётчик для подсчёта выполнений блока catch
            int counterBracketRight = 0;
            int counterBracketLeft = 0;

            while (counterSym < symbols.length) {
                try {

                    Double.parseDouble(symbols[counterSym]);

                    if(counterCatch >= 1) { // если catch сработал хотя бы 1 раз
                        // то переходим на след ячейку массива, чтобы туда поместить число
                        counterSAN++;
                    }
                    counterCatch = 0;

                     // проверка, первый раз ли сработал try после нахождения символа?
                    if (counterTry == 0) {  // если да
                        symAndNums[counterSAN] = ""; // то преобразуем текущую ячейку null в "", чтобы число поместить
                        counterTry++; // плюсуем счетчик try для оповещения, что try сработал минимум 1 раз
                        // и ячейку преобразовывать не над, тк число сформировалось не полностью

                        // 22+23+20
                    } else {
                        // если нет, то ниче не изменяем, тк текущая ячейка массива тоже число
                    }
                    symAndNums[counterSAN] += symbols[counterSym];
                    counterSym++;

                } catch (NumberFormatException e) {
                    counterCatch++;

                    if (symbols[counterSym].equals("(")) {  // если правая скобка нашлась, то увеличиваем её счетчик на 1
                        counterBracketLeft++;

                    } else if (symbols[counterSym].equals(")")) { // если левая скобка нашлась, то увеличиваем её счетчик на 1
                        counterBracketRight++;

                        if (counterBracketLeft < counterBracketRight) { // если чел ввел сперва ) эту скобку
                            System.out.println("Ошибка. Некорректно введён пример.");
                            break;
                        }
                    }
                    counterSAN++;
                    counterTry = 0; // обнуляем счётчик try тк нашелся символ, чтобы при срабатывании try, ячейка под число было не null

                    symAndNums[counterSAN] = symbols[counterSym];
                    counterSym++;
                }
            }

            int nullCounter = 0;
            for(int i=0; i < symAndNums.length; i++) {
                if(symAndNums[i] == null) {
                    nullCounter++;
                }
            }
            Object[] readyExample = new Object[symAndNums.length - nullCounter];

            for(int i=0; i < symAndNums.length; i++) {
                if(symAndNums[i] != null) {
                    readyExample[i] = symAndNums[i];
                }
            }


            if (counterBracketLeft == 0 && counterBracketRight == 0) {
                  // если скобки не найдены, продолжаем.
            } else if (counterBracketRight == counterBracketLeft) { // если счётчики левой и правой скобки совпали,
                // и счётчики не пустые, то значит в скобках нужно выполнить операцию

                int[] leftBrackets = new int[counterBracketLeft]; // массивы для сохранения туда номеров скобок
                int[] rightBrackets = new int[counterBracketRight];



                counterBracketRight = 0;
                counterBracketLeft = 0;

                for(int i = 0; i < readyExample.length; i++) {

                    if (readyExample[i].equals("(")) {

                        if (readyExample[i+1].equals("-")) { // если после левой скобки минус, делаем число отрицательным
                            try {

                                readyExample[i+2] = -Math.abs((Double) readyExample[i+2]);
                                for (int t = i+1; t >= 1; t--) {

                                    readyExample[t] = readyExample[t - 1]; // уничтожаем лишний минус
                                    readyExample[t - 1] = "n";
                                }

                            } catch (NumberFormatException e) {
                                System.out.println("Ошыбка. Некорректно введён пример");
                            }

                        }
                        counterBracketLeft++;

                    } else if (readyExample[i].equals(")")) {

                        rightBrackets[counterBracketRight] = i; // как только нашлась правая скобка, её номер передан в скобочный массив
                        leftBrackets[counterBracketLeft-1] = i; // под этим же счётчиком передается её левая скобка

                        /* таким образом, скобки будут выполняться в правильном порядке,
                        сперва самые вложенные(если есть) затем в более внешние */

                        counterBracketRight++;

                    }
                }

                int j = leftBrackets.length;

                for(int i = 0; i < j; i++) {

                    for(int l = leftBrackets[i]+1;
                        l < rightBrackets[i]; l++) {

                        go.MultipDivisOperation(readyExample, l);
                    }

                    int symbolCounter = 0; // счётчик оставшихся символов после */

                    for(int l = leftBrackets[i]+1; // цикл, который считает кол-во символов между скобками, после */
                        l < rightBrackets[i]; l++) {
                        symbolCounter++; // передавая кол-во в счётчик
                    }

                    if (symbolCounter == 1) { // если после */ остался 1 символ(число),
                        go.MiddleOperation(readyExample, leftBrackets[i] + 1); // то тогда избавляемся от лишних скобок

                    } else { // если нет, те там остались операции +-

                       readyExample[rightBrackets[i]] = start. // то символу правой скобке присваиваем результат операций +-
                               plusMinusOperation(readyExample, 0, leftBrackets[i] + 1, rightBrackets[i]);

                       // а остальные символы после выполнение +- в скобках перетаскиваем в лево
                       for(int f = leftBrackets[i]; f < rightBrackets[i]; f++) {
                           // начинаем с левой скобки, заканчиваем символом перед результатом

                           for(int h = f; h >= 1; h--) { // и каждый символ перетаскивается влево помечая после себя в конче n
                               readyExample[h] = readyExample[h - 1];
                               readyExample[h - 1] = "n";
                           }
                       }
                    }

                }

            } else {
                System.out.println("Ошибка. Некорректно введён пример.");
            }

            go.MultipDivisInStartEndOperations(readyExample); // выполняет умножение/деление в начале и конце массива

            for (int i = 1; i < readyExample.length - 1; i++) {

                go.MultipDivisOperation(readyExample, i);
            }

            int nullPointer = 0;

            for(int i = 0; i < readyExample.length; i ++) {
                if (readyExample[i].equals("n")) {
                    nullPointer++;
                }
            }

            Object[] Example2 = new Object[readyExample.length - nullPointer];
            int Ex2counter = 0;

            for(int i = 0; i < readyExample.length ; i++) {
                System.out.println(readyExample[i]);
                if (!readyExample[i].equals("n")) {
                    Example2[Ex2counter] = readyExample[i];

                    Ex2counter++;
                }

            }

            if(Example2.length != 1) { // если после */ остались ещё + и -

                double result = start.plusMinusOperation(Example2, 0, 0, Example2.length); // плюсует и минусует числа в отфильтрованном массиве
                System.out.println("\nОтвет: " + result);

            } else { // если нет(были ток */ и осталось в массиве ток одно число)
                System.out.println("\nОтвет: " + Example2[0]);
            }
        } // 7/3/1/5/9/2/3/3/3 апасный пример
    }
}