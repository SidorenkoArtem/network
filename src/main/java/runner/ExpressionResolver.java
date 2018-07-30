package runner;

import org.jsoup.helper.StringUtil;

public class ExpressionResolver {

    public static void resolver(final Character sign, final Character prev) {
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
                    if (Character.isDigit(prev)) {
                        final String digit = Stacks.digitStack.pop();
                        Stacks.digitStack.push(digit + sign);
                    } else {
                        Stacks.digitStack.push(sign.toString());
                    }
                } else {

                }
        }

    }


}
