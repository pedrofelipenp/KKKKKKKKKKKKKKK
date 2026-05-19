import sys
import threading


def do_sum(path):
    _sum = 0

    with open(path, 'rb', buffering=0) as f:
        byte = f.read(1)
        while byte:
            _sum += int.from_bytes(byte, byteorder='big', signed=False)
            byte = f.read(1)

    return _sum


def worker(path, results, index):
    try:
        results[index] = do_sum(path)
    except Exception:
        results[index] = None


if __name__ == "__main__":
    paths = sys.argv[1:]
    results = [None] * len(paths)
    threads = []

    for index, path in enumerate(paths):
        thread = threading.Thread(target=worker, args=(path, results, index))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    for index, path in enumerate(paths):
        if results[index] is not None:
            print(path + " : " + str(results[index]))