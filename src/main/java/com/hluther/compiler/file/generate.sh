jflex lexer.flex
cup -parser Parser parser.cup

mv Lexer.java ../lexer/
mv Parser.java ../parser/
mv sym.java ../parser/
