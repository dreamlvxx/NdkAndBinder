//
// Created by LXX on 2019/9/2.
//
#include <jni.h>
#include <string>
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_ndkcpp2_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello hahahahaahahah";
    return env->NewStringUTF(hello.c_str());
}

