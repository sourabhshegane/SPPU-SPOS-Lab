#include <stdio.h>
#include "ExampleDLL.h"
JNIEXPORT int JNICALL Java_ExampleDLL_mul(JNIEnv *env, jobject javaobj, jint num1, jint num2) 
{
	return num1*num2;
}
