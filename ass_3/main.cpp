#include <iostream>
#include <vector>
#include <omp.h>

constexpr int N = 100;            // total elements
constexpr int NUM_PROCESSORS = 4; // #threads to launch

int main()
{
    // 1. build the array
    std::vector<int> arr(N);
    for (int i = 0; i < N; ++i)
        arr[i] = sizeof(int) * i;

    // 2. per-thread partial sums
    long long partial[NUM_PROCESSORS] = {0};

#pragma omp parallel num_threads(NUM_PROCESSORS)
    {
        int tid = omp_get_thread_num();
        int start = tid * (N / NUM_PROCESSORS);
        int end = (tid + 1) * (N / NUM_PROCESSORS);

        for (int i = start; i < end; ++i)
            partial[tid] += arr[i];
    }

    long long total = 0;
    for (int i = 0; i < NUM_PROCESSORS; ++i)
    {
        total += partial[i];
        std::cout << "Thread " << i << " partial = " << partial[i] << '\n';
    }
    std::cout << "Total sum  = " << total << '\n';
    return 0;
}
