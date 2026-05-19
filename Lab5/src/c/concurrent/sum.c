#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int do_sum(const char *path) {
    FILE *file = fopen(path, "rb");
    if (file == NULL) {
        return -1;
    }

    int sum = 0;
    int byte;

    while ((byte = fgetc(file)) != EOF) {
        sum += byte;
    }

    fclose(file);
    return sum;
}

typedef struct {
    const char *path;
    int sum;
} ThreadData;

void *sum_file_thread(void *arg) {
    ThreadData *data = (ThreadData *) arg;
    data->sum = do_sum(data->path);
    return NULL;
}

int main(int argc, char *argv[]) {
    int files_count = argc - 1;

    if (files_count <= 0) {
        return 0;
    }

    pthread_t *threads = malloc(sizeof(pthread_t) * files_count);
    ThreadData *data = malloc(sizeof(ThreadData) * files_count);

    if (threads == NULL || data == NULL) {
        free(threads);
        free(data);
        return 1;
    }

    for (int i = 0; i < files_count; i++) {
        data[i].path = argv[i + 1];
        data[i].sum = -1;

        if (pthread_create(&threads[i], NULL, sum_file_thread, &data[i]) != 0) {
            data[i].sum = do_sum(data[i].path);
        }
    }

    for (int i = 0; i < files_count; i++) {
        pthread_join(threads[i], NULL);
    }

    for (int i = 0; i < files_count; i++) {
        if (data[i].sum >= 0) {
            printf("%s : %d\n", data[i].path, data[i].sum);
        }
    }

    free(threads);
    free(data);
    return 0;
}