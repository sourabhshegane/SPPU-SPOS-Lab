%{
int lineCount,wordsCount,charsCount;
%}

%%
\n {
	charsCount++;
	lineCount++; 
   }
 
[^ \n\t]+ {wordsCount++,
	  	charsCount=charsCount+yyleng;
	  }
. {charsCount++;}
%%
int yywrap(void)  
{
 return 1;
}
int main(int argc, char*argv[]) 
{
	yyin=fopen(argv[1],"r");
 yylex();
 printf("Number of Lines are %d\nNumber of Characters are %d\nNumber of Words are %d",lineCount,charsCount,wordsCount);

 return 0;
}
