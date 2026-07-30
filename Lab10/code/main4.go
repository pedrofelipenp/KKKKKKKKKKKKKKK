package main

import (
	"fmt"
	"time"
)

func produtor(nome string, c chan<- string) {
	for i := 1; i <= 5; i++ {
		c <- fmt.Sprintf("%s -> %d", nome, i)
		time.Sleep(500 * time.Millisecond)
	}
}

func main() {
	canal := make(chan string)

	go produtor("A", canal)
	go produtor("B", canal)

	for i := 0; i < 10; i++ {
		fmt.Println(<-canal)
	}
}