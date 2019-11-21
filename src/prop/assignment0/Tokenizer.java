package prop.assignment0;

/*
 * @author Katja Lindeberg
 * @author Marcus Wallén
 * @author Jesper Westin
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tokenizer implements ITokenizer {

	private static final Map<Character, Token> symbols = new HashMap<>();
	private IScanner scanner = null;
	private Lexeme current = null;
	private Lexeme next = null;

	public Tokenizer() {
		symbols.put('(', Token.LEFT_PAREN);
		symbols.put(')', Token.RIGHT_PAREN);
		symbols.put(';', Token.SEMICOLON);
		symbols.put('+', Token.ADD_OP);
		symbols.put('-', Token.SUB_OP);
		symbols.put('*', Token.MULT_OP);
		symbols.put('/', Token.DIV_OP);
		symbols.put('=', Token.ASSIGN_OP);
	}

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		scanner = new Scanner();
		scanner.open(fileName);
		scanner.moveNext();
		next = extractLexeme();
	}

	@Override
	public Lexeme current() {
		return current;
	}

	@Override
	public void moveNext() throws IOException, TokenizerException {
		if (scanner == null)
			throw new IOException("File could not be found");

		current = next;
		if (next.token() != Token.EOF)
			next = extractLexeme();
	}

	private void consumeWhiteSpaces() throws IOException {
		while (Character.isWhitespace(scanner.current())) {
			scanner.moveNext();
		}
	}

	private Lexeme extractLexeme() throws IOException, TokenizerException {
		consumeWhiteSpaces();
		Character currentCharacter = scanner.current();
		StringBuilder sb = new StringBuilder();

		if (currentCharacter == Scanner.EOF) {
			return new Lexeme(currentCharacter, Token.EOF);
		} else if (Character.isLetter(currentCharacter)) {
			while (Character.isLetter(scanner.current())) {
				sb.append(scanner.current());
				scanner.moveNext();
			}
			return new Lexeme(sb.toString(), Token.IDENT);
		} else if (Character.isDigit(currentCharacter)) {
			while (Character.isDigit(scanner.current())) {
				sb.append(scanner.current());
				scanner.moveNext();
			}
			return new Lexeme(sb.toString(), Token.INT_LIT);
		} else if (symbols.containsKey(currentCharacter)) {
			scanner.moveNext();
			return new Lexeme(currentCharacter, symbols.get(currentCharacter));
		} else {
			throw new TokenizerException("Unknown character: " + currentCharacter);
		}
	}

	@Override
	public void close() throws IOException {
		if (scanner != null) {
			scanner.close();
		}
	}

}
