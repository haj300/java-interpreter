package prop.assignment0;

import java.io.IOException;

public class Parser implements IParser {
	private ITokenizer tokenizer = null;

	public void open(String fileName) throws IOException, TokenizerException {
		tokenizer = new Tokenizer();
		tokenizer.open(fileName);
		tokenizer.moveNext();
	}

	public INode parse() throws IOException, TokenizerException, ParserException {
		if (tokenizer == null)
			throw new IOException("No open file.");
		return new AssignNode((tokenizer));
	}

	public void close() throws IOException {
		if (tokenizer != null)
			tokenizer.close();
	}

	private void addTabs(StringBuilder builder, int tabs) {

	}

	private class AssignNode implements INode {
		INode e;
		INode l;
		Lexeme id;
		Lexeme ao;
		Lexeme sc;

		public AssignNode(ITokenizer tz) {

			try {// TODO Try/catch might be unneeded.

				if (id.toString().matches("\\d+")) { // https://stackoverflow.com/questions/4388546/how-to-determine-whether-a-string-contains-an-integer#comment95746806_4388597

				} else {
					throw new ParserException("Bad ID.");
				}

			} catch (Exception e) {

			}

			l = new LetterNode(tz);
			e = new ExprNode(tz);
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			builder.append("AssingmentNode\n");
			builder.append(id.toString() + "\n");
			builder.append(ao.toString() + "\n");
			e.buildString(builder, tabs++);
			builder.append(sc.toString() + "\n");

		}

	}

	private class ExprNode implements INode {

		INode t;
		INode e;
		Lexeme op;

		public ExprNode(ITokenizer tz) {

			try {// TODO Try/catch might be unneeded.

				if (op.token() == Token.ADD_OP || op.token() == Token.SUB_OP) {

				} else {
					throw new ParserException("Bad operator.");
				}

			} catch (Exception e) {

			}

			t = new TermNode(tz);
			e = new ExprNode(tz);
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			builder.append("ExpressionNode\n");
			t.buildString(builder, tabs++);
			builder.append(op.toString() + "\n");
			e.buildString(builder, tabs++);
		}

	}

	private class TermNode implements INode {

		INode f;
		INode t;
		Lexeme op;

		public TermNode(ITokenizer tz) {

			try {

				if (op.token() == Token.MULT_OP || op.token() == Token.DIV_OP) {

				} else {
					throw new ParserException("Bad operator.");
				}

			} catch (Exception e) {

			}

			f = new FactorNode(tz);
			t = new TermNode(tz);
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {

		}

	}

	private class FactorNode implements INode {

		INode e;
		Lexeme i;

		public FactorNode(ITokenizer tz) {

			try {

				if (i.value() instanceof Integer) {
					e = new ExprNode(tz);
				} else {
					throw new ParserException("Bad operand.");
				}

			} catch (Exception e) {

			}

		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {

		}

	}

	private class LetterNode implements INode {

		Lexeme lexeme;

		public LetterNode(ITokenizer tz) {

		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {

		}
	}

	private class NumberNode implements INode {

		Lexeme lexeme;

		public NumberNode(ITokenizer tz) {

		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {

		}
	}

	private class SymbolNode implements INode {

		Lexeme lexeme;

		public SymbolNode(Tokenizer tz) {

		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {

		}
	}

}
