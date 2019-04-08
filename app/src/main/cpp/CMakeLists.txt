cmake_minimum_required(VERSION 3.4.1)

add_library(recognition
        SHARED
        recognition.cpp
        Thread_recognition_area.cpp
        ./Function/*.cpp
        ./Contour/*.cpp
        )


target_link_libraries(recognition
                      android
                      log)