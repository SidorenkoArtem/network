package runner;

public class ExpressionResolver {

    public static void resolver(final Character sign) {
        switch (sign) {
            case ' ' :
                break;
            case '(' :
                Stacks.signStack.push(sign.toString());
                break;
            case ')' :
                Stacks.signStack.push(sign.toString());
                break;
            case '+' :
                Stacks.signStack.push(sign.toString());
                break;
            case '-' :
                Stacks.signStack.push(sign.toString());
                break;
            case '*' :
                Stacks.signStack.push(sign.toString());
                break;
            case '/' :
                Stacks.signStack.push(sign.toString());
                break;
            default:
                if (Character.isDigit(sign)) {
//                    if (Character.isDigit()) {
//                        final String digit = Stacks.digitStack.pop();
//                        Stacks.digitStack.push(digit + sign);
//                    }
                    Stacks.digitStack.push(sign.toString());
                } else {
                    //TODO Error
                }
        }

    }


}
