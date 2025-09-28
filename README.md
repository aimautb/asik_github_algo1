# Assignment 1 – Divide and Conquer Algorithms

**Author:** Bolatkhanuly Aimaut (SE-2407)  
**Course:** Design and Analysis of Algorithms  
**Repository:** [asik_github_algo1](https://github.com/aimautb/asik_github_algo1)

---

## 🎯 Goals of the Assignment
- Implement core **Divide and Conquer algorithms** with safe recursion patterns.
- Perform **theoretical analysis** of recurrence relations using:
  - **Master Theorem** (all 3 cases)
  - **Akra–Bazzi intuition** for more complex cases.
- Validate theoretical results through **empirical experiments**.
- Collect metrics:
  - Execution time (milliseconds)
  - Number of comparisons
  - Number of basic operations (swaps, copies)
  - Recursion depth (current and maximum)
- Follow a clean **GitHub workflow** with feature branches and structured commits.

---

## 📂 Project Structure
```
src/
├── main/java
│   ├── mergesort/      # MergeSort: cutoff to insertion sort + reusable buffer
│   ├── quicksort/      # QuickSort: randomized pivot, tail recursion optimization
│   ├── select/         # Deterministic Select: Median-of-Medians, O(n)
│   ├── closestpair/    # Closest Pair of Points in 2D, O(n log n)
│   ├── metrics/        # Metrics tracking (comparisons, operations, recursion depth, time)
│   ├── cli/            # CLI runners for experiments and CSV output
│   ├── utils/          # Partition, swap, shuffle utilities
│   └── bench/          # JMH benchmarks
└── test/java
    ├── mergesort/      # Tests for MergeSort
    ├── quicksort/      # Tests for QuickSort (depth and correctness)
    ├── select/         # Tests for deterministic Select
    ├── closestpair/    # Validation vs brute-force O(n²)
    └── metrics/        # Unit tests for Metrics
```

---

## ⚙️ Architecture Notes
- **Recursion depth control**:
  - QuickSort always recurses into the *smaller partition* and iterates over the larger one → depth is guaranteed to be `O(log n)` even in bad cases.
  - MergeSort switches to **InsertionSort** for small subarrays (threshold `CUTOFF = 16`) to reduce recursion overhead.
- **Buffer reuse in MergeSort**:
  - A single buffer array is reused for merging to avoid excessive memory allocations.
- **Non-static metrics**:
  - Metrics are passed as objects to algorithms instead of using static counters.
  - This allows safe parallel tests and prevents incorrect data between runs.
- **Metrics track**:
  - Number of comparisons
  - Number of operations (assignments/swaps)
  - Current and maximum recursion depth
  - Execution time (ms)

---

## 🔢 Theoretical Recurrence Analysis

| Algorithm             | Recurrence                       | Method Used         | Complexity |
|----------------------|-----------------------------------|--------------------|------------|
| **MergeSort**        | T(n) = 2T(n/2) + Θ(n)            | Master Theorem, Case 2 | Θ(n log n) |
| **QuickSort** (avg)  | T(n) = T(k) + T(n-k-1) + Θ(n)     | Master intuition + randomization | Θ(n log n) |
| **QuickSort** (worst)| T(n) = T(n-1) + Θ(n)             | Direct expansion   | Θ(n²) |
| **Select (MoM5)**    | T(n) ≤ T(n/5) + T(7n/10) + Θ(n)   | Akra–Bazzi         | Θ(n) |
| **Closest Pair**     | T(n) = 2T(n/2) + Θ(n)            | Master Theorem, Case 2 | Θ(n log n) |

---

## 📊 Empirical Results

The CLI automatically generates CSV files for experiments.  
**CSV format:**
```
algorithm,n,timeMillis,comparisons,operations,maxDepth
mergesort,1000,3,10850,12100,11
quicksort,1000,2,9740,10450,10
select,1000,1,4270,5120,7
closest,1000,4,23000,24700,14
```

### Example Graphs:
- ⏱ **Time vs n** – MergeSort and QuickSort show n log n growth, Select shows linear growth.
- 🌲 **Recursion depth vs n** – QuickSort depth bounded by ~2 * log₂(n), MergeSort depth ≈ log₂(n).
- 📊 **Comparisons vs n** – MergeSort and QuickSort are n log n, Select is linear.

**Observations:**
- MergeSort is stable and predictable.
- QuickSort is slightly faster on random data but has more variance due to randomized pivot.
- Deterministic Select is slower for small n but shows clear linear scaling for large n.
- Closest Pair has higher constants but follows n log n growth as expected.

---

## 🧪 Testing Strategy

| Algorithm     | Validation Method |
|---------------|-------------------|
| **MergeSort** | Compare output with `Arrays.sort()` on random and sorted arrays |
| **QuickSort** | Ensure recursion depth ≤ 2 * floor(log₂ n) + O(1) |
| **Select**    | Compare kth element with `Arrays.sort()[k]` on 100 random trials |
| **Closest Pair** | Validate against brute-force O(n²) for n ≤ 2000 |

Example QuickSort depth test:
```java
assertTrue(metrics.getMaxDepth() <= 2 * (int)Math.floor(Math.log(n) / Math.log(2)) + 2);
```

---

## 🌱 Git Workflow

**Feature Branches:**
- `feature/metrics` — Metrics implementation
- `feature/mergesort` — MergeSort implementation
- `feature/quicksort` — QuickSort implementation
- `feature/select` — Deterministic Select
- `feature/closest` — Closest Pair of Points
- `feature/cli` — Command-line interface and CSV output
- `docs/report` — Report and documentation

**Example commit history:**
1. `init: add maven project, junit5, readme`
2. `feat(metrics): implement Metrics class and tests`
3. `feat(mergesort): add MergeSort with cutoff and reusable buffer`
4. `feat(quicksort): add QuickSort with randomized pivot`
5. `feat(select): implement deterministic Median-of-Medians select`
6. `feat(closest): implement closest pair D&C algorithm`
7. `feat(cli): CLI tool for experiments and CSV logging`
8. `docs(report): add theoretical analysis and plots`
9. `release: v1.0`

---

## ✅ Summary
- Implemented 4 major Divide and Conquer algorithms with safe recursion and bounded depth.
- Metrics collection was refactored to avoid static counters and ensure correctness.
- Graphs and experiments confirmed theoretical complexity matches practical results.
- Git workflow is clean with feature branches and clear commit messages.
- Ready for presentation and defense.

---

This project demonstrates not only correct implementations but also safe recursion patterns,  
clean metrics collection, and reproducibility through CLI experiments.  
All results confirm theoretical complexity bounds, and the Git workflow ensures maintainability.  
The repository is ready for submission and oral defense. ✅
