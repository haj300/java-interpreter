package prop.assignment0;

/*
 * @author Katja Lindeberg
 * @author Marcus Wallén
 * @author Jesper Westin
 * 
 * Tre-grupp godkänd av Beatrice.
 */

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
		return new AssignNode();
	}

	public void close() throws IOException {
		if (tokenizer != null)
			tokenizer.close();
	}

	private String addTabs(int tabs) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < tabs; i++) {
			builder.append("\t");
		}
		return builder.toString();
	}

	private class AssignNode implements INode {
		INode e;
		Lexeme id;
		Lexeme ao;
		Lexeme sc;

		public AssignNode() throws IOException, TokenizerException {
			id = tokenizer.current();
			tokenizer.moveNext();
			ao = tokenizer.current();
			tokenizer.moveNext();
			e = new ExprNode();
			sc = tokenizer.current();
			tokenizer.moveNext();
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return (String) id.value() + ao.value() + e.evaluate(null);
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			builder.append("AssingmentNode\n");
			String tab = addTabs(++tabs);
			builder.append(tab + id.toString() + "\n");
			builder.append(tab + ao.toString() + "\n");
			builder.append(tab + "ExpressionNode\n");
			e.buildString(builder, ++tabs);
			builder.append(tab + sc.toString() + "\n");

		}

	}

	private class ExprNode implements INode {

		INode t;
		INode e;
		Lexeme op;

		public ExprNode() throws IOException, TokenizerException {
			t = new TermNode();
			op = tokenizer.current();
			if (op.token() == Token.ADD_OP || op.token() == Token.SUB_OP) {
				tokenizer.moveNext();
				e = new ExprNode();
			}
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			if (op.token() == Token.ADD_OP) {
				return (double) t.evaluate(null) + (double) e.evaluate(null);
			} else if (op.token() == Token.SUB_OP) {
				return (double) t.evaluate(null) - (double) e.evaluate(null);
			} else {
				return t.evaluate(null);
			}
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			String tab = addTabs(tabs);
			builder.append(tab + "TermNode\n");
			t.buildString(builder, ++tabs);
			if (op.token() == Token.ADD_OP || op.token() == Token.SUB_OP) {
				builder.append(tab + op.toString() + "\n");
				builder.append(tab + "ExpressionNode\n");
				e.buildString(builder, ++tabs);
			}

		}

	}

	private class TermNode implements INode {

		INode f;
		INode t;
		Lexeme op;

		public TermNode() throws IOException, TokenizerException {
			f = new FactorNode();
			op = tokenizer.current();
			if (op.token() == Token.MULT_OP || op.token() == Token.DIV_OP) {
				tokenizer.moveNext();
				t = new TermNode();
			}
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			if (op.token() == Token.MULT_OP) {
				return (double) f.evaluate(null) * (double) t.evaluate(null);
			} else if (op.token() == Token.DIV_OP) {
				return (double) f.evaluate(null) / (double) t.evaluate(null);
			} else {
				return f.evaluate(null);
			}
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			String tab = addTabs(tabs);
			builder.append(tab + "FactorNode\n");
			f.buildString(builder, ++tabs);
			if (op.token() == Token.MULT_OP || op.token() == Token.DIV_OP) {

				builder.append(tab + op.toString() + "\n");
				builder.append(tab + "TermNode\n");
				t.buildString(builder, ++tabs);
			}
		}

	}

	private class FactorNode implements INode {

		INode e;
		Lexeme firstCh;
		Lexeme parenRight;

		public FactorNode() throws IOException, TokenizerException {
			firstCh = tokenizer.current();
			tokenizer.moveNext();

			if (firstCh.token() == Token.LEFT_PAREN) {
				e = new ExprNode();
				parenRight = tokenizer.current();
				tokenizer.moveNext();
			}
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			if (firstCh.token() == Token.INT_LIT) {
				return Double.valueOf(firstCh.value().toString());
			} else if (firstCh.token() == Token.LEFT_PAREN) {
				return e.evaluate(null);
			}
			throw new ParserException("Wrong token");
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			String tab = addTabs(tabs);
			if (firstCh.token() == Token.INT_LIT) {
				builder.append(tab + firstCh.toString() + "\n");
			} else {
				builder.append(tab + firstCh.toString() + "\n");
				builder.append(tab + "ExpressionNode\n");
				e.buildString(builder, ++tabs);
				builder.append(tab + parenRight.toString() + "\n");
			}
		}
	}

}
