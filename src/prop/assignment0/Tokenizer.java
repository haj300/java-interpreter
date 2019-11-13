package prop.assignment0;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Tokenizer implements ITokenizer {

    private static final HashMap<Character, Token> SYMBOLS;
    private static final HashSet<String> EXPR;

    static {
        SYMBOLS = new HashMap<>();
        EXPR = new HashSet<>();
        // TODO: define the different constants of our grammar
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
