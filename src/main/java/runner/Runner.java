package runner;

public class Runner {

    public static void main(String[] args) {
        final String text = "CaLcUlAtOr";
        if (text.equalsIgnoreCase("calculator")) {
            final String expression = "(10-20)*3 - 2";
            final char[] charExpression = expression.toCharArray();
            if (charExpression.length < 0) {
                ExpressionResolver.resolver(charExpression[0], null);
            }
            for (int i=1; i < charExpression.length; i++) {
                Character currentChar = charExpression[i];
                Character prevChar = charExpression[i-1];
                ExpressionResolver.resolver(currentChar, prevChar);
            }
        }
        System.out.println(Stacks.digitStack);
        System.out.println(Stacks.signStack);
    }
}
