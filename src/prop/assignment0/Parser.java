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
		return new BlockNode();
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

	private class BlockNode implements INode {
		INode s;
		Lexeme stmt;

		public BlockNode() throws IOException, TokenizerException {
			stmt = tokenizer.current();
			tokenizer.moveNext();
			s = new StmtsNode();

		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return (String) stmt.value() + s.evaluate(null);
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			builder.append("BlockNode\n");
			String tab = addTabs(++tabs);
			builder.append(tab + stmt.toString() + "\n");

			builder.append(tab + "ExpressionNode\n");
			s.buildString(builder, ++tabs);

		}

	}

	private class StmtsNode implements INode {

		INode a;
		INode s;

		public StmtsNode() throws IOException, TokenizerException {
			a = new AssignNode();
			s = new StmtsNode();

		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			return a.evaluate(null);

		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			String tab = addTabs(tabs);
			builder.append(tab + "TermNode\n");
			a.buildString(builder, ++tabs);

		}

	}

	private class AssignNode implements INode {

		INode e;
		Lexeme id;
		Lexeme sc;

		public AssignNode() throws IOException, TokenizerException {
			e = new ExprNode();
			id = tokenizer.current();
			if (id.token() == Token.MULT_OP || id.token() == Token.DIV_OP) {
				tokenizer.moveNext();

			}
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			if (id.token() == Token.ASSIGN_OP) {
				return (double) e.evaluate(null);
			} else if (id.token() == Token.SEMICOLON) {
				return (double) e.evaluate(null);
			} else {
				return e.evaluate(null);
			}
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			String tab = addTabs(tabs);
			builder.append(tab + "FactorNode\n");
			e.buildString(builder, ++tabs);
			if (id.token() == Token.MULT_OP || id.token() == Token.DIV_OP) {

				builder.append(tab + id.toString() + "\n");
				builder.append(tab + "TermNode\n");

			}
		}

	}

	private class ExprNode implements INode {

		INode t;
		INode e;
		Lexeme op;

		public ExprNode() throws IOException, TokenizerException {
			op = tokenizer.current();
			tokenizer.moveNext();

			if (op.token() == Token.LEFT_PAREN) {
				t = new TermNode();
				tokenizer.moveNext();
				// TODO e = new ExprNode();
			}
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			if (op.token() == Token.INT_LIT) {
				return Double.valueOf(op.value().toString());
			} else if (op.token() == Token.LEFT_PAREN) {
				return t.evaluate(null);
			}
			throw new ParserException("Wrong token");
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			String tab = addTabs(tabs);
			if (op.token() == Token.INT_LIT) {
				builder.append(tab + op.toString() + "\n");
			} else {
				builder.append(tab + op.toString() + "\n");
				builder.append(tab + "ExpressionNode\n");
				t.buildString(builder, ++tabs);
			}
		}
	}

	private class TermNode implements INode {

		INode f;
		INode t;
		Lexeme firstCh;
		Lexeme parenRight;

		public TermNode() throws IOException, TokenizerException {
			firstCh = tokenizer.current();
			tokenizer.moveNext();

			f = new FactorNode();
			t = new TermNode();
			parenRight = tokenizer.current();
			tokenizer.moveNext();

		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			if (firstCh.token() == Token.INT_LIT) {
				return Double.valueOf(firstCh.value().toString());
			} else if (firstCh.token() == Token.LEFT_PAREN) {
				return f.evaluate(null);
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
				f.buildString(builder, ++tabs);
				builder.append(tab + parenRight.toString() + "\n");
			}
		}
	}

	private class FactorNode implements INode {

		// TODO Fråga om grammatiken > factor = int | id | ’(’ , expr , ’)’ ;

		INode e;
		Lexeme il;
		Lexeme id;
		Lexeme lp;
		Lexeme rp;

		public FactorNode() throws IOException, TokenizerException {
			il = tokenizer.current();
			tokenizer.moveNext();

			if (il.token() == Token.LEFT_PAREN) {
				e = new ExprNode();
				id = tokenizer.current();
				tokenizer.moveNext();
			}
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			if (il.token() == Token.INT_LIT) {
				return Double.valueOf(il.value().toString());
			} else if (il.token() == Token.LEFT_PAREN) {
				return e.evaluate(null);
			}
			throw new ParserException("Wrong token");
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			String tab = addTabs(tabs);
			if (il.token() == Token.INT_LIT) {
				builder.append(tab + il.toString() + "\n");
			} else {
				builder.append(tab + il.toString() + "\n");
				builder.append(tab + "ExpressionNode\n");
				e.buildString(builder, ++tabs);
				builder.append(tab + id.toString() + "\n");
			}
		}
	}

}
