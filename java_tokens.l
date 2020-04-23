%{

FILE* yyin;
%}
DIGIT [0-9]
NUMBER {DIGIT}+
REAL {DIGIT}*[.]{NUMBER}
TEXT [A-Za-z]
KEYWORDS "class"|"static"
DATATYPE "int"|"float"|"double"|"long"|"void"|"String"
CONDITIONAL_STATEMENT "if"|"else"|"else if"|"switch"|"case"
ITERATIVE_STATEMENT "for"|"while"|"do"
IMPORT_STATEMENT "import".*";"
SEMI_COLON ";"
IDENTIFIER {TEXT}({DIGIT}|{TEXT}|"_")*
NONIDENTIFIER {DIGIT}({TEXT}|{DIGIT}|"_")*
ARITH_OPERATOR "+"|"-"|"/"|"%"|"*";
LOGICAL_OPERATOR "&&"|"||"|"!"|"!="
RELATIONAL_OPERATOR "<"|">"|"<="|">="|"=="
UNARY_OPERATOR "++"|"--"
ACCESS_SPECIFIER "public"|"private"|"protected"|""
FUNCTION {ACCESS}{DATATYPE}{IDENTIFER}"("({DATATYPE}{IDENTIFIER})*")"

%%
[ \n\t]+ ;
{IMPORT_STATEMENT} {printf("%s\tis an Import Statement\n",yytext);}
{CONDITIONAL_STATEMENT} { printf("%s\tis an Conditional Statement\n",yytext);}
{ITERATIVE_STATEMENT} {printf("%s\t is an Iterative Statement\n",yytext);}
{DATATYPE}  {printf("%s\tis a Datatype\n",yytext);}
{ACCESS_SPECIFIER} {printf("%s\tis an Acess Specifier\n",yytext);}
{KEYWORDS} {printf("%s\tis a Keywords\n",yytext);}
{IDENTIFIER} {printf("%s\tis an Identifier\n",yytext);}
{REAL} {printf("%s\tis a Real Number\n",yytext);}
{NUMBER} {printf("%s\tis an Integer\n",yytext);}
{NONIDENTIFIER} {printf("%s\tis a Non Ideentifier\n",yytext);}
{SEMI_COLON} {printf("%s\tis a Delimiter\n",yytext);}
{UNARY_OPERATOR} {printf("%s\tis an Unary Operator\n",yytext);}
{ARITH_OPERATOR} {printf("%s\tis an Arithmetic Operator\n",yytext);}
{LOGICAL_OPERATOR} {printf("%s\tis a Logical Operator\n",yytext);}
{RELATIONAL_OPERATOR} {printf("%s\tis a Relational Operator\n",yytext);}
"=" {printf("%s\tis an Assignment Operator\n",yytext);}
"{"  {printf("%s\tis Begining of the Block\n",yytext);}
"}"  {printf("%s\tis End of the Block\n",yytext);}
"("  {printf("%s\tis Begining of the Paranthesis\n",yytext);}
")"  {printf("%s\tis End of the Paranthesis\n",yytext);}
. ;

%%
int yywrap()
{
	return 1;
}

int main(int argc,char* argv[])
{
	yyin=fopen(argv[1],"r");
	yylex();
	fclose(yyin);
}
