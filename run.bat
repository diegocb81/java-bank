@echo off
call gradlew build
cd build\classes\java\main
java br.com.dio.Main
cd ..\..\..\..