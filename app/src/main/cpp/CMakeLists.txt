
# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html.
# For more examples on how to use CMake, see https://github.com/android/ndk-samples.

# Sets the minimum CMake version required for this project.
cmake_minimum_required(VERSION 3.22.1)

project("plasma")

add_subdirectory(audio)
INCLUDE_DIRECTORIES(audio)

option(BUILD_TESTS "Build tests" OFF)
option(ENABLE_FLOAT "single-precision" ON)
option(DISABLE_FORTRAN "Disable Fortran wrapper routines" ON)

add_subdirectory(fftw-3.3.9)
INCLUDE_DIRECTORIES(fftw-3.3.9/api)

add_library(${CMAKE_PROJECT_NAME} SHARED
        # List C/C++ source files with relative paths to this CMakeLists.txt.
        plasma.cpp colormaps.cpp scale.cpp waterfall.cpp auformat.cpp ChunkerProcessor.cpp Processor.cpp
)

# Specifies libraries CMake should link to your target library. You
# can link libraries from various origins, such as libraries defined in this
# build script, prebuilt third-party libraries, or Android system libraries.
target_link_libraries(${CMAKE_PROJECT_NAME}
        # List libraries link to the target library
        android
        log
        audio
        fftw3f
)
