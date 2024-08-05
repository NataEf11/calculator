import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Выражение");
        String input = in.nextLine();
        try {
            String result = calc(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        String[] list = input.split(" ");
        String num1 = list[0];
        String oper = list[1];
        String num2 = list[2];
        String regexNumber = "^[0-9][0]?";
        String regexRim = "[XIVxiv]*";
        int number1;
        int number2;
        if (num1.matches(regexNumber) && num2.matches(regexNumber)) {
            number1 = Integer.parseInt(num1);
            number2 = Integer.parseInt(num2);
            int result = operation(number1, number2, oper);
            return Integer.toString(result);
        } else if (num1.matches(regexRim) && num2.matches(regexRim)) {
            number1 = checkNumber(num1);
            number2 = checkNumber(num2);
            if (number1 > 10 && number2 > 10) {
                throw new Exception("Введенные числа больше 10");
            }
            int result = operation(number1, number2, oper);
            return checkRim(result);
        } else {
            throw new Exception("Одна из переменных не удовлетворяет условию");
        }
    }

    public static int operation(int num1, int num2, String oper) throws Exception {
        switch (oper) {
            case ("+"):
                return num1 + num2;
            case ("-"):
                return num1 - num2;
            case ("*"):
                return num1 * num2;
            case ("/"):
                if (num2 == 0) {
                    throw new Exception("На ноль не делится");
                }
                return num1 / num2;
            default:
                throw new Exception("Неверное условие");
        }
    }

    public static int checkNumber(String num) {
        int result = 0;
        num = num.toUpperCase();
        for (int i = 0; i < num.length(); i++) {
            switch (num.charAt(i)) {
                case ('X'):
                    result += 10;
                    break;
                case ('I'):
                    result += 1;
                    break;
                case ('V'):
                    result += 5;
                    break;
            }
        }
        if (num.charAt(1) == 'V' || num.charAt(1) == 'X')
            return result - 2;
        else
            return result;
    }

    public static String checkRim(int num) throws Exception {
        String result = "";
        if (num - 80 >= 0) {
                result += "LXXX";
                num -= 80;
            } else if (num - 70 >= 0) {
                result += "LXX";
                num -= 70;
            } else if (num - 60 >= 0) {
                    result += "LX";
                    num -= 60;
            } else if (num - 50 >= 0) {
                result += "L";
                num -= 50;
            } else if (num - 40 >= 0) {
                result += "XL";
                num -= 40;
            } else if (num - 10 >= 0) {
                result += "X";
                num -= 10;
            } else if (num - 9 >= 0) {
                result += "IX";
                num -= 9;
            } else if (num - 5 >= 0) {
                result += "V";
                num -= 5;
            } else if (num - 4 >= 0) {
                result += "IV";
                num -= 4;
            } else if (num - 1 >= 0) {
                result += "I";
                num -= 1;
            }
        }
        return result;
    }
}
