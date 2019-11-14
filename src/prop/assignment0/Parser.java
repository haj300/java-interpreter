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

			// TODO Check that first char of ID is letter.

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

			try {

				if (op.token() == Token.ADD_OP || op.token() == Token.SUB_OP) {

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

		INode t;
		INode e;

		public FactorNode(ITokenizer tz) {
			t = new TermNode(tz);
			e = new ExprNode(tz);
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
