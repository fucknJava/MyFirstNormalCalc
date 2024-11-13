public class PlusMinusOperations {



    public double plusMinusOperation(Object[] Example, double result, int startPos, int endPos) {

        /* прибавляет и отнимает числа в отфильтрованном массиве */

        for(; startPos < endPos; startPos++) {

            if (Example[startPos].equals("+")) {

                if (startPos != 1) {
                    result += Double.parseDouble(Example[startPos + 1].toString());
                } else {
                    result += Double.parseDouble(Example[startPos - 1].toString()) +
                            Double.parseDouble(Example[startPos + 1].toString());
                }
                return result;

            } else if (Example[startPos].equals("-")) {

                if (startPos != 1) {
                    result -= Double.parseDouble(Example[startPos + 1].toString());

                } else {
                    result += Double.parseDouble(Example[startPos - 1].toString()) -
                            Double.parseDouble(Example[startPos + 1].toString());
                }
                return result;
            }
        }
        return result;
    }
}
