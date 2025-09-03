#include <iostream>
#include <iomanip>

using namespace std;

/**
 * Echo all command line arguments, one per line in the form:
 *
 *   idx: arg
 *
 * where idx is the index (0, 1, ...) and arg is the argument.
 *
 * @param argc number of arguments
 * @param argv 2D array of C-style (null terminated) strings
 * @param outs output destination (e.g., cerr or cout)
 */
void echo_arguments(const int argc, char** argv, ostream& outs=std::cerr)
{
    // There is always at least one argument, i.e., the program name
    outs << right << setw(3) << 0 << ": " << argv[0] << "\n";
    outs << "\n";

    for (int i = 1; i < argc; i++) {
        // Technically "right" does not need to be repeated, but
        // we would probably tweak formatting more in the future
        outs << right << setw(3) << i << ": " << argv[i] << "\n";
    }
}

int main(int argc, char** argv)
{
    echo_arguments(argc, argv);

    return 0;
}