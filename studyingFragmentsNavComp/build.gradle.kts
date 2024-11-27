// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false


}

//Por algum motivo, estava dando algum problema ao criar essa variavel com a versao do nav component.
//buildscript {
//    ext.nav_version = "2.3.5"
//}