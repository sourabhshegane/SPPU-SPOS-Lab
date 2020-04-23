%{
#include "y.tab.h"
%}
%%
[\t ] ;	
am|is|are|have|has|can|will|shall|eat|sing|go|goes|loves { printf("%s is a VERB\n",yytext);return VERB;}
very|simply|gently { printf("%s\t is an adverb\n",yytext);return(ADVERB); }
and|or|also|so|but|if|then {printf("\t %s is a conjunction\n",yytext);return (CONJUNCTION);}
fast|good|honest {printf("\t %s is an Adjective\n",yytext);return (ADJECTIVE);}
I|he|she|we|they|you|this {printf("%s is Pronoun\n",yytext);return (PRONOUN);}
in|on|to {printf("%s is a Preposition\n",yytext);return (PREPOSITION);}
[a-zA-Z]+ {printf("%s is a Noun\n",yytext);return (NOUN);}
. ;
%%
int yywrap()
{
return 1;
}
