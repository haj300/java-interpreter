package prop.assignment0;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Tokenizer implements ITokenizer {

	private static final HashMap<Character, Token> Symbols;
	private static final HashSet<String> EXPR;

	static {
		Symbols = new HashMap<>();
		EXPR = new HashSet<>();

		// TODO: define the different constants of our grammar
		// TODO Use all defined tokens?

		// TODO Symbols might be unneeded since tokens are available in Token class.
		Symbols.put('(', Token.LEFT_PAREN);
		Symbols.put(')', Token.RIGHT_PAREN);
		Symbols.put('+', Token.ADD_OP);
		Symbols.put('-', Token.SUB_OP);
		Symbols.put('*', Token.MULT_OP);
		Symbols.put('/', Token.DIV_OP);
		Symbols.put('=', Token.ASSIGN_OP);
		Symbols.put(';', Token.SEMICOLON);

	}

	private IScanner scanner = null;
	private Lexeme current = null;
	private Lexeme next = null;

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		scanner = new Scanner();

	}

	@Override
	public Lexeme current() {
		return null;
	}

	@Override
	public void moveNext() throws IOException, TokenizerException {

	}

	private void consumeWhiteSpaces() throws IOException {
		consumeWhiteSpaces();

		char ch = scanner.current();
	}

	private Lexeme extractLexeme() throws IOException, TokenizerException {
		return null;
	}

	@Override
	public void close() throws IOException {

	}

}
