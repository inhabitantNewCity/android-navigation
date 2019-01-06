cmake_minimum_required(VERSION 3.4.1)

add_library(recognition SHARED
            recognition.c)


target_link_libraries(recognition
                      android
                      log)