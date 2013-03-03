#include <jni.h>
#include <sys/mman.h>
#include <stdint.h>

JNIEXPORT jint JNICALL Java_net_zhuoweizhang_pokerface_PokerFace_mprotect
  (JNIEnv *env, jclass clazz, jlong addr, jlong len, jint prot) {
	return mprotect((void *)(uintptr_t) addr, len, prot);
}

JNIEXPORT jlong JNICALL Java_net_zhuoweizhang_pokerface_PokerFace_sysconf
  (JNIEnv *env, jclass clazz, jint name) {
	long result = sysconf(name);
	return result;
}
