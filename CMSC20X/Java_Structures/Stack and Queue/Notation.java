
/**
 * @author Melvin
 *
 */
public class Notation {

	static MyStack<Character> currentStack;
	static MyQueue<Character> currentQueue;

	/**
	 * 
	 * @param infix string
	 * @return converted postfix string
	 * @throws InvalidNotationFormatException
	 * @throws StackUnderflowException
	 * @throws StackOverflowException
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException, StackUnderflowException, StackOverflowException
	{
		currentStack = new MyStack<Character>();
		currentQueue = new MyQueue<Character>();
		String postfixString = "";

		for (int index = 0; index < infix.length(); index++) {
			char value = infix.charAt(index);
			if (value == '(') {
				currentStack.push('(');
			}
			
			
			else if (value == ')') {
				Character oper = currentStack.peek();

				while (!(oper.equals('(')) && !(currentStack.isEmpty())) {
					currentStack.pop();
					postfixString += oper.charValue();
					if (!currentStack.isEmpty()) {
						oper = currentStack.peek();
						currentStack.pop();
					} else
						throw new InvalidNotationFormatException("The notation is invalid");
				}

			} else if (value == '+' || value == '-') {
				if (currentStack.isEmpty()) {
					currentStack.push(value);
				} else {
					Character oper = currentStack.peek();
					while (!(currentStack.isEmpty() || oper.equals(('(')) || oper.equals((')')))) {
						oper = currentStack.pop();
						postfixString += oper.charValue();
					}
					currentStack.push(value);
				}
			} else if (value == '*' || value == '/') {
				if (currentStack.isEmpty()) {
					currentStack.push(value);
				} else {
					Character oper = currentStack.peek();

					while (!oper.equals(('(')) && !oper.equals(('+')) && !oper.equals(('-')) && !currentStack.isEmpty()) {
						oper = currentStack.pop();
						postfixString += oper.charValue();
					}
					currentStack.push(value);
				}
			} else {
				postfixString += value;
			}
		}

		while (!currentStack.isEmpty()) {
			Character oper = currentStack.peek();
			if (!oper.equals(('('))) {
				currentStack.pop();
				postfixString += oper.charValue();
			}
		}
		return postfixString;
	}

	
	
	/**
	 * makes postfix into infix
	 * @param postfix
	 * @return string of infix
	 * @throws InvalidNotationFormatException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 */
	public static String convertPostfixToInfix(String postfix)
			throws InvalidNotationFormatException, StackOverflowException, StackUnderflowException {

		String infixString = "";
		MyStack<String> stack = new MyStack<String>();

		for (int index = 0; index < postfix.length(); index++) {

			char value = postfix.charAt(index);
			if (value == ' ')
				continue;

			else if (Character.isDigit(value))
				stack.push("" + value);

			else if (value == '+' || value == '*' || value == '/' || value == '-') {

				if (stack.size() < 2)
					throw new InvalidNotationFormatException(
							"This should have thrown an InvalidNotationFormatException");

				else {
					String leftOp;
					String rightOp;
					String op = "";
					leftOp = stack.pop();

					rightOp = stack.pop();
					op = "(" + rightOp + value + leftOp + ")";
					stack.push(op);

				}

			}
		}

		return infixString += stack.pop();

	}

	/**
	 *  changes postfix string to a decimal answer
	 * @param postfix string
	 * @return evaluated function
	 * @throws InvalidNotationFormatException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 */
	public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException, StackOverflowException, StackUnderflowException {

		double result = 0.0;
		MyStack<String> stack = new MyStack<String>(); 

		for (int index = 0; index < postfix.length(); index++) {

			char newChar = postfix.charAt(index);
			if (newChar == ' ')
				continue;

			else if (Character.isDigit(newChar))
				stack.push("" + newChar);

			else if (newChar == '+' || newChar == '*' || newChar == '/' || newChar == '-') {

				if (stack.size() < 2)
					throw new InvalidNotationFormatException(
							"This should have thrown an InvalidNotationFormatException");

				else {

					String leftOp;
					String rightOp;
					leftOp = stack.pop();
					double op = Double.parseDouble(leftOp);
					rightOp = stack.pop();
					double Operator = Double.parseDouble(rightOp);

					switch (newChar) {

					case '+':
						result = op + Operator;
						break;

					case '-':
						result = Operator - op;
						break;

					case '*':
						result = op * Operator;
						break;

					case '/':
						result = Operator / op;
						break;
					}
					stack.push("" + result);
				}

			}

		}
		if (stack.size() > 1) {
			throw new InvalidNotationFormatException("The notation is invalid");
		}

		else {
			return result;
		}

	}

	/**
	 * evaluates the infix functions
	 * @param complexInfix string
	 * @return decimal value of the infixed string
	 * @throws InvalidNotationFormatException
	 * @throws StackUnderflowException
	 * @throws StackOverflowException
	 */
	public static double evaluateInfixExpression(String complexInfix) throws InvalidNotationFormatException, StackUnderflowException, StackOverflowException {
		String postfix = convertInfixToPostfix(complexInfix);
		double infixVal = evaluatePostfixExpression(postfix);
		return infixVal;
	}


}
