### My semantic model (tree structure) based regular expression (SM-Regex)

*Standard Java regex use as:*

    Pattern p = Pattern.compile(regexString);
    Matcher m = p.matcher(inputString);
    m.matches(); //boolean value returned

*And my SM-Regex use:*

    RegularExpression regex = RegularExpressionBuilder.create(regexString);
    regex.isMatch(inputString); // boolean value returned

*Or:*

    regex.isFullMath(inputString); //boolean value
    /* example:
    for regex: "[A-Z][a-z]+" 
    String "Vova" - true
    String "VovaN" - false

*Or method matchedString(String):* _[for upper example regex]_

    regex.matchedString("Vova93"); // returned "Vova"

*Or method unmatchedString(String):*

    regex.matchedString("Vova93"); // returned "93"

### How it work?
This regular expression processor based on semantic model processing.
It is recursive tear down algorithm. This is not optimal of size method. But it work more speedy.

*Work steps:*

    1. Lexer: tokenize input string (regex string) to token stream.
    2. Parser: parse token stream to syntax tree
    3. Semantic model builder: optimise syntax tree and create analogy semantic model
    4. Regular expression builder: include semantic model into resulting regular expression

**PS:**
    For good API all this steps packaged in one used patterns Facade and Fabrick Method {GoF] <br>
    This is final release (version 1.0) <br>
    _Не могу не сказать что это велосипедостроение, но все же иногда бывает полезно строить велосипеды, в учебных целях._

### Maven

For add it to your project, use maven. And add this dependency in the dependencies block in 'pom.xml'

    <dependency>
        <groupId>vova_cons</groupId>
        <artifactId>sm_regex</artifactId>
        <version>1.0.0</version>
    </dependency>

### Autor
**Vladimir Konstantinov** <br>
**anbu23477@gmail.com** <br>
**NEFU IMI** <br>
**October 2016**
