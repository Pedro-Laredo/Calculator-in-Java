public class Calculator {
    private double result = 0;
    private String operator = "";

    
    public void calculate(double input) {
        switch (operator) {
            case "+":
                result += input;
                break;
            case "-":
                result -= input;
                break;
            case "*":
                result *= input;
                break;
            case "/":
                if (input != 0) {
                    result /= input;
                } else {
                    throw new ArithmeticException("Are you realy trying to divide by 0? ");
                }
                break;
            default:
                result = input; // first number entered
                break;
        }
    }
    public double getResult(){
        return result;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }
    public void reset(){
        result = 0;
        operator="";
    }
}
