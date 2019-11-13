package prop.assignment0;

import java.io.IOException;

public class Tokenizer implements ITokenizer {



    static {

    }

    private Scanner scanner = null;




    @Override
    public void open(String fileName) throws IOException, TokenizerException {

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

    }

    @Override
    public void close() throws IOException {

    }
}
