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
		INode id;
		Lexeme ao;
		Lexeme sc;

		public AssignNode(ITokenizer tz) {

			e = new ExprNode(tz);
			id = new IdNode(tz);
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			builder.append("AssingmentNode\n");
			builder.append(ao.toString() + "\n");
			e.buildString(builder, tabs++);
			builder.append(sc.toString() + "\n");

		}

	}

	private class ExprNode implements INode {

		INode t;
		INode e;
		INode ad;

		public ExprNode(ITokenizer tz) {

			ad = new AdNode(tz);
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

		}

	}

	private class TermNode implements INode {

		INode f;
		INode t;
		INode md;

		public TermNode(ITokenizer tz) {

			md = new MdNode(tz);
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
		INode i;

		public FactorNode(ITokenizer tz) {

			e = new ExprNode(tz);
			i = new IntNode(tz);

		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {

		}

	}

	private class IdNode implements INode {

		Lexeme id;

		public IdNode(ITokenizer tz) {

			try {// TODO Try/catch might be unneeded.

				if (id.value().toString().matches("\\d+")) { // https://stackoverflow.com/questions/4388546/how-to-determine-whether-a-string-contains-an-integer#comment95746806_4388597

				} else {
					throw new ParserException("Bad ID.");
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

	private class AdNode implements INode {

		Lexeme ad;

		public AdNode(ITokenizer tz) {

			try {// TODO Try/catch might be unneeded.

				if (ad.token() == Token.ADD_OP || ad.token() == Token.SUB_OP) {

				} else {
					throw new ParserException("Bad operator.");
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

	private class MdNode implements INode {

		Lexeme md;

		public MdNode(ITokenizer tz) {

			try {

				if (md.token() == Token.MULT_OP || md.token() == Token.DIV_OP) {

				} else {
					throw new ParserException("Bad operator.");
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

	private class IntNode implements INode {

		Lexeme i;

		public IntNode(ITokenizer tz) {

			try {

				if (i.value() instanceof Integer) {

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

}
